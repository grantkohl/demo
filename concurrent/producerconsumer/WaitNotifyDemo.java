package demo.concurrent.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {

        Resource resource=new WaitNotifyResource();

        Thread produceThreadA = new ProduceThread(resource,"producerA");
        Thread produceThreadB = new ProduceThread(resource,"producerB");
        Thread produceThreadC = new ProduceThread(resource,"producerC");
        Thread produceThreadD = new ProduceThread(resource,"producerD");

        produceThreadA.start();
        produceThreadB.start();
        produceThreadC.start();
        produceThreadD.start();

        Thread.sleep(10000);

        Thread consumeThreadA = new ConsumeThread(resource,"consumerA");
        Thread consumeThreadB = new ConsumeThread(resource,"consumerB");
        Thread consumeThreadC = new ConsumeThread(resource,"consumerC");
        Thread consumeThreadD = new ConsumeThread(resource,"consumerD");

        consumeThreadA.start();
        consumeThreadB.start();
        consumeThreadC.start();
        consumeThreadD.start();
    }
}

class WaitNotifyResource implements  Resource{

    List<String> resources =new ArrayList<>();

    Lock lock=new ReentrantLock();

    @Deprecated
    public synchronized void pushAll(){

        try {
            while(true){
                if(resources.size()>10){
                    System.out.println("生产队列已空");
                    this.wait();
                }else{
//                    Thread.sleep(100); //并不释放锁
                    System.out.println(Thread.currentThread().getName()+"生产中");
                    resources.add(UUID.randomUUID().toString());
                    this.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public synchronized void push(){

        try {
            while(resources.size()>10){
                System.out.println("生产队列已满");
                this.wait();
            }
//            Thread.sleep(100); //并不释放锁
            System.out.println(Thread.currentThread().getName()+"生产中");
            resources.add(UUID.randomUUID().toString());
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Deprecated
    public synchronized void popAll(){

        try {
            while(true){
                if(resources.size()==0){
                    System.out.println("生产队列已满");
                    this.wait();
                }else{
                    System.out.println(Thread.currentThread().getName()+"消费中");
                    resources.remove(resources.size()-1);
                    this.notifyAll();
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void pop(){

        try {
            while(resources.size()==0){
                System.out.println("生产队列已满");
                this.wait();
            }
            System.out.println(Thread.currentThread().getName()+"消费中");
            resources.remove(resources.size()-1);
            this.notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

