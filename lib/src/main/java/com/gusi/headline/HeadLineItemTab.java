package com.gusi.headline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
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
    int visibility = mTabPre.getVisibility();
    if (selectedChange) {
      if (visibility == GONE) {
        mTabPre.setVisibility(VISIBLE);
      }
    } else {
      if (visibility == VISIBLE) {
        mTabPre.setVisibility(GONE);
      }
    }
  }

  void addViews(View view, View view1) {
    View viewById = findViewById(R.id.head_line_pre);
    if (viewById == null) {
      LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.MATCH_PARENT);
      view.setLayoutParams(params);
      view1.setLayoutParams(params);

      addView(view);
      mTabPre = new HeadLineTabTabPre(getContext());

      mTabPre.setLayoutParams(params);

      mTabPre.addView(view1);
      mTabPre.setId(R.id.head_line_pre);
      addView(mTabPre);
      mTabPre.setVisibility(GONE);
    } else {
      mTabPre = (HeadLineTabTabPre) viewById;
    }
  }
}
