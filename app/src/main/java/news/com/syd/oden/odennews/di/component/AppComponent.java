package news.com.syd.oden.odennews.di.component;

import javax.inject.Singleton;

import dagger.Component;
import news.com.syd.oden.odennews.app.App;
import news.com.syd.oden.odennews.di.ContextLife;
import news.com.syd.oden.odennews.di.module.AppModule;
import news.com.syd.oden.odennews.model.http.RetrofitHelper;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/21 10:04
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    @ContextLife("Application")
    App getContext();  // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类
}
