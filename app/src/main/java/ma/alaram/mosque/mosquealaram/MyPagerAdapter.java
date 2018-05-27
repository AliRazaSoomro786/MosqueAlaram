package ma.alaram.mosque.mosquealaram;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alisoomro on 30/04/2018.
 */

public class MyPagerAdapter extends FragmentPagerAdapter
{
    private static int NUM_ITEMS = 5;
    private String tabTitles[] = new String[] { "Prays", "Calender", "Quraan","Gallery","Pryer times"};
    public MyPagerAdapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount()
    {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0: // Fragment # 0 - This will show FirstFragment
                return FirstFragment.newInstance();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return SecondFragment.newInstance();
            case 2:
                return ThirdFragment.newInstance();
            case 3:
                return FourthFragment.newInstance();
            case 4:
                return FifthFragment.newInstance();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabTitles[position];
    }

}

