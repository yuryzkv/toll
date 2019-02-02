package example;

public class BadThreadSolution3 {


    public static void main(String args[]) throws InterruptedException {
        CheckLock checkLock = new CheckLock();
        for (int i = 0; i < 1000; i++) {
            AgainCorrectorThread againCorrectorThread = new AgainCorrectorThread(checkLock);
            checkLock.put("Казнить");
            againCorrectorThread.start();
            if (checkLock.take().equals("Казнить"))
                System.out.println(checkLock.take());
        }
    }
}
