package com.panicDev.calendarview.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.panicDev.calendarview.MaterialCalendarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomizeXmlActivity extends AppCompatActivity {

  @BindView(R.id.calendarView)
  MaterialCalendarView widget;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customization);
    ButterKnife.bind(this);
  }
}
