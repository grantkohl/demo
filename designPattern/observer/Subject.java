package demo.designPattern.observer;

import java.util.Observable;

public class Subject extends Observable {

    private int count=0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        //修改标识状态
        super.setChanged();
    }

}
