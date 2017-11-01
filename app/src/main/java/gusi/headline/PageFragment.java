package gusi.headline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作者：${ylw} on 2017-10-30 18:27
 */
public class PageFragment extends Fragment {


  private TextView mTvContent;

  public static PageFragment newInstance(String content) {
    Bundle args = new Bundle();
    PageFragment fragment = new PageFragment();
    args.putString("Content", content);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_pager, container, false);
    mTvContent = view.findViewById(R.id.tv_content);
    return view;
  }



  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    String content = getArguments().getString("Content", "Null Content");
    mTvContent.setText(content);
  }
}
