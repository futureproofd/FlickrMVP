package to.marcus.FlickrMVP.ui.views;

/**
 * Created by marcus on 6/15/2015
 * Methods to be implemented to get view structure
 */

public interface HomeView extends BaseContextView {
   public void initHomeViewPager();
   public void initToolBar();
   public void initSlidingTabs();
   public void initSearchBox();
   public void initProgressBar();
}
