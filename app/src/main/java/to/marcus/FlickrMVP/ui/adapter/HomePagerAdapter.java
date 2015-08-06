package to.marcus.FlickrMVP.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.ui.views.base.BaseFragment;
import to.marcus.FlickrMVP.ui.views.fragments.HistoryFragment;
import to.marcus.FlickrMVP.ui.views.fragments.PhotoViewFragment;
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
    private BaseFragment mFragmentAtPos0;
    Context context;
    private final FragmentManager mFragmentManager;

    public HomePagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.mFragmentManager = fm;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case RECENT_POSITION:
                if(mFragmentAtPos0 == null) {
                    mFragmentAtPos0 = RecentFragment.newInstance(new FragmentChangeListener(){
                        public void onSwitchToNextFragment(Bundle args) {
                            mFragmentManager.beginTransaction()
                                    .remove(mFragmentAtPos0)
                                    .commit();
                            mFragmentAtPos0 = PhotoViewFragment.newInstance();
                            mFragmentAtPos0.setArguments(args);
                            mFragmentAtPos0.setIsChildFragment(true);
                            notifyDataSetChanged();
                        }
                    });
                }
                return mFragmentAtPos0;
            case SEARCH_POSITION:
                return new SearchFragment();
            case HISTORY_POSITION:
                return new HistoryFragment();
        }
        return null;
    }

    @Override
    public int getItemPosition(Object object){
        if(object instanceof RecentFragment && mFragmentAtPos0 instanceof PhotoViewFragment){
            return POSITION_NONE;
        }else if(object instanceof PhotoViewFragment){
            return POSITION_NONE;
        }
        return POSITION_UNCHANGED;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public void replaceChildFragment(BaseFragment childFrg, int position){
        switch(position){
            case RECENT_POSITION:
                mFragmentManager.beginTransaction().remove(childFrg).commit();
                mFragmentAtPos0 = RecentFragment.newInstance(new FragmentChangeListener() {
                    @Override
                    public void onSwitchToNextFragment(Bundle args) {
                        mFragmentManager.beginTransaction().remove(mFragmentAtPos0)
                                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                                .commit();
                        mFragmentAtPos0 = PhotoViewFragment.newInstance();
                        mFragmentAtPos0.setArguments(args);
                        mFragmentAtPos0.setIsChildFragment(true);
                        notifyDataSetChanged();
                    }
                });
                notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    private int[] imageResId = {
            R.drawable.ic_brightness_5,
            R.drawable.ic_magnify,
            R.drawable.ic_history
    };

    //Set image in place of tab text
    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        Log.i(TAG, "Set image per tab " + imageResId);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    public interface FragmentChangeListener{
        void onSwitchToNextFragment(Bundle args);
    }

}
