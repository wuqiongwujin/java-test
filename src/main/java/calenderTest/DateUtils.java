package calenderTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Cain
 * @Package calenderTest
 * @Description
 * @date 2018/9/6
 */
public class DateUtils {

    public static java.util.Date addMonth(java.util.Date date, int month) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int year){
        if(date == null){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static void main(String[] args) throws Exception {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        String startTimeStr = "2018-02-28 15:30:01";
        Date startTime = new SimpleDateFormat(dateFormat).parse(startTimeStr);
        System.out.println("开始时间:" + new SimpleDateFormat(dateFormat).format(startTime));
//        Date endTime = addMonth(startTime, 1);
//        System.out.println("结束时间:" + new SimpleDateFormat(dateFormat).format(endTime));

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String str="20180129";
//        Date dt=sdf.parse(str);
//        Calendar rightNow = Calendar.getInstance();
//        rightNow.setTime(dt);
//        rightNow.add(Calendar.MONTH,1);//日期加3个月
//        Date dt1=rightNow.getTime();
//        String reStr = sdf.format(dt1);
//        System.out.println(reStr);

        Date endTime = addYear(startTime, 1);
        System.out.println("结束时间:" + new SimpleDateFormat(dateFormat).format(endTime));


    }
}
