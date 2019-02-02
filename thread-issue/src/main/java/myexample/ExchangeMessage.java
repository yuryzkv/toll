package myexample;

public class ExchangeMessage {


    public static void main(String[] args) {
        DropMessage drop = new DropMessage();
        (new Thread(new ProducerMessage(drop))).start();
        (new Thread(new ConsumerMessage(drop))).start();
    }
}
