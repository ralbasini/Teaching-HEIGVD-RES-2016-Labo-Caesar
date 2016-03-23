# Teaching-HEIGVD-RES-2016-Labo-Caesar

## COMMENTS

* We achieved every requested objectives. Our programm runs smoothly and allows multiple clients to connect to the server. We use a `Message` class to encapsulate the message to be sent. A random `delta` is generated for the encryption, and the Message object is serialized using the Gson method. This serialized object is written by the client on the buffer, and then received by the server. The server will first unserialized it, and then uncrypt its payload using the random `delta` contained in the structure. The server then re-encrypts the uncrypted payload with a newly generated `delta`, and send it to the client. The clients repeats the unencryption process, and displays the plain message.

* We just have a small issue on some encrypted messages. We wanted our message encryption to work not only on alphabetical characters, but on special characters too (!, ?, accentuated letters, aso...). Unfortunately, the characters after 'z' in the ASCII table are not supported by the JDK, resulting in some encryption errors when applying the caesar method on some lowercase characters whith some delta. 

* To resolve this bug, we should have a cyclic implementation on the alphabetical characters only. There wouldn't be much trouble fixing it, but as said by the assistant, it wasn't the purpose of this lab. In the meantime, a workaround is to type the message in UPPER CASE. Our encryption works perfectly fine in that case.

* See the `class.png` and `sequence.png` files for better understanding of our implementation.

* ALBASINI Romain, SERNEELS Guillaume

## Objective

* The first objective is to **design a simple application protocol**, which allows a client and a server to have a confidential conversation over a TCP channel. The messages exchanged by the client and the server will be encoded using the [Caesar cipher](https://en.wikipedia.org/wiki/Caesar_cipher).

* The second objective is to **implement a Java client and a Java server** compliant with your protocol.

* The third objective is to **prepare a demonstration** to show the result of your work and **prove** that the client and the server can communicate **and** that the communication is encrypted. During the demo, you should also present your protocol and OO design. **All of that within 5'**!

![image](./images/Ave_Moi.png)

## Requirements

* The Caesar cipher is a very simple encoding scheme. To make it a bit more robust, the "delta" parameter (the number of alphabet positions to use for the shift) **MUST NOT** always be the same and **MUST NOT** be hard-coded. In other words, the client and the server must agree on the value of this parameter. 

* During the demo, you **MUST** prove that the communication is encrypted.

* During the demo, you **MUST** prove that the server is able to serve several clients in parallel (i.e. it is multi-threaded). Even if it takes a very short time to serve a client.


* In the `fb-mvn-starter-kit` branch, you will find a structure of maven projects that you **CAN** use as a starting point. You **MAY** also start from scratch and define your own structure.

* Please fork this repo and push your code and documentation artifacts in your repo when you are done.


## Q&A

**Do we have to implement a stateful or a stateless protocol?**
Up to you. A stateless protocol (a single request-reply in every TCP connection) might be easier to implement, but harder to demo (if you want to prove that multi-threading works).

**Do we have to set the "delta" parameter for every conversation or every message?**
Up to you. In either case, the client and the server will need to agree on the value of the parameter.

**How could we prove that the communication is really encrypted?**
Using wireshark or ``tcp dump`` is probably a good idea.

**How can we quickly present the design of our application protocol?**
A picture is worth a thousand words. Especially if you are using a [sequence diagram](http://cdn.routemybrain.com/wp-content/uploads/2010/04/3-way-handshake-Intro-to-transport-layer-The-internetworking-Part2.gif). Showing an example of a [concrete scenario](https://tools.ietf.org/html/rfc5321#appendix-D.1) is also very effective.

**How can we quickly present the OO design of our solution**
A picture is worth a thousand words. Especially if you are using a class diagram. You don't have to display all the interfaces and classes in your code. You should rather highlight the important ones and explain what are their responsibilities.

**How can we avoid to be late and not to have anything to demo in time?**

1. **Don't rush in the code**. Spend the time to design the protocol and write a concrete scenario (i.e. an example of several messages exchanged by the client and the server in sequence). 

2. **Plan your demo scenario** as early as possible. This will help you to take all requirements in consideration, to prepare the visual support, etc. You will see that it will also help you for the design of the protocol!

3. **If you decide to split the work** and decide that one person implements the client and the other implements the server, then make sure that they are interoperable as soon as possible. Do not wait to have completely implemented the protocol to do the first test. It is already useful to know that a client can connect with the server, send a plain-text message and receive an answer. **But maybe you will prefer to do pair-programming** and to do (most of) the work together. Given the timeline, this might be a good option (maybe the diagrams used for the demo can be done in parallel by one person).




