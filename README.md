                                        Bobble Assignment(Android)

Ans. 1:   I created a ChatLogActivity containing all of our messages. I rendered out rows for our messages and with the help of Groupie I made the adapter objects. To send the messages on our screen, we first require to save a list of messages under a node in Firebase Database. Next I listened for all of the messages as they are entered in the system, and then refreshing our list in real time. I also added the functionality to automatically scroll to the bottom of the recycler view and also the edit text will become empty once the text is sent.

The link to my  github repository is below. Please clone the project and run it to have a practical view. 



Ans. 2:  Concurrent Modification Exception is the child class of Runtime Exception and was introduced in JDK 1.2. We can also consider this as an unchecked exception. Thus we don’t need to take care of these exceptions at the time of compilation (we don’t need to wrap it with Throws). 
This happens when a collection type (List/Map/Set etc.) is getting by more than one thread or multiple threads.   
For e.g. 
public static void main(String[] args) throws InterruptedException{
System.out.println(“Main thread Started”);
List<String> ob=new ArrayList<String>();
ob.add(“java”);
ob.add(“.net”);
ob.add(“cpp”);
ob.add(“c”);
MyThread mythread=new MyThread(ob);
new Thread(mythread).start();                     
Iterator<String> itr=courses.iterator();
while(itr.hasNext()){
System.out.println(“Course:”+itr.next());
Thread.currentThread().sleep(1000) ;   //making main thread to sleep for 1 sec.
}
}


This program will have concurrentModificationException as there are two threads getting operated on a single collection type.

How to handle this exception-
There are two ways for this:
1)	If we are using lower versions of JDK 1.5 then use synchronized collections (SynchronizedList, SynchronizedSet, SynchronizedMap)
2)	If we are using jdk 1.5 or higher version then use java.util.concurrent package (CopyOnwriteArrayList, CopyOnWriteArraySet, ConcurrentHashMap)
FOR E.G.-
•	Using concurrent package –
Package com.sun.concurrent;
Import java.util.ArrayList;
Public class SynchronizedListTest{
public static void main(String[] args) throws InterruptedException{
System.out.println(“Main Thread Started”);
List<String> courses=Collections.synchronizedList(new ArrayList<String>());
courses.add(“java”);
courses.add(“.net”);
courses.add(“cpp”);
courses.add(“c”);
MyThread mythread=new MyThread(courses);
new Thread(mythread).start();
Iterator<String> itr=courses.iterator();
synchronized(courses){
While(itr.hasNext()){
System.out.println(“Course:”+itr.next());
Thread.currentThread().sleep(1000);
}
}
}


•	Using concurrent package :
package com.sun.concurrent;
import java.util.Iterator;
public class ConcurrentTest{
public static void main (String[] args) throws InterruptedException{
System.out.println(“Main Thread Started”);
List<String> courses=new CopyOnWriteArrayList<String>();
courses.add(“Java”);
courses.add(“.net”);
courses.add(“cpp”);
courses.add(“c”);
MyThread mythread=new MyThread(courses);
New Thread(mythread).start();
Iterator<String>itr=courses.iterator();
While(itr.hasNext()){
System.out.println(“Course:”+itr.next());
Thread.currentThread().sleep(1000);
}
}
}


Though we have two ways of handling this exception, the recommended way is using java.util.concurrent package exceptions. These classes are specially developed for the collections concurrent usage.
In Synchronized collections, the entire object is going to be synchronized but at a time only one object is going to be operated in synchronized collection. Whereas in concurrent collections, for reading purpose, any number of threads can be operated. For updating purpose only one thread should be operated.








            

