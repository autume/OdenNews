package news.com.syd.oden.odennews.di.component;

import android.app.Activity;

import dagger.Component;
import news.com.syd.oden.odennews.di.ActivityScope;
import news.com.syd.oden.odennews.di.module.ActivityModule;
import news.com.syd.oden.odennews.ui.main.activity.MainActivity;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/21 10:32
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);
}
