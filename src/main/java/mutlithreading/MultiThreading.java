package mutlithreading;

import java.util.concurrent.*;

/*
 MultiThreading: is a process of executing multiple threads simultaneously.
    1. By extending Thread class
    2. By implementing Runnable interface
    When to use Runnable and when to use Thread?
        1. If we want to extend some other class then we should use Runnable interface
        2. If we want to override some method of Thread class then we should use Thread class
    3. By using ExecutorService
    4. By implementing Callable interface
    5. By using FutureTask -> represent the result of an asynchronous computation
    6. By using CompletableFuture -> advantage over FutureTask is that it can be chained
 */

public class MultiThreading {
}

// by extending Thread class
class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}

// by implementing Runnable interface
class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Runnable is running");
    }
}


class Client{
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();

        Runnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}

// by using executorService
class Client1{
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++){
            int finalI = i;
            executorService.submit(()-> System.out.println("Thread "+ finalI +" is running"));
        }
    }
}

// by implementing Callable interface
class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Callable Thread is running";
    }
}


class Client2{
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<String> myCallable = new MyCallable();
        System.out.println(executorService.submit(myCallable).get());
    }
}


// by using FutureTask -> represent the result of an asynchronous computation
class Client3{
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<String> myCallable = new MyCallable();
        Future<String>  future = executorService.submit(myCallable);
        System.out.println(future.get());
    }
}

// by using CompletableFuture -> advantage over FutureTask is that it can be chained
class Client4{
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->"Hello", executorService)
                .thenApplyAsync(s->s+" World", executorService);
        System.out.println(completableFuture.get());
    }
}