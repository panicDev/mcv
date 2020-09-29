package com.panicDev.calendarview.sample.decorators;

import android.graphics.Typeface;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import com.panicDev.calendarview.CalendarDay;
import com.panicDev.calendarview.DayViewDecorator;
import com.panicDev.calendarview.DayViewFacade;
import com.panicDev.calendarview.MaterialCalendarView;
import org.threeten.bp.LocalDate;

/**
 * Decorate a day by making the text big and bold
 */
public class OneDayDecorator implements DayViewDecorator {

  private CalendarDay date;

  public OneDayDecorator() {
    date = CalendarDay.today();
  }

  @Override
  public boolean shouldDecorate(CalendarDay day) {
    return date != null && day.equals(date);
  }

  @Override
  public void decorate(DayViewFacade view) {
    view.addSpan(new StyleSpan(Typeface.BOLD));
    view.addSpan(new RelativeSizeSpan(1.4f));
  }

  /**
   * We're changing the internals, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
   */
  public void setDate(LocalDate date) {
    this.date = CalendarDay.from(date);
  }
}
