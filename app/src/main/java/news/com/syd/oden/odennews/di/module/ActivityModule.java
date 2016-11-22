package news.com.syd.oden.odennews.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import news.com.syd.oden.odennews.di.ActivityScope;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/21 10:31
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
