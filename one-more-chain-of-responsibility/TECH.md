## How would I deal with a processing of a document? 

### The requirement & the solution

Let's say, I have a requirement to process a document and its processing depends on its content or meta information. Additionally, I don't know how many types of documents are out there, so ideally I would like my solution to be easily extensible, so that it's able to cover types of documents that are unknown at the time of the implementation. Naturally this could be done with an if-else chain with complex conditions or switch statement, but I think that a framework that have a certain degree of separation of concerns would be more elegant and easily maintainable. 

### The framework in detail

Of course, we can utilize some existing framework for that, like Spring for example, but we don't want to make ourselves too dependent on frameworks, do we? Oftentimes these frameworks are too overblown, for the use cases, that they are used for. Besides, if we choose an external framework, next thing we'll need to do is to take care of the maintenance of the framework. So, it's better to not overcomplicate things.

What we have is a document, which we want to read, evaluate and write into some storage. It has content and some properties relevant to the processing, like for instance - header, footer, size in terms of columns or rows, words etc. In Java the document can look something like this:

```
record RawDocument(Content content, ProcessProperties processProperties) {}

record Content() {}

record ProcessProperties(ProcessType processType) {}

```

The document is a subject to some processing. The processing could be anything. What we, however know, that we do have certain number of processing steps, which are fixed for every document. Let's say we have pre-processing, processing and persisting. In some cases, a processing could be very simple, and it would involve only minor transformations in the document's content. In other cases, it might be more complicated. We don't know which case of processing will it be, until we have some information about the document. We can let the user give us full description of it and work with that. However, user information can be unreliable, and needs to be validated anyway, so we want to have less user input and more work on our side, to make sure we're processing the document correctly. 

Let's start with as single interface:

```

interface DocumentProcessor {

    RawDocument process(RawDocument doc);

    default void doStuff() {
         System.out.println("Processing stuff with " + this);
    }
}

```


