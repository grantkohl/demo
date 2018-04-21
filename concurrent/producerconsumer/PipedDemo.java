package demo.concurrent.producerconsumer;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Random;

public class PipedDemo {

    public static void main(String[] args) throws InterruptedException {
        Resource resource = new PipedResource(new PipedInputStream(),new PipedOutputStream());

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

class PipedResource implements Resource{

    PipedInputStream inputStream ;
    PipedOutputStream outputStream ;

    PipedResource(PipedInputStream inputStream,PipedOutputStream outputStream){
        try {
            this.inputStream=inputStream;
            this.outputStream=outputStream;
            this.outputStream.connect(this.inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void push() {

        try {
            Random rand = new Random();
            int a = rand.nextInt(100);
            System.out.println(Thread.currentThread().getName()+"生产内容："+a);
            outputStream.write(a);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void pop() {

        try {
            int b=inputStream.read();
            System.out.println(Thread.currentThread().getName()+"消费内容："+b);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
