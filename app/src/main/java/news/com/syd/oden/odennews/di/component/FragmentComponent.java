package news.com.syd.oden.odennews.di.component;

import android.app.Activity;

import dagger.Component;
import news.com.syd.oden.odennews.di.FragmentScope;
import news.com.syd.oden.odennews.di.module.FragmentModule;
import news.com.syd.oden.odennews.ui.zhihu.fragment.DailyFragment;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/22 16:08
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(DailyFragment dailyFragment);
}

