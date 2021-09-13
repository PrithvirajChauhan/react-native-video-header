package com.reactnativevideoheader;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CalendarModule  extends ReactContextBaseJavaModule {
  public CalendarModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "CalendarModule";
  }

  @ReactMethod
  public void createCalendarEvent(String name, String location) {
    Log.d("CalendarModule", "Create event called with name: " + name
      + " and location: " + location);
  }
}
