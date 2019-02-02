package myexample;

import java.util.Random;

public  class ConsumerMessage implements Runnable {
    private DropMessage drop;
    public ConsumerMessage(DropMessage drop){
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        try {
            for (String message = drop.take();
                 ! message.equals("DONE");
                 message = drop.take()) {
                System.out.format("MESSAGE RECEIVED: %s%n",
                        message);
                Thread.sleep(random.nextInt(5000));
            }
        } catch (InterruptedException e) {}
    }
}