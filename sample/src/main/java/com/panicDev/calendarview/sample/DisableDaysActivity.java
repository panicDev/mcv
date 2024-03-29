package com.panicDev.calendarview.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.panicDev.calendarview.CalendarDay;
import com.panicDev.calendarview.DayViewDecorator;
import com.panicDev.calendarview.DayViewFacade;
import com.panicDev.calendarview.MaterialCalendarView;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Show off setting min and max dates and disabling individual days
 */
public class DisableDaysActivity extends AppCompatActivity {

  @BindView(R.id.calendarView) MaterialCalendarView widget;

  @Override protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic);
    ButterKnife.bind(this);

    // Add a decorator to disable prime numbered days
    widget.addDecorator(new PrimeDayDisableDecorator());
    // Add a second decorator that explicitly enables days <= 10. This will work because
    // decorators are applied in order, and the system allows re-enabling
    widget.addDecorator(new EnableOneToTenDecorator());

    final LocalDate calendar = LocalDate.now();
    widget.setSelectedDate(calendar);

    final LocalDate min = LocalDate.of(calendar.getYear(), Month.JANUARY, 1);
    final LocalDate max = LocalDate.of(calendar.getYear() + 1, Month.OCTOBER, 31);

    widget.state().edit()
        .setMinimumDate(min)
        .setMaximumDate(max)
        .commit();
  }

  private static class PrimeDayDisableDecorator implements DayViewDecorator {

    @Override public boolean shouldDecorate(final CalendarDay day) {
      return PRIME_TABLE[day.getDay()];
    }

    @Override public void decorate(final DayViewFacade view) {
      view.setDaysDisabled(true);
    }

    private static boolean[] PRIME_TABLE = {
        false,  // 0?
        false,
        true, // 2
        true, // 3
        false,
        true, // 5
        false,
        true, // 7
        false,
        false,
        false,
        true, // 11
        false,
        true, // 13
        false,
        false,
        false,
        true, // 17
        false,
        true, // 19
        false,
        false,
        false,
        true, // 23
        false,
        false,
        false,
        false,
        false,
        true, // 29
        false,
        true, // 31
        false,
        false,
        false, //PADDING
    };
  }

  private static class EnableOneToTenDecorator implements DayViewDecorator {

    @Override
    public boolean shouldDecorate(CalendarDay day) {
      return day.getDay() <= 10;
    }

    @Override
    public void decorate(DayViewFacade view) {
      view.setDaysDisabled(false);
    }
  }
}
