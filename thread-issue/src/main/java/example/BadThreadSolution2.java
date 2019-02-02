package example;


public class BadThreadSolution2 {

    static String message;


    private static class CorrectorThread
            extends Thread {

        public void run() {
            message = "Помиловать";
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Thread correctorThread;
        for (int i = 0; i < 10; i++) {
            correctorThread = new CorrectorThread();
            message = "Казнить";
            correctorThread.start();
            correctorThread.join();
            System.out.println(message);
        }
    }
}
