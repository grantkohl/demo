package demo.designPattern.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverImpl implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update.......");
        //如果arg不为null,被观察者对象向观察者推送主题的详细信息
        System.out.println(arg);
        //从主题对象中"拉取"所需要的数据
        Subject sub=(Subject)o;
        System.out.println(sub.getCount());
    }
}
