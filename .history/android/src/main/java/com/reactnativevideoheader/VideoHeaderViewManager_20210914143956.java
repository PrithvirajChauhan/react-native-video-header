package com.reactnativevideoheader;

import android.app.Activity;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.VideoView;


import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.yqritc.scalablevideoview.ScalableType;

import java.util.Map;

public class VideoHeaderViewManager extends SimpleViewManager<VideoModule> implements ReactContextBaseJavaModule  {
    public static final String REACT_CLASS = "VideoHeaderView";
  public static final String PROP_SRC = "src";
  public static final String PROP_SRC_URI = "uri";
  public static final String PROP_SRC_TYPE = "type";
  public static final String PROP_SRC_HEADERS = "requestHeaders";
  public static final String PROP_SRC_IS_NETWORK = "isNetwork";
  public static final String PROP_SRC_MAINVER = "mainVer";
  public static final String PROP_SRC_PATCHVER = "patchVer";
  public static final String PROP_SRC_IS_ASSET = "isAsset";
  public static final String PROP_RESIZE_MODE = "resizeMode";
  public static final String PROP_REPEAT = "repeat";
  public static final String PROP_PAUSED = "paused";
  public static final String PROP_MUTED = "muted";
  public static final String PROP_PREVENTS_DISPLAY_SLEEP_DURING_VIDEO_PLAYBACK = "preventsDisplaySleepDuringVideoPlayback";
  public static final String PROP_VOLUME = "volume";
  public static final String PROP_STEREO_PAN = "stereoPan";
  public static final String PROP_PROGRESS_UPDATE_INTERVAL = "progressUpdateInterval";
  public static final String PROP_SEEK = "seek";
  public static final String PROP_RATE = "rate";
  public static final String PROP_FULLSCREEN = "fullscreen";
  public static final String PROP_PLAY_IN_BACKGROUND = "playInBackground";
  public static final String PROP_CONTROLS = "controls";
  private Activity mActivity = null;
  public VideoHeaderViewManager(Activity activity) {
    mActivity = activity;
  }

  @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    @NonNull
    public VideoModule createViewInstance(ThemedReactContext reactContext) {
      return new VideoModule(reactContext,mActivity);
    }


  @Override
  public void onDropViewInstance(VideoModule view) {
    super.onDropViewInstance(view);
    view.cleanupMediaPlayerResources();
  }

  @Override
  @Nullable
  public Map getExportedCustomDirectEventTypeConstants() {
    MapBuilder.Builder builder = MapBuilder.builder();
    for (VideoModule.Events event : VideoModule.Events.values()) {
      builder.put(event.toString(), MapBuilder.of("registrationName", event.toString()));
    }
    return builder.build();
  }


  @Override
  @Nullable
  public Map getExportedViewConstants() {
    return MapBuilder.of(
      "ScaleNone", Integer.toString(ScalableType.LEFT_TOP.ordinal()),
      "ScaleToFill", Integer.toString(ScalableType.FIT_XY.ordinal()),
      "ScaleAspectFit", Integer.toString(ScalableType.FIT_CENTER.ordinal()),
      "ScaleAspectFill", Integer.toString(ScalableType.CENTER_CROP.ordinal())
    );
  }

  @ReactProp(name = PROP_SRC)
  public void setSrc(final VideoModule videoView, @Nullable ReadableMap src) {
    int mainVer = src.getInt(PROP_SRC_MAINVER);
    int patchVer = src.getInt(PROP_SRC_PATCHVER);
    if(mainVer<0) { mainVer = 0; }
    if(patchVer<0) { patchVer = 0; }
    if(mainVer>0) {
      videoView.setSrc(
        src.getString(PROP_SRC_URI),
        src.getString(PROP_SRC_TYPE),
        src.getBoolean(PROP_SRC_IS_NETWORK),
        src.getBoolean(PROP_SRC_IS_ASSET),
        src.getMap(PROP_SRC_HEADERS),
        mainVer,
        patchVer
      );
    }
    else {
      videoView.setSrc(
        src.getString(PROP_SRC_URI),
        src.getString(PROP_SRC_TYPE),
        src.getBoolean(PROP_SRC_IS_NETWORK),
        src.getBoolean(PROP_SRC_IS_ASSET),
        src.getMap(PROP_SRC_HEADERS)
      );
    }
  }

  @ReactProp(name = PROP_PREVENTS_DISPLAY_SLEEP_DURING_VIDEO_PLAYBACK)
  public void setPropPreventsDisplaySleepDuringVideoPlayback(final VideoModule videoView, final boolean doPreventSleep) {
    videoView.setPreventsDisplaySleepDuringVideoPlaybackModifier(doPreventSleep);
  }

  @ReactProp(name = PROP_RESIZE_MODE)
  public void setResizeMode(final VideoModule videoView, final String resizeModeOrdinalString) {
    videoView.setResizeModeModifier(ScalableType.values()[Integer.parseInt(resizeModeOrdinalString)]);
  }

  @ReactProp(name = PROP_REPEAT, defaultBoolean = false)
  public void setRepeat(final VideoModule videoView, final boolean repeat) {
    videoView.setRepeatModifier(repeat);
  }

  @ReactProp(name = PROP_PAUSED, defaultBoolean = false)
  public void setPaused(final VideoModule videoView, final boolean paused) {
    videoView.setPausedModifier(paused);
  }

  @ReactProp(name = PROP_MUTED, defaultBoolean = false)
  public void setMuted(final VideoModule videoView, final boolean muted) {
    videoView.setMutedModifier(muted);
  }

  @ReactProp(name = PROP_VOLUME, defaultFloat = 1.0f)
  public void setVolume(final VideoModule videoView, final float volume) {
    videoView.setVolumeModifier(volume);
  }

  @ReactProp(name = PROP_STEREO_PAN)
  public void setStereoPan(final VideoModule videoView, final float stereoPan) {
    videoView.setStereoPan(stereoPan);
  }

  @ReactProp(name = PROP_PROGRESS_UPDATE_INTERVAL, defaultFloat = 250.0f)
  public void setProgressUpdateInterval(final VideoModule videoView, final float progressUpdateInterval) {
    videoView.setProgressUpdateInterval(progressUpdateInterval);
  }

  @ReactProp(name = PROP_SEEK)
  public void setSeek(final VideoModule videoView, final float seek) {
    videoView.seekTo(Math.round(seek * 1000.0f));
  }

  @ReactProp(name = PROP_RATE)
  public void setRate(final VideoModule videoView, final float rate) {
    videoView.setRateModifier(rate);
  }

  @ReactProp(name = PROP_FULLSCREEN, defaultBoolean = false)
  public void setFullscreen(final VideoModule videoView, final boolean fullscreen) {
    videoView.setFullscreen(fullscreen);
  }

  @ReactProp(name = PROP_PLAY_IN_BACKGROUND, defaultBoolean = false)
  public void setPlayInBackground(final VideoModule videoView, final boolean playInBackground) {
    videoView.setPlayInBackground(playInBackground);
  }

  @ReactProp(name = PROP_CONTROLS, defaultBoolean = false)
  public void setControls(final VideoModule videoView, final boolean controls) {
    videoView.setControls(controls);
  }


}
