package com.gusi.headline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;

/**
 * Created by batue on 2017/10/31.
 *
 * @author LC
 */

public class HeadLineTabTabPre extends LinearLayout {
  public static final int LEFT = 0;
  public static final int RIGHT = 1;

  private float mClipPercent = 0;
  private int mDirection = -1;

  private boolean mIsClip = false;

  public HeadLineTabTabPre(@NonNull Context context) {
    super(context);
  }

  /**
   * 不会调用onDraw（） 使用dispatchDraw（）
   */
  @Override protected void dispatchDraw(Canvas canvas) {
    if (!mIsClip) {
      super.dispatchDraw(canvas);
    } else {
      int width = getWidth();
      int height = getHeight();

      float left = mDirection == RIGHT ? width * mClipPercent : 0;
      float right = mDirection == RIGHT ? width : width * mClipPercent;
      //在 裁剪出去的画布上绘制
      canvas.save();
      canvas.clipRect(left, 0, right, height);
      canvas.drawColor(Color.parseColor("#00000000"));
      super.dispatchDraw(canvas);
      canvas.restore();
    }
  }

  public void clipPercent(float clipPercent, int direction) {
    mIsClip = true;
    if (getVisibility() != VISIBLE) {
      setVisibility(VISIBLE);
    }
    this.mClipPercent = clipPercent;
    this.mDirection = direction;
    invalidate();
  }

  @Override public void setVisibility(int visibility) {
    this.mIsClip = false;
    super.setVisibility(visibility);
  }
}
