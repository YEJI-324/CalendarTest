package com.kimhello.calendartest.UI.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kimhello.calendartest.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends Fragment {
    View view;

    private TextView txtDate; // month + year
    private ImageButton btnPrev;
    private ImageButton btnNext;
    private GridView grid;

    private static final int DAYS_COUNT = 42;
    private static final String DATE_FORMAT = "MMM YYYY";
    private static Calendar currentDate = Calendar.getInstance(); //현재 날짜
    private static int realMonth = currentDate.get(Calendar.MONTH);
    private static int realYear = currentDate.get(Calendar.YEAR);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate calendar fragment
        view = inflater.inflate(R.layout.calendar_fragment,container,false);

        txtDate = (TextView)view.findViewById(R.id.text_date_calendar);
        grid = (GridView)view.findViewById(R.id.grid_calendar);
        btnPrev = (ImageButton)view.findViewById(R.id.btn_prev_calendar);
        btnNext = (ImageButton)view.findViewById(R.id.btn_next_calendar);

        updateCalendar();
        Log.d("current date", currentDate.toString());
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDate.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDate.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });

        return view;
    }

    private void updateCalendar() {
        ArrayList<Date> cells = new ArrayList<>();
        Calendar mCal = (Calendar) currentDate.clone();

        // current month's beginning
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = mCal.get(Calendar.DAY_OF_WEEK) -1;

        // 이전달 날짜 채우기
        mCal.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        //fill cells
        while (cells.size()<DAYS_COUNT) {
            cells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH,1); //여기서 다음달까지 채우면서 다음 달으로 넘어가는듯
        }

        //update grid
        grid.setAdapter(new CalendarAdapter(getContext(), cells));

        mCal.set(Calendar.MONTH, currentDate.get(Calendar.MONTH));
        //update title
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String curDateFormat = sdf.format(mCal.getTime());
        txtDate.setText(curDateFormat);
    }

    public static Date getSelectedDate(){ // current Date
        Date date = currentDate.getTime();
        return date;
    }

    public static int getRealMonth() {
        return realMonth;
    }

    public static int getRealYear() {
        return realYear;
    }
}

