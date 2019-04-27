package bardiademon.Memoir.bardiademon.Class.Other;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterTabHost extends FragmentPagerAdapter
{
    private List<PutAdapter.ValueFragment> valueFragments;

    public AdapterTabHost (FragmentManager fragment)
    {
        super(fragment);
    }

    @Override
    public Fragment getItem (int position)
    {
        return valueFragments.get(position).fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle (int position)
    {
        return valueFragments.get(position).title;
    }

    @Override
    public int getCount ()
    {
        return valueFragments.size();
    }

    public static abstract class PutAdapter
    {
        private static List<ValueFragment> ValueFragments;

        public static void Put (Fragment fragment, String title)
        {
            CheckList();
            ValueFragment valueFragment = new ValueFragment();
            valueFragment.fragment = fragment;
            valueFragment.title = title;
            ValueFragments.add(valueFragment);
        }

        private static void CheckList ()
        {
            if (ValueFragments == null) ValueFragments = new ArrayList<>();
        }

        private static class ValueFragment
        {
            String title;
            Fragment fragment;
        }

        public static List<ValueFragment> GetValueFragments ()
        {
            return ValueFragments;
        }

        public static void Clear ()
        {
            ValueFragments = null;
        }
    }

    public void apply ()
    {
        valueFragments = PutAdapter.GetValueFragments();
        PutAdapter.Clear();
    }
}
