package news.com.syd.oden.odennews.ui.main.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import news.com.syd.oden.odennews.R;
import news.com.syd.oden.odennews.base.BaseActivity;
import news.com.syd.oden.odennews.presenter.MainPresenter;
import news.com.syd.oden.odennews.presenter.contract.MainContract;
import news.com.syd.oden.odennews.ui.zhihu.fragment.ZhihuMainFragment;
import news.com.syd.oden.odennews.ui.zhihu.fragment.ZhihuMainFragment_;
import news.com.syd.oden.odennews.util.MyLog;
import news.com.syd.oden.odennews.util.SnackbarUtil;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View,NavigationView.OnNavigationItemSelectedListener {
    MyLog myLog = new MyLog("[MainActivity] ");

    ActionBarDrawerToggle toggle;
    MenuItem mLastMenuItem;

    ZhihuMainFragment mZhihuFragment;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;
    @ViewById(R.id.nav_view)
    NavigationView navigationView;
    @ViewById(R.id.tv_hello)
    TextView tv_hello;

    @AfterViews
    void init() {
        initDrawer();
    }

    private void initDrawer() {
        //toolbar
        setToolBar(toolbar, getString(R.string.zhihu));

        mZhihuFragment = new ZhihuMainFragment_();

        loadRootFragment(R.id.fl_main_content, mZhihuFragment);

        //侧滑
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mLastMenuItem = navigationView.getMenu().findItem(R.id.nav_zhihu);
    }

    @SuppressWarnings("StatementWithEmptyBody") //屏蔽编译警告
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_zhihu:
                break;
            case R.id.nav_wechat:
                break;
            case R.id.nav_gank:
                break;
            case R.id.nav_like:
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_about:
                break;
        }
        if(mLastMenuItem != null) {
            mLastMenuItem.setChecked(false);
        }
        mLastMenuItem = menuItem;
        menuItem.setChecked(true);
        toolbar.setTitle(menuItem.getTitle());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showError(String msg) {
        SnackbarUtil.showShort(toolbar, msg);
    }

}
