package src;

public class Distribution extends Thread {

    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("erreur du sleep");
        }

    }
}
