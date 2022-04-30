package com.yeeee.crowdfunding.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 23:35
 */
public class DateConvertUtil {

    public static String getLeftDays(long totalDays, Date from, Date to){
        long daysTimes = TimeUnit.DAYS.toDays(totalDays);
        long times = to.getTime() - from.getTime();
        long leftTimes = daysTimes - times;
        if(leftTimes <= 0){
            return "0天";
        }
        if(leftTimes < 60*1000){
            return new Double(Math.floor(leftTimes / (1000.0))).intValue() + "秒";
        }else if(leftTimes <60*60*1000){
            return new Double(Math.floor(leftTimes / (60*1000.0))).intValue() + "分钟";
        }else if(leftTimes<24*60*60*1000){
            return new Double(Math.floor(leftTimes/(60*60*1000.0))).intValue() + "小时";
        }
        return new Double(Math.floor(leftTimes / (24*60*60*1000.0))).intValue() + "天";
    }

    //计算距离现在多久
    public static String timeToNow(Date date) {
        Date current = new Date();
        Long times = current.getTime() - date.getTime();

        if(times>0&&times<60*1000){
            return new Double(Math.floor(times/(1000.0))).intValue()+"秒前";
        }else if(times>=60*1000&&times<60*60*1000){
            return new Double(Math.floor(times/(60*1000.0))).intValue()+"分钟前";
        }else if(times>=60*60*1000&&times<24*60*60*1000){
            return new Double(Math.floor(times/(60*60*1000.0))).intValue()+"小时前";
        }/*else if(times>=24*60*60*1000&&times<200*24*60*60*1000){*/
        return new Double(Math.floor(times/(24*60*60*1000.0))).intValue()+"天前";
		/*}else{
			return dateTime.split(" ")[0];
		}*/

    }

}
