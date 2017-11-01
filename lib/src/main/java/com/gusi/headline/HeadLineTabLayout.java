package com.gusi.headline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by batue on 2017/10/31.
 */

public class HeadLineTabLayout extends TabLayout {
  private static final double MIN_SCROLL = 0.02;
  private ViewPager mViewPager;
  private int mBgLayout;
  private int mPreLayout;

  public HeadLineTabLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public HeadLineTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HeadLineTabLayout);
    try {
      mBgLayout = a.getInt(R.styleable.HeadLineTabLayout_tab_item_bg, -1);
      mPreLayout = a.getInt(R.styleable.HeadLineTabLayout_tab_item_pre, -1);
    } finally {
      a.recycle();
    }
  }

  //@Override public void setupWithViewPager(@Nullable ViewPager viewPager) {
  //  super.setupWithViewPager(viewPager);
  //  setupPager(viewPager);
  //}

  @Override public void setupWithViewPager(@Nullable ViewPager viewPager, boolean autoRefresh) {
    super.setupWithViewPager(viewPager, autoRefresh);
    setupPager(viewPager);
  }

  private void setupPager(@Nullable ViewPager viewPager) {
    if (mViewPager == null) {
      mViewPager = viewPager;
      mViewPager.addOnPageChangeListener(new PagerListener());

      Context context = getContext();
      LayoutInflater inflater = LayoutInflater.from(context);

      int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
      LayoutParams params = new LayoutParams(matchParent, matchParent, Gravity.CENTER);
      PagerAdapter adapter = viewPager.getAdapter();
      int count = adapter.getCount();
      for (int i = 0; i < count; i++) {
        TabLayout.Tab tab = getTabAt(i);
        HeadLineItemTab itemTab = new HeadLineItemTab(context);
        if (mBgLayout == -1 || mPreLayout == -1) {
          CharSequence title = adapter.getPageTitle(i);
          TextView tvBg = new TextView(context);
          tvBg.setTextColor(Color.BLACK);
          tvBg.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
          tvBg.setText(title);
          tvBg.setGravity(Gravity.CENTER);
          tvBg.setLayoutParams(params);

          TextView tvPre = new TextView(context);
          tvPre.setTextColor(Color.parseColor("#0fffff"));
          tvPre.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
          tvPre.setText(title);
          tvPre.setGravity(Gravity.CENTER);
          tvPre.setLayoutParams(params);

          itemTab.addViews(tvBg, tvPre);
        } else {
          itemTab.addViews(inflater.inflate(mBgLayout, null), inflater.inflate(mPreLayout, null));
        }
        tab.setCustomView(itemTab);
      }

      //切换Tab ViewPager 不滑动
      //clearOnTabSelectedListeners();
      //addOnTabSelectedListener(new OnTabSelectedListener() {
      //  @Override public void onTabSelected(Tab tab) {
      //    int position = tab.getPosition();
      //    mViewPager.setCurrentItem(position, false);
      //  }
      //
      //  @Override public void onTabUnselected(Tab tab) {
      //  }
      //
      //  @Override public void onTabReselected(Tab tab) {
      //
      //  }
      //});

    }
  }

  /**
   * @author LC
   */
  private class PagerListener implements ViewPager.OnPageChangeListener {

    private float mLastOffset = 0;

    private int mL = 0; //Last Scroll State
    private int mC = 0; //Current Scroll State

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      if (mC == 0 || (positionOffset == 0 || positionOffset == 1)) {
        //selected
        int selectedTabPosition = getSelectedTabPosition();
        Tab selectedTab = getTabAt(selectedTabPosition);
        HeadLineItemTab selectedItemTab = (HeadLineItemTab) selectedTab.getCustomView();
        selectedItemTab.setSelectedChange(true);

        int tabCount = getTabCount();
        for (int i = 0; i < tabCount; i++) {
          if (i == selectedTabPosition) continue;
          //unSelected
          Tab unSelectedTab = getTabAt(i);
          HeadLineItemTab unSelectedItemTab = (HeadLineItemTab) unSelectedTab.getCustomView();
          unSelectedItemTab.setSelectedChange(false);
        }
      } else if (mC == 1 || mL == 1) { //只处理手势拖动
        float diffOffset = mLastOffset - positionOffset;
        if (positionOffset < MIN_SCROLL || (1 - positionOffset) < MIN_SCROLL
            || Math.abs(diffOffset) > MIN_SCROLL) {
          //mDiffOffset > 0  ViewPager 向左滑动(和手势相反)
          if (diffOffset > 0) {
            //position 是目标position
            //当前的
            TabLayout.Tab currentTab = getTabAt(position + 1);
            HeadLineItemTab itemTabCurrent = (HeadLineItemTab) currentTab.getCustomView();
            itemTabCurrent.clipPercent(positionOffset, HeadLineTabTabPre.LEFT);
            //前面的
            TabLayout.Tab tabPre = getTabAt(position);
            HeadLineItemTab itemTabPre = (HeadLineItemTab) tabPre.getCustomView();

            itemTabPre.clipPercent(positionOffset, HeadLineTabTabPre.RIGHT);
          } else {
            //ViewPager 向右滑动  position 是当前position
            TabLayout.Tab tab = getTabAt(position);
            HeadLineItemTab itemTab = (HeadLineItemTab) tab.getCustomView();
            itemTab.clipPercent(positionOffset, HeadLineTabTabPre.RIGHT);
            //后边的
            TabLayout.Tab tabRight = getTabAt(position + 1);
            HeadLineItemTab itemTabRight = (HeadLineItemTab) tabRight.getCustomView();
            itemTabRight.clipPercent(positionOffset, HeadLineTabTabPre.LEFT);
          }

          mLastOffset = positionOffset;
        }
      }

      if (mC == 0) { //重置
        mC = 0;
        mL = 0;
      }
      if (mC == 1) { //记录上一次
        mL = 1;
      }
    }

    @Override public void onPageSelected(int position) {

    }

    @Override public void onPageScrollStateChanged(int state) {
      this.mC = state;
    }
  }
}
