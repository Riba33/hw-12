package javafiles;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;


public class Task1 {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3);
    private static final Semaphore oxiSemaphore = new Semaphore(1);
    private static final Semaphore hydroSemaphore = new Semaphore(2);

    public static void main(String[] args) {
        for (int i = 0; i <7; i++)
        { new Oxygen().start();
            new Hydrogen().start();
            new Hydrogen().start();
        }


    }
    public static class Hydrogen extends Thread{
        private static void releaseHydrogen() {
            try {
                hydroSemaphore.acquire();
                try {

                    BARRIER.await();
                    System.out.print("H");
                }
                catch (InterruptedException | BrokenBarrierException e) {}
                hydroSemaphore.release();
            }
            catch (InterruptedException e){}

        }
        @Override
        public void run()
        {
            releaseHydrogen();
        }
    }
    public static class Oxygen extends Thread{
        private static void releaseOxygen() {
            try {
                oxiSemaphore.acquire(1);
                try {

                    BARRIER.await();
                    System.out.print("O");
                }
                catch (InterruptedException | BrokenBarrierException e) {}
                oxiSemaphore.release();
            }
            catch (InterruptedException e){}
        }
        @Override
        public void run()
        {
            releaseOxygen();

        }
    }
}
