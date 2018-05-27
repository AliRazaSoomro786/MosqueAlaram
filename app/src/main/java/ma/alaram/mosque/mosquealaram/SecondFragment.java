package ma.alaram.mosque.mosquealaram;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

import ma.alaram.mosque.mosquealaram.COMMON.UnitOfwork;
import ma.alaram.mosque.mosquealaram.DTO.HijriCalendar;

public class SecondFragment extends Fragment
{
    // Store instance variables
    // newInstance constructor for creating fragment with arguments
    public static SecondFragment newInstance()
    {
        SecondFragment secondFragment  = new SecondFragment();
        Bundle args = new Bundle();
        secondFragment.setArguments(args);
        return secondFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_secon, container, false);
        final HijriCalendar hijriCalendar=new HijriCalendar();
        CalendarView calendarView=(CalendarView)view.findViewById(R.id.calendar);
        final TextView DanteChanger=(TextView)view.findViewById(R.id.DanteChanger);
        final Calendar calendar=Calendar.getInstance();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                double day=dayOfMonth;
                double Month =month;
                double Year =year;
                double weekday = calendar.get(Calendar.DAY_OF_WEEK);
                String Date=hijriCalendar.getSimpleDate(Year,Month,weekday,day);
                String date="";
                for(int i=0;i<Date.length();i++)
                {
                    if(Date.charAt(i)==',')
                    {
                        for(int j=i+1;j<Date.length();j++)
                        {
                            date+=Date.charAt(j);
                        }
                    }

                }

                DanteChanger.setText(date);
            }
        });

        TextView text=(TextView)view.findViewById(R.id.textView);
        double day =calendar.get(Calendar.DAY_OF_MONTH);
        double month =calendar.get(Calendar.MONTH);
        double year =calendar.get(Calendar.YEAR);
        double weekday = calendar.get(Calendar.DAY_OF_WEEK);
        text.setText(hijriCalendar.getSimpleDate(year,month,weekday,day));
        return view;
    }

}