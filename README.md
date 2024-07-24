# Reactive Streams
The purpose of Reactive Streams is to provide a standard for asynchronous stream processing with non-blocking backpressure.

### Asynchronous still Blocks

In Java, you can write code asynchronously using [Callbacks](https://docs.oracle.com/javase/8/docs/api/javax/security/auth/callback/Callback.html) and [Futures](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Future.html). You can then get and join threads at some later point in time and process the result. Java 8 introduced us with a new class - [CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html), which makes it much easier to coordinate these things.



# Java 9 Reactive Streams

Reactive Streams is a standard for asynchronous stream processing with non-blocking back pressure. This specification is defined in the Reactive Manifesto, and there are various implementations of it, for example, RxJava or Akka-Streams.

[Java 9 Flow API](https://dev.to/ajiteshtiwari/java-9-flow-api-4e38)

Flow API is Java's official support for [Reactive Streams Specification](http://www.reactive-streams.org/). It is a combination of both Iterator(Pull) and Observer(Push) patterns.


## Introduction to Java 9’s Flow API

### How it works
A subscriber calls subscribe method of publisher to subscribe, publisher returns a subscription to subscriber. Subscriber can request for data to publisher via onRequest and then publisher emits events 1 to N times via onNext calls and finally emits an onComplete event to tell subscriber about completion of events.

### From Pull to Push to Pull-Push

Reactive programming, if I try to shrink it to a paragraph, is a way of programming in which the consumers are in control of the Data Flow, assigning a special importance to the fact that there might be slow consumers that need the publisher to slow down to be able to read all items within the data stream (the back-pressure concept). It’s not a disruptive technique; you could have used this pattern already, but it’s now becoming popular due to its integration in major frameworks and library distributions (e.g. Java 9 or Spring 5), and the rise of distributed systems coming with huge amounts of data that needs to be inter-communicated.

Looking at the past helps us understand its rise as well. Some years ago, the most popular technique to get data from consumers was a pull-based-mechanism. The client polls for data periodically and, if available, they read it. The advantage is that they can control the flow of data in case of having fewer resources (stop polling); the main disadvantage is the waste of processing time and/or network resources by polling for data when there is nothing to consume.

The trend changed over time and it became popular to push data from producers and let consumers take care of it. The problem with that is that consumers may have more limited resources than producers, ending up with full input buffers in case of slow consumers and therefore data losses. This may be fine if it happens only to a low percentage of our subscribers but, what if it’s happening to most of them? We could do better slowing down our publisher…

The hybrid pull-push approach that comes with Reactive Programming tries to bring the best of both worlds: it lets the consumers the responsibility of requesting data and controlling the flow from the Publisher, which can also decide between blocking or dropping data in case of lack of resources. We’ll see a good practical example below.



# Akka

Akka Streams implements the [Reactive Streams](https://www.reactive-streams.org/) standard for asynchronous stream processing with non-blocking back pressure.

