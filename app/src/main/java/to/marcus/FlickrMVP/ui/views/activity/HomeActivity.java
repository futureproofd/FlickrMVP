package to.marcus.FlickrMVP.ui.views.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.ui.adapter.HomePagerAdapter;
import to.marcus.FlickrMVP.ui.views.HomeView;
import to.marcus.FlickrMVP.ui.views.supportwidgets.SlidingTabLayout;

/**
 * Created by marcus on 23/03/15
 * Main navigation
 */

public class HomeActivity extends ActionBarActivity implements HomeView {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private HomePagerAdapter mHomePagerAdapter;
    public ViewPager mViewPager;
    public Toolbar mToolBar;
    public SlidingTabLayout mSlidingTabLayout;
    public EditText mSearchBox;
    public ImageView mClearSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_main);
        initHomeViewPager();
        initSlidingTabs();
        initToolBar();
        initSearchBox();
    }

    /*
    HomeView Implementations
     */
    @Override
    public void initHomeViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.homeViewPager);
        mHomePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), getContext());
        mViewPager.setAdapter(mHomePagerAdapter);
    }

    @Override
    public void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.homeToolbar);
        mToolBar.setTitleTextAppearance(this, R.style.ToolBarTextStyle);
        mToolBar.setLogo(R.drawable.camera_logo);
        setSupportActionBar(mToolBar);
        setToolbarTitle(mViewPager.getCurrentItem());
    }

    @Override
    public void initSlidingTabs() {
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.homeSlidingTabs);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_layout, R.id.tab_text);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabScroll);
            }
        });
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int position) {
                setToolbarTitle(position);
                if(position == HomePagerAdapter.SEARCH_POSITION){
                    mSearchBox.setVisibility(View.VISIBLE);
                    mClearSearchButton.setVisibility(View.VISIBLE);
                }else{
                    mSearchBox.setVisibility(View.INVISIBLE);
                    mClearSearchButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void initSearchBox() {
        mClearSearchButton = (ImageView) findViewById(R.id.search_clear);
        mSearchBox = (EditText) findViewById(R.id.toolbar_search_box);
        mSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "on text changed listener");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() <= 0) {
                    Log.i(TAG, "after text changed listener");
                    mSearchBox.setHintTextColor(Color.parseColor("#b3ffffff"));
                }
            }
        });

        mClearSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchBox.setText("");
            }
        });
    }

    //Helper
    private void setToolbarTitle(int position) {
        switch(position){
            case HomePagerAdapter.RECENT_POSITION:
                getSupportActionBar().setTitle(R.string.fragment_recent);
                break;
            case HomePagerAdapter.SEARCH_POSITION:
                getSupportActionBar().setTitle("");
                break;
            case HomePagerAdapter.HISTORY_POSITION:
                getSupportActionBar().setTitle(R.string.fragment_history);
                break;
        }
    }

    /*
    BaseContextView
    */
    @Override
    public Context getContext(){
        return this.getApplicationContext();
    }
}

