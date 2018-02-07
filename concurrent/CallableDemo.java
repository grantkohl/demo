package demo.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        // TODO Auto-generated method stub
        Thread.sleep(3000);
        return "money";
    }

    public static void main(String[] args){
        //匿名内部类 双大括号初始化
        Callable call=new Callable() {//由大括号创建匿名内部类
            @Override
            public Object call() throws Exception { //实现call方法
                return null;
            }
        };

        CallableDemo demo=new CallableDemo();
        FutureTask<String> task = new FutureTask<>(demo);
        //创建线程
        Thread thread=new Thread(task);
        thread.start();
        System.out.println("id:"+thread.getId()+";name:"+thread.getName()+";priority:"+thread.getPriority()+";state:"+thread.getState());

        try {
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("this is main function");
    }

}

