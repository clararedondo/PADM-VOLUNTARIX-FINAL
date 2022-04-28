package voluntarix.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;

import java.sql.Time;


public class CalendarActivity extends AppCompatActivity {

    CustomCalendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = (CustomCalendar) findViewById(R.id.calendar);

        calendar.setCallBack(new CustomCalendar.DayClickListener() {
            @Override
            public void onDayClick(View view) {
                Button selectedDayButton = (Button) view;
                if (selectedDayButton.getTag() != null) {
                    int[] dateArray = (int[]) selectedDayButton.getTag();
                    int day = dateArray[0];
                    int month = dateArray[1];
                    int year = dateArray[2];

                    //TODO consulta base de datos
                }
            }
        });

    }
}