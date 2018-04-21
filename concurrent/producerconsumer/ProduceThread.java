package demo.concurrent.producerconsumer;

public class ProduceThread extends Thread{

    private Resource resource;

    ProduceThread(Resource resource,String name){
        super(name);
        this.resource=resource;

    }

    @Override
    public void run() {
        while(true){
            resource.push();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
