package news.com.syd.oden.odennews.presenter;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.List;

import javax.inject.Inject;

import news.com.syd.oden.odennews.base.RxPresenter;
import news.com.syd.oden.odennews.component.RxBus;
import news.com.syd.oden.odennews.model.bean.DailyBeforeListBean;
import news.com.syd.oden.odennews.model.bean.DailyListBean;
import news.com.syd.oden.odennews.model.http.RetrofitHelper;
import news.com.syd.oden.odennews.presenter.contract.DailyContract;
import news.com.syd.oden.odennews.util.MyLog;
import news.com.syd.oden.odennews.util.RxUtil;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/22 16:11
 */
public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter{
    MyLog myLog = new MyLog("[DailyPresenter] ");
    private RetrofitHelper mRetrofitHelper;

    private int topCount = 0;

    @Inject
    public DailyPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
//        mView.showError("数据加载失败ヽ(≧Д≦)ノ");
    }

//    private void registerEvent() {
//        Subscription rxSubscription = RxBus.getDefault().toObservable(CalendarDay.class)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())  //为了显示progress切到主线程
//                .map(new Func1<CalendarDay, String>() {
//                    @Override
//                    public String call(CalendarDay calendarDay) {
//                        mView.showProgress();
//                        StringBuilder date = new StringBuilder();
//                        String year = String.valueOf(calendarDay.getYear());
//                        String month = String.valueOf(calendarDay.getMonth() + 1);
//                        String day = String.valueOf(calendarDay.getDay() + 1);
//                        if(month.length() < 2) {
//                            month = "0" + month;
//                        }
//                        if(day.length() < 2) {
//                            day = "0" + day;
//                        }
//                        return date.append(year).append(month).append(day).toString();
//                    }
//                })
//                .filter(new Func1<String, Boolean>() {
//                    @Override
//                    public Boolean call(String s) {
//                        if(s.equals(DateUtil.getTomorrowDate())) {
//                            getDailyData();
//                            return false;
//                        }
//                        return true;
//                    }
//                })
//                .observeOn(Schedulers.io())   //为了网络请求切到io线程
//                .flatMap(new Func1<String, Observable<DailyBeforeListBean>>() {
//                    @Override
//                    public Observable<DailyBeforeListBean> call(String date) {
//                        return mRetrofitHelper.fetchDailyBeforeListInfo(date);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())    //为了使用Realm和显示结果切到主线程
//                .map(new Func1<DailyBeforeListBean, DailyBeforeListBean>() {
//                    @Override
//                    public DailyBeforeListBean call(DailyBeforeListBean dailyBeforeListBean) {
//                        List<DailyListBean.StoriesBean> list = dailyBeforeListBean.getStories();
//                        for(DailyListBean.StoriesBean item : list) {
//                            item.setReadState(mRealmHelper.queryNewsId(item.getId()));
//                        }
//                        return dailyBeforeListBean;
//                    }
//                })
//                .subscribe(new Action1<DailyBeforeListBean>() {
//                               @Override
//                               public void call(DailyBeforeListBean dailyBeforeListBean) {
//                                   int year = Integer.valueOf(dailyBeforeListBean.getDate().substring(0,4));
//                                   int month = Integer.valueOf(dailyBeforeListBean.getDate().substring(4,6));
//                                   int day = Integer.valueOf(dailyBeforeListBean.getDate().substring(6,8));
//                                   mView.showMoreContent(String.format("%d年%d月%d日",year,month,day),dailyBeforeListBean);
//                               }
//                           },
//                        new Action1<Throwable>() {
//                            @Override
//                            public void call(Throwable throwable) {
//                                registerEvent();
//                                mView.showError("数据加载失败ヽ(≧Д≦)ノ");
//                            }});
//        addSubscrebe(rxSubscription);
//    }

    @Override
    public void getDailyData() {
        Subscription rxSubscription = mRetrofitHelper.fetchDailyListInfo()
                .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
                .map(new Func1<DailyListBean, DailyListBean>() {
                    @Override
                    public DailyListBean call(DailyListBean dailyListBean) {
                        List<DailyListBean.StoriesBean> list = dailyListBean.getStories();
//                        for(DailyListBean.StoriesBean item : list) {
//                            item.setReadState(mRealmHelper.queryNewsId(item.getId()));
//                        }
                        return dailyListBean;
                    }
                })
                .subscribe(new Action1<DailyListBean>() {
                    @Override
                    public void call(DailyListBean dailyListBean) {
                        topCount = dailyListBean.getTop_stories().size();
                        myLog.d("dailyListBean: " + dailyListBean.getStories());
                        mView.showContent(dailyListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败ヽ(≧Д≦)ノ");
                    }
                });
        addSubscrebe(rxSubscription);
    }

}
