package to.marcus.FlickrMVP.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.ui.views.fragments.RecentFragment;
import to.marcus.FlickrMVP.ui.views.fragments.SearchFragment;

/**
 * For MainActivity navigation between fragments
 */

public class HomePagerAdapter extends FragmentPagerAdapter{

    private static final String TAG = HomePagerAdapter.class.getSimpleName();
    public static final int RECENT_POSITION = 0;
    public static final int SEARCH_POSITION = 1;
    public static final int HISTORY_POSITION = 2;
    Context context;

    public HomePagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
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

    private int[] imageResId = {
            R.drawable.ic_brightness_5,
            R.drawable.ic_magnify,
            R.drawable.ic_history
    };

    @Override
    public CharSequence getPageTitle(int position) {

        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        Log.i(TAG, "Set image per tab " + imageResId);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;


        //return "Item " + (position + 1);
    }
}
