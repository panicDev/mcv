package com.panicDev.calendarview.sample.decorators;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.panicDev.calendarview.CalendarDay;
import com.panicDev.calendarview.DayViewDecorator;
import com.panicDev.calendarview.DayViewFacade;
import com.panicDev.calendarview.MaterialCalendarView;
import com.panicDev.calendarview.sample.R;
import java.util.HashSet;

/**
 * Decorate 2 days.
 */
public class RangeDayDecorator implements DayViewDecorator {

  private final HashSet<CalendarDay> list = new HashSet<>();
  private final Drawable drawable;

  public RangeDayDecorator(final Context context) {
    drawable = context.getResources().getDrawable(R.drawable.my_selector);
  }

  @Override
  public boolean shouldDecorate(CalendarDay day) {
    return list.contains(day);
  }

  @Override
  public void decorate(DayViewFacade view) {
    view.setSelectionDrawable(drawable);
  }

  /**
   * We're changing the dates, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
   */
  public void addFirstAndLast(final CalendarDay first, final CalendarDay last) {
    list.clear();
    list.add(first);
    list.add(last);
  }
}
