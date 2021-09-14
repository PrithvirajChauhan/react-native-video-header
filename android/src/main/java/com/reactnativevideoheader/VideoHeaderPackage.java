package com.reactnativevideoheader;

import android.app.Activity;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VideoHeaderPackage implements ReactPackage {

  private Activity mActivity = null;

  public VideoHeaderPackage(Activity activity) {
    mActivity = activity;
  }
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
//      List<NativeModule> modules = new ArrayList<>();
//
//      modules.add(new VideoModule(reactContext));
//
//      return modules;
      return Collections.emptyList();
    }

  @Override
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return null;
  }

  @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(new VideoHeaderViewManager(mActivity));
    }


}
