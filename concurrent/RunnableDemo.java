package demo.concurrent;

/**
 * Created by Lee on 2018/2/6.
 */
public class RunnableDemo implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I wanna money");
    }

    public static void main(String[] args) throws InterruptedException {
        RunnableDemo demo = new RunnableDemo();
        Thread thread = new Thread(demo);
        thread.start();
        thread.join();
        System.out.println("end");
    }
}
