package at.fb.portfolio.adapters;

import java.util.Locale;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import at.fb.portfolio.MainActivity;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one
 * of the sections/tabs/pages.
 */
public class TabsAdapter extends FragmentPagerAdapter {

	private Fragment[] mFrags;

	public TabsAdapter(FragmentManager fm, Fragment[] frags) {
		super(fm);
		this.mFrags = frags;
	}

	@Override
	public Fragment getItem(int position) {

		return mFrags[position];
	}

	@Override
	public int getCount() {
		return mFrags.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		return mFrags[position].getArguments().getString(MainActivity.TAB_TITLE).toUpperCase(l);
	}
}
