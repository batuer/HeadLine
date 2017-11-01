package gusi.headline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
  }

  private List<String> initData() {
    ArrayList<String> list = new ArrayList<>(15);
    for (int i = 0; i < 15; i++) {
      list.add("Item: " + i);
    }
    return list;
  }
}
