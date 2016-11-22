package news.com.syd.oden.odennews.ui.zhihu.fragment;

import org.androidannotations.annotations.EFragment;

import news.com.syd.oden.odennews.R;
import news.com.syd.oden.odennews.base.BaseFragment;
import news.com.syd.oden.odennews.presenter.DailyPresenter;
import news.com.syd.oden.odennews.presenter.contract.DailyContract;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/22 16:05
 */
@EFragment(R.layout.fragment_daily)
public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View  {


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showError(String msg) {

    }
}
