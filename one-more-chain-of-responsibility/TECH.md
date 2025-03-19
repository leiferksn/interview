## How would I deal with a processing of a document? 

### The requirement & the solution

Let's say, I have a requirement to process a document and its processing depends on its content or meta information. I don't know how many types of documents are out there. Ideally, I would like my solution to be easily extensible ss that the application be able to cover types of documents that are unknown at the time of the initial implementation. In addition, I want the user to make a single input in the beginning with as little configuration as possible and the application to take over from there and do the processing automatically. Naturally this could be done with an if-else chain with complex conditions or switch statement, but a framework that have a certain degree of separation of concerns would be more elegant and easily maintainable. 

### What does the framework look like?

Of course, we can utilize some existing framework for that, like Spring for example, but we don't want to make ourselves too dependent on frameworks, do we? Oftentimes these frameworks are too overblown, for the use cases, that they are used for. Besides, if we choose an external framework, next thing we'll need to do is to take care of the maintenance of the framework. So, it's better to not overcomplicate things.

What we have is a document, which we want to read, evaluate and write into some storage. It has content and some properties relevant to the processing, like for instance - header, footer, size in terms of columns or rows, words etc. In Java the document can look something like this:

```
record Document(Content content, Properties properties) {}

record Content(byte[] data) {}

record Properties(ProcessType processType) {}

```

The document is a subject to some processing. The processing could be anything. What we, however know, is, that we do have certain number of processing steps, which are fixed for every document. Let's say we have pre-processing, processing and persisting. In some cases, a processing could be very simple, and it would involve only minor transformations in the document's content. In other cases, it might be more complicated. We don't know which case of processing will it be, until we have some information about the document. We can let the user give us full description of it and work with that. However, user information can be unreliable, and it needs to be validated anyway, therefore, we want to have less user input and more work on our side, to make sure we're processing the document correctly. 

As we go, we can use the meta information stored in Properties to determine what to do with the document at each step of the process.

Let's start with as single interface:

```

interface DocumentProcessor {

    void process(Document doc);

}

```

All processors that we will implement this interface to ensure interoperability. 

At this point we need to think about how to make the process run automatically with a preconfigured set of processors. Two design patterns come to mind - chain of responsibility and state machine. In the first one the logic the processors are the driving force, in the second - the logic is in the states. Since my requirements look linear and consist of finite amount of steps, I would go for chain of responsibility. 

First we create two base classes to cover both the simple case and the more complicated case.

```



public abstract class SimpleDocumentProcessor implements DocumentProcessor {

    protected DocumentProcessor documentProcessor;

    @Override
    public abstract void process(RawDocument doc);

    public void nextProcessor(DocumentProcessor documentProcessor) {
        this.documentProcessor = documentProcessor;
    }
}


```


```

public abstract class CompositeDocumentProcessor<T> implements DocumentProcessor {

    protected DocumentProcessor documentProcessor;
    protected Map<T, DocumentProcessor> processors = new HashMap<>(); // this should either be static final (fixed) or of type ConcurrentHashMap to ensure thread-safety  

    @Override
    public abstract void process(Document doc);

    public void nextProcessor(T parameter) {
        this.documentProcessor = processors().get(parameter);
    }

    public Map<T, DocumentProcessor> processors() {
        return this.processors;
    }

    public  void addProcessor(T t, DocumentProcessor documentProcessor) {
        this.processors.put(t, documentProcessor);
    }

}


```

SimpleProcessor is self-explanatory. We implement the *process(Document doc)* method and make possible to add next processor - classical chain of responsibility.

In CompositeDocumentProcessor we allow the possibility to add sub-processors, which we can use to process different variants of the initial document. We do this by defining a map that links a generic property of the document to a specific processor. The next processor in the chain is chosen based on that property. 

This is all we need to cover a bunch of processing scenarios. 

### How to apply the framework?

Let's consider the following scenario. We have pre-processor, that reads a document and extracts some properties. With these properties we go into the main processing step. Here we will decide how to handle the document using the meta information we've gathered in the pre-processing step. In the end we'll persist the document. 

With our framework in hand, we get the following implementations

#### The pre-processor

