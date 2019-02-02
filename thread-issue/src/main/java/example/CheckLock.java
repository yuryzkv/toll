package example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CheckLock {

    private String message;
    private Lock lock = new ReentrantLock();

    public boolean isGood() {
        Boolean isLock = false;

        if (message.equals("Помиловать")) {
            isLock = lock.tryLock();
        }

        return isLock;
    }


    public synchronized void put(String message) {
        notifyAll();
        this.message = message;
    }

    public synchronized String take() {
        try {
            while (!isGood()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        return message;
    }
}
