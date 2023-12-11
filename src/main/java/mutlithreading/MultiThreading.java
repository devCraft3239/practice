package mutlithreading;

import java.util.concurrent.*;

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
        System.out.println("Thread is running");
    }
}


class Client{
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        MyRunnable myRunnable = new MyRunnable();
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
        MyCallable myCallable = new MyCallable();
        System.out.println(executorService.submit(myCallable).get());
    }
}


// by using FutureTask -> represent the result of an asynchronous computation
class Client3{
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyCallable myCallable = new MyCallable();
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