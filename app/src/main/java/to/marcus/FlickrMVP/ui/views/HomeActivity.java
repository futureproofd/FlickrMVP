package to.marcus.FlickrMVP.ui.views;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.ui.adapter.HomePagerAdapter;
import to.marcus.FlickrMVP.ui.views.supportwidgets.SlidingTabLayout;

/**
 * Created by marcus on 23/03/15
 */

public class HomeActivity extends ActionBarActivity implements HomeView{
    private static final String TAG = HomeActivity.class.getSimpleName();
    private HomePagerAdapter mHomePagerAdapter;
    public ViewPager mViewPager;
    public Toolbar mToolBar;
    public SlidingTabLayout mSlidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initHomeViewPager();
        initSlidingTabs();
        initToolBar();
    }

    @Override
    public void initHomeViewPager(){
        mHomePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.homeViewPager);
        mViewPager.setAdapter(mHomePagerAdapter);
    }

    @Override
    public void initToolBar(){
        mToolBar = (Toolbar)findViewById(R.id.homeToolbar);
        setSupportActionBar(mToolBar);

    }

    @Override
    public void initSlidingTabs(){
        mSlidingTabLayout = (SlidingTabLayout)findViewById(R.id.homeSlidingTabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer(){
            @Override
            public int getIndicatorColor(int position){
                return getResources().getColor(R.color.tabScroll);
            }
        });
        mSlidingTabLayout.setViewPager(mViewPager);
    }
}
