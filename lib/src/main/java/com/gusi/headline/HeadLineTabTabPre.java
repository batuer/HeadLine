package com.gusi.headline;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

/**
 * Created by batue on 2017/10/31.
 */

public class HeadLineTabTabPre extends FrameLayout {
  public static final int LEFT = 0;
  public static final int RIGHT = 1;

  private float mClipPercent = 0;
  private int mDirection = -1;

  public HeadLineTabTabPre(@NonNull Context context) {
    super(context);
  }

  @Override protected void onDraw(Canvas canvas) {
    int width = getWidth();
    int height = getHeight();
    //Log.e("Fire", getText() + ":--:" + mDirection + ":--:" + mClipPercent);
    float left = mDirection == RIGHT ? width * mClipPercent : 0;
    float right = mDirection == RIGHT ? width : width * mClipPercent;
    //在 裁剪出去的画布上绘制
    canvas.save();
    canvas.clipRect(left, 0, right, height);
    super.onDraw(canvas);
    canvas.restore();
  }

  public void clipPercent(float clipPercent, int direction) {
    this.mClipPercent = clipPercent;
    this.mDirection = direction;
    invalidate();
  }
}
