package com.panicDev.calendarview.sample;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.panicDev.calendarview.CalendarDay;
import com.panicDev.calendarview.MaterialCalendarView;
import com.panicDev.calendarview.OnDateSelectedListener;
import com.panicDev.calendarview.sample.decorators.EventDecorator;
import com.panicDev.calendarview.sample.decorators.HighlightWeekendsDecorator;
import com.panicDev.calendarview.sample.decorators.MySelectorDecorator;
import com.panicDev.calendarview.sample.decorators.OneDayDecorator;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Shows off the most basic usage
 */
public class BasicActivityDecorated extends AppCompatActivity implements OnDateSelectedListener {

  private final OneDayDecorator oneDayDecorator = new OneDayDecorator();

  @BindView(R.id.calendarView)
  MaterialCalendarView widget;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic);
    ButterKnife.bind(this);

    widget.setOnDateChangedListener(this);
    widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

    final LocalDate instance = LocalDate.now();
    widget.setSelectedDate(instance);

    final LocalDate min = LocalDate.of(instance.getYear(), Month.JANUARY, 1);
    final LocalDate max = LocalDate.of(instance.getYear(), Month.DECEMBER, 31);

    widget.state().edit().setMinimumDate(min).setMaximumDate(max).commit();

    widget.addDecorators(
        new MySelectorDecorator(this),
        new HighlightWeekendsDecorator(),
        oneDayDecorator
    );

    new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());
  }

  @Override
  public void onDateSelected(
      @NonNull MaterialCalendarView widget,
      @NonNull CalendarDay date,
      boolean selected) {
    //If you change a decorate, you need to invalidate decorators
    oneDayDecorator.setDate(date.getDate());
    widget.invalidateDecorators();
  }

  /**
   * Simulate an API call to show how to add decorators
   */
  private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

    @Override
    protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      LocalDate temp = LocalDate.now().minusMonths(2);
      final ArrayList<CalendarDay> dates = new ArrayList<>();
      for (int i = 0; i < 30; i++) {
        final CalendarDay day = CalendarDay.from(temp);
        dates.add(day);
        temp = temp.plusDays(5);
      }

      return dates;
    }

    @Override
    protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
      super.onPostExecute(calendarDays);

      if (isFinishing()) {
        return;
      }

      widget.addDecorator(new EventDecorator(Color.RED, calendarDays));
    }
  }
}
