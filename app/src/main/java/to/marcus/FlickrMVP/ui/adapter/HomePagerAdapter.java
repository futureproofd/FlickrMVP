package to.marcus.FlickrMVP.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import to.marcus.FlickrMVP.ui.views.fragments.RecentFragment;
import to.marcus.FlickrMVP.ui.views.fragments.SearchFragment;

/**
 * For MainActivity navigation between fragments
 */

public class HomePagerAdapter extends FragmentPagerAdapter{

    public static final int RECENT_POSITION = 0;
    public static final int SEARCH_POSITION = 1;
    public static final int HISTORY_POSITION = 2;

    public HomePagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case RECENT_POSITION:
                return new RecentFragment();
            case SEARCH_POSITION:
                return new SearchFragment();
            case HISTORY_POSITION:
                return new RecentFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
