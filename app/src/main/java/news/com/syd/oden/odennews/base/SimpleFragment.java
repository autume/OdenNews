package news.com.syd.oden.odennews.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 项目名称：OdenNews
 * 类描述：无MVP的Fragment基类
 * 创建人：oden
 * 创建时间：2016/11/22 15:07
 */
public abstract class SimpleFragment extends SupportFragment {
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
