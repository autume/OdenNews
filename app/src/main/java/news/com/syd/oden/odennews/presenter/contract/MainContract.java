package news.com.syd.oden.odennews.presenter.contract;

import news.com.syd.oden.odennews.base.BasePresenter;
import news.com.syd.oden.odennews.base.BaseView;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/12 11:50
 */

public interface MainContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
    }
}
