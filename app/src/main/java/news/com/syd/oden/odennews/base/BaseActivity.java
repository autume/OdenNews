package news.com.syd.oden.odennews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportActivity;
import news.com.syd.oden.odennews.app.App;
import news.com.syd.oden.odennews.di.component.ActivityComponent;
import news.com.syd.oden.odennews.di.component.DaggerActivityComponent;
import news.com.syd.oden.odennews.di.module.ActivityModule;
import news.com.syd.oden.odennews.util.MyLog;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/12 9:36
 */
public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseView{
    MyLog myLog = new MyLog("[BaseActivity] ");

    @Inject
    public T mPresenter;

    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        App.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //左上角图标可点击
        getSupportActionBar().setDisplayShowHomeEnabled(true); // 给左上角图标的左边加上一个返回的图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        App.getInstance().removeActivity(this);
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    protected abstract void initInject();

}