package example;

public class AgainCorrectorThread extends Thread {

    private CheckLock checkLock;

    public AgainCorrectorThread(CheckLock checkLock){
        this.checkLock = checkLock;
    }

    public void run() {
        checkLock.put("Помиловать");
    }
}
