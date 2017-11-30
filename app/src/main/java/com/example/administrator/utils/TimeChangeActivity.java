package com.example.administrator.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class TimeChangeActivity extends Activity {

    int mYear, mMonth, mDay;
    private Button btn;
    private TextView timeShow;
    final int DATE_DIALOG  = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_change);

        btn = (Button) findViewById(R.id.dateChoose);
        timeShow = (TextView) findViewById(R.id.dateDisplay);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });

        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DATE);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_DIALOG:
                return new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                        timeShow.setText(new StringBuffer().append(mMonth + 1).append("-").append(mDay).append("-").append(mYear).append(" "));
                    }
                }, mYear, mMonth, mDay);
        }
        return null;
    }
    /**
     * 设置日期,利用StringBuffer追加
     */
//    public void display(){
//
//    }

//    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            mYear = year;
//            mMonth = month;
//            mDay = dayOfMonth;
//            display();
//        }
//    };
}
