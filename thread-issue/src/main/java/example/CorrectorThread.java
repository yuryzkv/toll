package example;

public class CorrectorThread extends Thread {

    private MessageDelivery messageDelivery;

    public CorrectorThread(MessageDelivery messageDelivery) {
        this.messageDelivery = messageDelivery;
    }

    public void run() {
        messageDelivery.put("Помиловать");
    }
}
