package news.com.syd.oden.odennews.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import news.com.syd.oden.odennews.app.App;
import news.com.syd.oden.odennews.di.ContextLife;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/21 10:19
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    App provideApplicationContext() {
        return application;
    }
}
