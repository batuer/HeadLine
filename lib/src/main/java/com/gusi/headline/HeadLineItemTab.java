package com.gusi.headline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by batue on 2017/10/31.
 */

public class HeadLineItemTab extends FrameLayout {
  private HeadLineTabTabPre mTabPre;

  public HeadLineItemTab(@NonNull Context context) {
    super(context);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
  }

  public void clipPercent(float positionOffset, int left) {
    mTabPre.clipPercent(positionOffset, left);
  }

  public void setSelectedChange(boolean selectedChange) {
    mTabPre.setVisibility(selectedChange ? VISIBLE : GONE);
  }

  void addViews(View view, View view1) {
    View viewById = findViewById(R.id.head_line_pre);
    if (viewById == null) {
      addView(view);
      mTabPre = new HeadLineTabTabPre(getContext());
      mTabPre.addView(view1);
      mTabPre.setId(R.id.head_line_pre);
      addView(mTabPre);
      mTabPre.setVisibility(GONE);
    } else {
      mTabPre = (HeadLineTabTabPre) viewById;
    }
  }
}
