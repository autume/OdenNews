package news.com.syd.oden.odennews.ui.zhihu.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import news.com.syd.oden.odennews.R;
import news.com.syd.oden.odennews.base.BaseFragment;
import news.com.syd.oden.odennews.model.bean.DailyListBean;
import news.com.syd.oden.odennews.presenter.DailyPresenter;
import news.com.syd.oden.odennews.presenter.contract.DailyContract;
import news.com.syd.oden.odennews.ui.zhihu.adapter.DailyAdapter;
import news.com.syd.oden.odennews.util.MyLog;
import news.com.syd.oden.odennews.util.SnackbarUtil;
import news.com.syd.oden.odennews.widget.ProgressImageView;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/22 16:05
 */
@EFragment(R.layout.fragment_daily)
public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View  {
    MyLog myLog = new MyLog("[DailyFragment] ");

    @ViewById(R.id.iv_progress)
    ProgressImageView ivProgress;
    @ViewById(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @ViewById(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @ViewById(R.id.rv_daily_list)
    RecyclerView rvDailyList;

    String currentDate;
    DailyAdapter mAdapter;
    List<DailyListBean.StoriesBean> mList = new ArrayList<>();

    @AfterViews
    void init() {
        initRecyclerView();
        setAdapter();
        initEventAndData();
        mPresenter.getDailyData();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    /**
     * 配置RecyclerView
     */
    private void initRecyclerView() {
        rvDailyList.setLayoutManager(new LinearLayoutManager(mContext));
    }

    public void setAdapter() {
        mAdapter = new DailyAdapter(mContext, mList);
        rvDailyList.setAdapter(mAdapter);
    }

    private void initEventAndData() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getDailyData();
            }
        });
    }

    /**
     * 当天数据
     * @param info
     */
    @Override
    public void showContent(DailyListBean info) {
        myLog.d("showContent");
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        mList = info.getStories();
        currentDate = String.valueOf(Integer.valueOf(info.getDate()) + 1);
        setAdapter();
//        mPresenter.stopInterval();
//        mPresenter.startInterval();
    }

    @Override
    public void showError(String msg) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        SnackbarUtil.showShort(rvDailyList,msg);
    }

}
