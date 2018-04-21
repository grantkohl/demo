package demo.concurrent.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionDemo {

    public static void main(String[] args) throws InterruptedException {

        Resource resources=new LockConditionResource();

        Thread produceThreadA = new ProduceThread(resources,"producerA");
        Thread produceThreadB = new ProduceThread(resources,"producerB");
        Thread produceThreadC = new ProduceThread(resources,"producerC");
        Thread produceThreadD = new ProduceThread(resources,"producerD");

        produceThreadA.start();
        produceThreadB.start();
        produceThreadC.start();
        produceThreadD.start();

        Thread.sleep(10000);

        Thread consumeThreadA = new ConsumeThread(resources,"consumerA");
        Thread consumeThreadB = new ConsumeThread(resources,"consumerB");
        Thread consumeThreadC = new ConsumeThread(resources,"consumerC");
        Thread consumeThreadD = new ConsumeThread(resources,"consumerD");

        consumeThreadA.start();
        consumeThreadB.start();
        consumeThreadC.start();
        consumeThreadD.start();

    }

}

class LockConditionResource implements Resource{

    private List list=new ArrayList();

    private Lock lock =new ReentrantLock();
    private Condition produceCondition = lock.newCondition();
    private Condition consumeCondition = lock.newCondition();

    @Override
    public void pop(){
        lock.lock();

        try {
            while(list.size()==0){
                System.out.println("生产队列已空");
                consumeCondition.await();
            }
            System.out.println(Thread.currentThread().getName()+"消费中:"+list.get(list.size()-1));
            list.remove(list.size()-1);
            produceCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void push(){
        lock.lock();
        try {
            while(list.size()>1){
                System.out.println("生产队列已满");
                produceCondition.await();
            }
            System.out.println(Thread.currentThread().getName()+"生产中");
            list.add(UUID.randomUUID().toString());
            consumeCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

