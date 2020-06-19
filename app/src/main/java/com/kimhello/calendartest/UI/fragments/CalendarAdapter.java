package com.kimhello.calendartest.UI.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kimhello.calendartest.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarAdapter extends ArrayAdapter<Date> {

    private final LayoutInflater inflater;

    public CalendarAdapter(Context context, ArrayList<Date> days) {
        super(context,R.layout.item_calendar_gridview, days);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder = null;

        // day in question
        Date date = getItem(position);
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();

        // today
        Date today = new Date();

        // inflate item
        if (view == null) {
            view = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
            holder = new ViewHolder();

            holder.tvItemGridView = (TextView) view.findViewById(R.id.tv_item_gridview);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }


        holder.tvItemGridView.setTypeface(null, Typeface.NORMAL);
        holder.tvItemGridView.setTextColor(Color.BLACK);

        if (month != today.getMonth() || year != today.getYear()) {
            holder.tvItemGridView.setTextColor(Color.GRAY);
        }
        else if (day == today.getDate()) {
            holder.tvItemGridView.setTextColor(parent.getResources().getColor(R.color.today));
            holder.tvItemGridView.setBackgroundColor(parent.getResources().getColor(R.color.today_bg));
        }

        // set text
        holder.tvItemGridView.setText(String.valueOf(date.getDate()));

        return view;
    }
}
