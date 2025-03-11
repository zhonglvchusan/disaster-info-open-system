package wang.zehui.disasterinfoopensystem.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: wangxiaobai
 * @date: 2022/4/11 17:28
 * @Description: 包含两个方法，将时间戳转化成时间，以及将时间转化为时间戳
 */
public class DateTransformationUtils {

    public static String dateToStamp(String s) throws Exception {

        String res = null;
        //设置时间格式，将该格式的时间转化为时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = simpleDateFormat.parse(s);
        long time = date.getTime();
        res = String.valueOf(time);

        return res;
    }

    public static String stamptoDate(String s) throws Exception {
        String res = null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long it = new Long(s);
        //将时间戳转化为时间
        Date date = new Date(it);
        //将时间戳调整为规定格式
        res = simpleDateFormat.format(date);

        return res;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(stamptoDate("1649670065000"));
        System.out.println(dateToStamp("2022-04-11 00:00:00"));
        System.out.println(dateToStamp("2022-04-12 00:00:00"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(1649670065000L);
        Date old = sdf.parse(sdf.format(date));
        Date now = sdf.parse(sdf.format(new Date()));
        long oldTime = old.getTime();
        long nowTime = now.getTime();

        System.out.println(oldTime);
        System.out.println(nowTime);
        System.out.println(nowTime - 86400000);

    }
}
