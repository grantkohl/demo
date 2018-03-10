package demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lee on 2018/3/7.
 */
public class PoolDemo {
    static ThreadLocal<Long> platformId = new ThreadLocal<Long>();

    public static Long num=1L;

    public static Long notnum=1L;

    public static void setPlatformId(Long pid) {
        platformId.set(pid);
    }

    public static Long getPlatformId() {
        return platformId.get();
    }

    public static void main(String[] args) throws InterruptedException {
        PoolDemo.setPlatformId(PoolDemo.num);
        System.out.println("id before:"+PoolDemo.getPlatformId());
        PoolDemo.platformId=null;
//        Thread.currentThread().sleep(4000);
        PoolDemo.platformId=new ThreadLocal<Long>();;
        System.out.println("id after:"+PoolDemo.getPlatformId());//测试弱引用,key被回收后,value是否被回收

        PoolDemo.setPlatformId(PoolDemo.num);
        System.out.println("id before:"+PoolDemo.getPlatformId());
        PoolDemo.platformId.remove();
//        Thread.currentThread().sleep(4000);
        PoolDemo.platformId=new ThreadLocal<Long>();;
        System.out.println("id after:"+PoolDemo.getPlatformId());//测试remove方法

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS ,
                new ArrayBlockingQueue<Runnable>(5));

        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);

//            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
//                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}

class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
//        System.out.println("正在执行task "+taskNum);
        try {
            System.out.println("当前线程id："+Thread.currentThread().getId());
            if(null==PoolDemo.getPlatformId()){
                PoolDemo.setPlatformId(PoolDemo.num);
                System.out.println("==============>> 第"+PoolDemo.num+"次进入"+Thread.currentThread().getId()+";"+PoolDemo.getPlatformId());
                PoolDemo.num++;
            }else{
                System.out.println("++++++++++++++>> 第"+PoolDemo.notnum+"次未进入"+Thread.currentThread().getId()+";"+PoolDemo.getPlatformId());
                PoolDemo.notnum++;
            }


            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("task "+taskNum+"执行完毕");
    }
}