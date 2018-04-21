package demo.concurrent.producerconsumer;

public class ConsumeThread extends Thread{

    private Resource resource;

    ConsumeThread(Resource resource,String name){
        super(name);
        this.resource=resource;

    }

    @Override
    public void run() {
        while(true){
            resource.pop();//执行完同步代码块,释放锁
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
