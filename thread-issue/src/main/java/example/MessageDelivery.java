package example;

public class MessageDelivery {

    static String message;
    static boolean isReallyBad = true;


    public  synchronized void put(String message) {

       if(message.equals("Казнить")){
           isReallyBad = true;
       }

       if(message.equals("Помиловать")){
           isReallyBad = false;
       }

        notifyAll();
        this.message = message;
    }

    public synchronized String take(){

        while(isReallyBad){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isReallyBad = true;
        notifyAll();
        return message;
    }
}
