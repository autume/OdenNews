package news.com.syd.oden.odennews.ui.zhihu.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import news.com.syd.oden.odennews.R;
import news.com.syd.oden.odennews.base.SimpleFragment;
import news.com.syd.oden.odennews.ui.zhihu.adapter.ZhihuMainAdapter;
import news.com.syd.oden.odennews.util.MyLog;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/22 15:07
 */
@EFragment(R.layout.fragment_zhihu_main)
public class ZhihuMainFragment extends SimpleFragment {
    MyLog myLog = new MyLog("[ZhihuMainFragment] ");

    @ViewById(R.id.tab_zhihu_main)
    TabLayout mTabLayout;

    @ViewById(R.id.vp_zhihu_main)
    ViewPager mViewPager;

    String[] tabTitle = new String[]{"日报", "主题", "专栏", "热门"};
    List<Fragment> fragments = new ArrayList<Fragment>();

    ZhihuMainAdapter mAdapter;

    @AfterViews
    void init() {
        myLog.d("init");
        fragments.add(new DailyFragment_());
        fragments.add(new DailyFragment_());
        fragments.add(new DailyFragment_());
        fragments.add(new DailyFragment_());
        mAdapter = new ZhihuMainAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);

//        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[3]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
        mTabLayout.getTabAt(2).setText(tabTitle[2]);
        mTabLayout.getTabAt(3).setText(tabTitle[3]);
    }
}
