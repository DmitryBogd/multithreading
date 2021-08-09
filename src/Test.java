import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ArrayBlockingQueue;


public class Test {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
    private static void produce() throws InterruptedException {
        Random random = new Random();

        while (true){
            queue.put(random.nextInt(100));
        }
    }

    private  static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(random.nextInt(10)==5) {
                System.out.println(queue.take());
                System.out.println("queue size is: " + queue.size());
            }
        }
    }
}




