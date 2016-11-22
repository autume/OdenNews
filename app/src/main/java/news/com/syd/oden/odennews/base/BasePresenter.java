package news.com.syd.oden.odennews.base;


public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
