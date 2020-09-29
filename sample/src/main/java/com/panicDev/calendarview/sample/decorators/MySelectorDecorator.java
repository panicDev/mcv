package com.panicDev.calendarview.sample.decorators;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import com.panicDev.calendarview.CalendarDay;
import com.panicDev.calendarview.DayViewDecorator;
import com.panicDev.calendarview.DayViewFacade;
import com.panicDev.calendarview.sample.R;

/**
 * Use a custom selector
 */
public class MySelectorDecorator implements DayViewDecorator {

  private final Drawable drawable;

  public MySelectorDecorator(Activity context) {
    drawable = context.getResources().getDrawable(R.drawable.my_selector);
  }

  @Override
  public boolean shouldDecorate(CalendarDay day) {
    return true;
  }

  @Override
  public void decorate(DayViewFacade view) {
    view.setSelectionDrawable(drawable);
  }
}
