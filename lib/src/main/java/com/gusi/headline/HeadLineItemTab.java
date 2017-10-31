package com.gusi.headline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

/**
 * Created by batue on 2017/10/31.
 */

public class HeadLineItemTab extends FrameLayout {
  private static final int TAB_PRE_TAG = 100;
  private boolean mSelectedChange;

  public HeadLineItemTab(@NonNull Context context) {
    super(context);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    HeadLineTabTabPre tabPre = (HeadLineTabTabPre) findViewWithTag(TAB_PRE_TAG);

  }

  public void clipPercent(float positionOffset, int left) {

  }

  public void setSelectedChange(boolean selectedChange) {
    mSelectedChange = selectedChange;
  }
}
