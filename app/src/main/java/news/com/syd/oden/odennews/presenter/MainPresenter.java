package news.com.syd.oden.odennews.presenter;

import android.util.Log;

import javax.inject.Inject;

import news.com.syd.oden.odennews.base.RxPresenter;
import news.com.syd.oden.odennews.component.RxBus;
import news.com.syd.oden.odennews.model.bean.NightModeEvent;
import news.com.syd.oden.odennews.presenter.contract.MainContract;
import news.com.syd.oden.odennews.util.RxUtil;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/12 11:50
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    @Inject
    public MainPresenter() {
        //registerEvent();
    }

    void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(NightModeEvent.class)
                .compose(RxUtil.<NightModeEvent>rxSchedulerHelper())
                .map(new Func1<NightModeEvent, Boolean>() {
                    @Override
                    public Boolean call(NightModeEvent nightModeEvent) {
                        return nightModeEvent.getNightMode();
                    }
                })
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError("切换模式失败ヽ(≧Д≦)ノ");
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        mView.useNightMode(aBoolean);
                    }
                });
        addSubscrebe(rxSubscription);
    }

}