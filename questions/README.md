## Describe and compare fail-fast and fail-safe iterators. Give examples.

The main distinction between fail-fast and fail-safe iterators is whether or not the collection can be modified while it is being iterated. Fail-safe iterators allow this; fail-fast iterators do not.

* Fail-fast iterators operate directly on the collection itself. During iteration, fail-fast iterators fail as soon as they realize that the collection has been modified (i.e., upon realizing that a member has been added, modified, or removed) and will throw a ConcurrentModificationException. Some examples include **ArrayList**, **HashSet**, and **HashMap** (most JDK1.4 collections are implemented to be fail-fast).

* Fail-safe iterates operate on a cloned copy of the collection and therefore do not throw an exception if the collection is modified during iteration. Examples would include iterators returned by **ConcurrentHashMap** or **CopyOnWriteArrayList**.

## ArrayList, LinkedList, and Vector are all implementations of the List interface. Which of them is most efficient for adding and removing elements from the list? Explain your answer, including any other alternatives you may be aware of.

Of the three, LinkedList is generally going to give you the best performance. Here’s why:

ArrayList and Vector each use an array to store the elements of the list. As a result, when an element is inserted into (or removed from) the middle of the list, the elements that follow must all be shifted accordingly. Vector is synchronized, so if a thread-safe implementation is not needed, it is recommended to use ArrayList rather than Vector.

LinkedList, on the other hand, is implemented using a doubly linked list. As a result, an inserting or removing an element only requires updating the links that immediately precede and follow the element being inserted or removed.

However, it is worth noting that if performance is that critical, it’s better to just use an array and manage it yourself, or use one of the high performance 3rd party packages such as Trove or HPPC.

## Why would it be more secure to store sensitive data (such as a password, social security number, etc.) in a character array rather than in a String?

In Java, Strings are immutable and are stored in the String pool. What this means is that, once String is created, it stays in the pool in memory until being garbage collected. Therefore, even after you’re done processing the string value (e.g., the password), it remains available in memory for an indeterminate period of time thereafter (again, until being garbage collected) which you have no real control over. Therefore, anyone having access to a memory dump can potentially extract the sensitive data and exploit it.

In contrast, if you use a mutable object like a character array, for example, to store the value, you can set it to blank once you are done with it with confidence that it will no longer be retained in memory.

## SOLID

* Single responsibility principle - Class has one job to do. Each change in requirements can be done by changing just one class.
* Open/closed principle - Class is happy (open) to be used by others. Class is not happy (closed) to be changed by others.
* Liskov substitution principle - Class can be replaced by any of its children. Children classes inherit parent's behaviours.
* Interface segregation principle - When classes promise each other something, they should separate these promises (interfaces) into many small promises, so it's easier to understand. A client should never be forced to implement an interface that it doesn’t use, or clients shouldn’t be forced to depend on methods they do not use.

* Dependency inversion principle - Entities must depend on abstractions, not on concretions. It states that the high-level module must not depend on the low-level module, but they should depend on abstractions. When classes talk to each other in a very specific way, they both depend on each other to never change. Instead classes should use promises (interfaces, parents), so classes can change as long as they keep the promise.