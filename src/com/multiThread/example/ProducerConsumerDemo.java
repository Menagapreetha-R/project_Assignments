package com.multiThread.example;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerDemo {

public static void main(String[] args) {
List<Integer> sharedList = new ArrayList<Integer>();
Thread thread1 = new Thread(new Producer(sharedList));
Thread thread2 = new Thread(new Consumer(sharedList));
thread1.start();
thread2.start();


}

}
class Producer implements Runnable{
List<Integer> sharedList= null;
final int MAX_SIZE = 5;
private int i = 0;

public Producer(List<Integer> sharedList) {
super();
this.sharedList = sharedList;
}
@Override
public void run() {
while(true) {
try {
Produce(i++);
}
catch(InterruptedException exception) {
}
}
}
public void Produce(int i) throws InterruptedException {
synchronized(sharedList) {
while(sharedList.size() == MAX_SIZE) {
System.out.println("sharelist is full ...waiting for the consumer to consume");
sharedList.wait();
}
}
synchronized (sharedList) {
System.out.println("produce produced element" + i);
sharedList.add(i);
Thread.sleep(100);
sharedList.notify();
}
}
}
class Consumer implements Runnable{
List<Integer> sharedList= null;
public Consumer(List<Integer> sharedList) {
super();
this.sharedList = sharedList;
}
@Override
public void run() {
while(true) {
try {
Consume();
}
catch(InterruptedException exception) {
}
}
}
public void Consume() throws InterruptedException {
synchronized(sharedList) {
while(sharedList.isEmpty()) {
System.out.println("sharelist is empty ...waiting for the producer to produce the object");
sharedList.wait();
}
}
synchronized (sharedList) {
Thread.sleep(500);
System.out.println("consume the element" + sharedList.remove(0));
sharedList.notify();
}
}
}



