package com.wds.utils;

import java.util.Calendar;

public class DateUtils {

    /**
     * 获取当前年
     * @return
     */
    public static int getCurrentYear() {
        Calendar cal=Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     * @return
     */
    public static int getCurrentMonth() {
        Calendar cal=Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    /**
     * 获取当前日
     * @return
     */
    public static int getCurrentDay() {
        Calendar cal=Calendar.getInstance();
        return cal.get(Calendar.DATE);
    }

}