```

public class PreProcessor extends SimpleDocumentProcessor {

    @Override
    public void process(Document doc) {
        doStuff(doc);
        final var newDoc = new Document(doc.content(), new Properties(randomProcessType()));
        this.documentProcessor.process(newDoc);
    }

    @Override
    public String toString() {
        return "Pre-Processor";
    }

    private ProcessType randomProcessType() {
        final var random = new Random();
        final var randomInt = random.ints(0, 100).findFirst().getAsInt();
        final var randomOrdinal = randomInt % 2 == 0 ? 1 : 0;
        return Arrays.stream(ProcessType.values()).filter(s -> s.ordinal() == randomOrdinal).findFirst().get();
    }
}

enum ProcessType {
    HEADERS_FROM_COLS, HEADERS_FROM_ROWS;
}

```

The purpose of the pre-processor is to find out what kind of process should we apply to the document to extract the headers from it. For the purposes of this example we randomize the setting of the property. This way, we can do see the different types of properties in action. 

The method *doStuff()* is where the actual processing logic is implemented. For this example this is just a method in DocumentProcessor, that would show us where are we at in our processing. 

```

default void doStuff(Document document) {
     System.out.println("Processing document " + document +  " with " + this);
}

```

To get a proper output, we need an implementation of *toString()* in our *Document* record. Like this:

```

@Override
public String toString() {
    return "of type " + (properties.processType() != null ? properties.processType().toString() : "undefined");
}

```

#### The main processor

Next in our implementation is the main processor. Its implementation looks like this:

```

public class MainProcessor extends CompositeDocumentProcessor<ProcessType> {

    @Override
    public void process(Document doc) {
        doStuff(doc);
        nextProcessor(doc.processProperties().processType());
        this.documentProcessor.process(doc);
    }

    @Override
    public String toString() {
        return "Main Processor";
    }
}

```

You can see that we make use of the generic type here and make a concrete implementation based on *ProcesType*. We don't do anything else here. We just do something with the document and pass it on to the next processor. In this case this could be one of two possible implementations:

```

public class HeadersFromColumnsProcessor extends SimpleDocumentProcessor {

    @Override
    public void process(RawDocument doc) {
        doStuff(doc);
        this.documentProcessor.process(doc);
    }

    @Override
    public void nextProcessor(DocumentProcessor documentProcessor) {
        super.nextProcessor(documentProcessor);
    }

    @Override
    public String toString() {
        return "Header from Cols Processor";
    }
}


```

or 

```

public class HeadersFromRowsProcessor extends SimpleDocumentProcessor {

    @Override
    public void nextProcessor(DocumentProcessor documentProcessor) {
        super.nextProcessor(documentProcessor);
    }

    @Override
    public void process(RawDocument doc) {
        doStuff(doc);
        this.documentProcessor.process(doc);
    }

    @Override
    public String toString() {
        return "Header from Rows Processor";
    }
}


```

#### The writer processor

In the end we have the persisting, which is handled by a simple processor.

```

public class WriteProcessor extends SimpleDocumentProcessor {

    @Override
    public void process(RawDocument doc) {
        doStuff(doc);
    }

    @Override
    public String toString() {
        return "Write Processor";
    }
}


```

Since this is the end of our processing, we don't pass the document any further.


### What does the configuration look like?

To see all this in action. We create a simple main class, where we put both the configuration of the process and the execution. In a real-life project we would like these two to be separate. 

```

public class DocumentProcessingApp {

    public static void main(String[] args) {

        // configuration

        final var writeProcessor = new WriteProcessor();
        writeProcessor.nextProcessor(null);

        final var headersFromColsProcessor = new HeadersFromColumnsProcessor();
        headersFromColsProcessor.nextProcessor(writeProcessor);

        final var headersFromRowsProcessor = new HeadersFromRowsProcessor();
        headersFromRowsProcessor.nextProcessor(writeProcessor);

        final var preProcessor = new PreProcessor();
        final var mainProcessor = new MainProcessor();
        mainProcessor.addProcessor(HEADERS_FROM_COLS, headersFromColsProcessor);
        mainProcessor.addProcessor(HEADERS_FROM_ROWS, headersFromRowsProcessor);

        preProcessor.nextProcessor(mainProcessor);

        // initiate execution

        preProcessor.process(new RawDocument(new Content(), new Properties(null)));

    }

}

```

By calling the *process* method in the *PreProcessor* we initiate the whole chain. 

The output of the execution looks something like this:

```

Processing document of type undefined with Pre-Processor
Processing document of type HEADERS_FROM_ROWS with Main Processor
Processing document of type HEADERS_FROM_ROWS with Header from Rows Processor
Processing document of type HEADERS_FROM_ROWS with Write Processor

```

Based on the different value of *processType* the main processor delegates the handling either to the HeadersFromColumnsProcessor or to the HeadersFromRowsProcessor. Both pass the document to the *WriteProcessor*.

### Final considerations

