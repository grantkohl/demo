package demo.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lee on 2018/2/9.
 */
public class CalendarDemo {
    public static void main(String[] args){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Calendar calendar = Calendar.getInstance();
         // 设置时间，不设置的话，默认是当前时间
        calendar.setTime(new Date());
        // 获取时间中的年份
        int year = calendar.get(Calendar.YEAR);

//        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        //一周第一天是否为星期天
        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
        if(isFirstSunday){
            calendar.set(Calendar.DAY_OF_WEEK,calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
            calendar.add(Calendar.DATE, 1);
        }else{
            calendar.set(Calendar.DAY_OF_WEEK,calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
        }
        System.out.println(format.format(calendar.getTime()));
    }
}
