package to.marcus.FlickrMVP.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import to.marcus.FlickrMVP.ui.views.HomeFragment;

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
                return new HomeFragment();
            case SEARCH_POSITION:
                return new HomeFragment();
            case HISTORY_POSITION:
                return new HomeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
