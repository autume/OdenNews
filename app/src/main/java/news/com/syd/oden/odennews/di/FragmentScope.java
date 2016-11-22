package news.com.syd.oden.odennews.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 项目名称：OdenNews
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/11/22 16:08
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
