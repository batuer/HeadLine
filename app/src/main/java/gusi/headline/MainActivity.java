package gusi.headline;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.gusi.headline.HeadLineTabLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    HeadLineTabLayout tabLayout = (HeadLineTabLayout) findViewById(R.id.tabLayout);
    ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

    final List<String> data = initData();
    final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

      @Override public Fragment getItem(int position) {

        return PageFragment.newInstance(data.get(position));
      }

      @Override public int getCount() {
        return data.size();
      }

      @Override public CharSequence getPageTitle(int position) {
        return data.get(position);
      }
    };
    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);

    int[] ivbgs = {
        R.mipmap.ic_1_bg, R.mipmap.ic_2_bg, R.mipmap.ic_3_bg, R.mipmap.ic_4_bg, R.mipmap.ic_5_bg
    };
    int[] ivpres = {
        R.mipmap.ic_1_pre, R.mipmap.ic_2_pre, R.mipmap.ic_3_pre, R.mipmap.ic_4_pre,
        R.mipmap.ic_5_pre
    };

    String[] titles = { "保障", "家人", "首页", "我的", "药丸" };

    for (int count = tabLayout.getTabCount(), i = 0; i < count; i++) {
      TabLayout.Tab tab = tabLayout.getTabAt(i);
      View customView = tab.getCustomView();
      ImageView ivPre = customView.findViewById(R.id.iv_pre);
      ImageView ivBg = customView.findViewById(R.id.iv_bg);
      TextView tvBg = customView.findViewById(R.id.tv_bg);
      TextView tvPre = customView.findViewById(R.id.tv_pre);

      int pos = i % 5;
      ivPre.setImageResource(ivpres[pos]);
      ivBg.setImageResource(ivbgs[pos]);
      tvBg.setText(titles[pos]);
      tvPre.setText(titles[pos]);
    }
  }

  private List<String> initData() {
    ArrayList<String> list = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      list.add("Item: " + i);
    }
    return list;
  }
}
