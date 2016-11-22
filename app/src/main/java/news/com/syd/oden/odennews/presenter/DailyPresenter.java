package news.com.syd.oden.odennews.presenter;

import javax.inject.Inject;

import news.com.syd.oden.odennews.base.RxPresenter;
import news.com.syd.oden.odennews.presenter.contract.DailyContract;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/22 16:11
 */
public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter{

    @Inject
    public DailyPresenter() {

    }

}
