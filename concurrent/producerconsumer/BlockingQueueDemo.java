package demo.concurrent.producerconsumer;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Resource resource = new BlockingResource();

        Thread produceThreadA = new ProduceThread(resource,"producerA");
        Thread produceThreadB = new ProduceThread(resource,"producerB");
        Thread produceThreadC = new ProduceThread(resource,"producerC");
        Thread produceThreadD = new ProduceThread(resource,"producerD");

        produceThreadA.start();
        produceThreadB.start();
        produceThreadC.start();
        produceThreadD.start();

        Thread.sleep(5000);

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

class BlockingResource implements Resource{
    private BlockingQueue resourceQueue = new LinkedBlockingQueue(10);

    @Override
    public void push() {
        try {
            System.out.println(Thread.currentThread().getName()+"生产中");
            resourceQueue.put(UUID.randomUUID());
            System.out.println("资源池 size："+resourceQueue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void pop() {
        try {
            System.out.println(Thread.currentThread().getName()+"消费中");
            resourceQueue.take();
            System.out.println("资源池 size："+resourceQueue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

