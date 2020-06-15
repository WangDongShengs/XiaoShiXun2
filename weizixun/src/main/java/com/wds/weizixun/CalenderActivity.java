package com.wds.weizixun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.wds.base.BaseActivity;
import com.wds.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalenderActivity extends BaseActivity {
    @BindView(R.id.cl_mcv)
    MaterialCalendarView clMcv;
    @BindView(R.id.cl_login)
    Button clLogin;
    private String newDate;

    @Override
    protected int getLayout() {
        return R.layout.activity_calender;
    }

    @Override
    protected void initView() {
        super.initView();

        clMcv.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2018,8,11))
                .setMaximumDate(CalendarDay.from(DateUtils.getCurrentYear(),DateUtils.getCurrentMonth(),DateUtils.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        clMcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                newDate = simpleDateFormat.format(date.getCalendar().getTime());

                /*int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDay();
                String  newMonth=month>9?0+""+month:month+"";
                String newDay=day>9? 0+""+month:month+"";
                CalenderActivity.this.newDate = year+""+newMonth+newDay;*/
            }
        });
    }

    @OnClick(R.id.cl_login)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.putExtra("date",newDate);
        setResult(200,intent);
        finish();
    }
}
