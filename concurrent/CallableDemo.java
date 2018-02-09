package demo.concurrent;

import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

public class CallableDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        // TODO Auto-generated method stub
        Thread.sleep(3000);
        return "money";
    }

    public static void main(String[] args){
//        //匿名内部类 双大括号初始化
//        Callable call=new Callable() {//由大括号创建匿名内部类
//            @Override
//            public Object call() throws Exception { //实现call方法
//                return null;
//            }
//        };

//        CallableDemo demo=new CallableDemo();
//        FutureTask<String> task = new FutureTask<>(demo);
//        //创建线程
//        Thread thread=new Thread(task);
//        thread.start();
//        System.out.println("id:"+thread.getId()+";name:"+thread.getName()+";priority:"+thread.getPriority()+";state:"+thread.getState());
//
//        try {
//            System.out.println(task.get());
//        } catch (InterruptedException | ExecutionException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

//        ExecutorService threadPool= Executors.newSingleThreadExecutor();
//        Future<String> future = threadPool.submit(
//            new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    Thread.sleep(1000);
//                    return "MONEY";
//                }
//            }
//        );
//
//        try {
//            System.out.println("I WANNA "+future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        ExecutorService threadPool = Executors.newCachedThreadPool();
//        CompletionService<Integer> cs=new ExecutorCompletionService<>(threadPool);
//
//        for(int i=1;i<=5;i++){
//            final int num=i;
//            cs.submit(new Callable<Integer>() {
//                @Override
//                public Integer call() throws Exception {
//                    Thread.sleep((6-num)*1000);
//                    return num;
//                }
//            });
//        }
//
//        try {
//            for(int i=0;i<10;i++){
//                System.out.println(cs.take().get());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Long>> results = null;
        try {
            results = executor.invokeAll(asList(
                    getCallables()
            ));
            executor.shutdown();

            for (Future<Long> result : results) {
                System.out.println(result.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println("this is main function");
    }

    private static Callable<Long>[] getCallables(){
        Callable[] cs=new Callable[5];
        for(int i=1;i<=5;i++){
            final int num=i;
            cs[i-1]=new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep((6-num)*1000);
                    return num;
                }
            };
        }
        return cs;
    }
}

