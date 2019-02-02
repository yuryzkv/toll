package example;

public class BadThreadSolution1 {


    public static void main(String args[]) throws InterruptedException {
        MessageDelivery messageDelivery = new MessageDelivery();
        for (int i = 0; i < 1000; i++) {
            CorrectorThread correctorThread = new CorrectorThread(messageDelivery);
            messageDelivery.put("Казнить");
            correctorThread.start();
            if (messageDelivery.take().equals("Казнить"))
                System.out.println(messageDelivery.take());
        }
    }
}
