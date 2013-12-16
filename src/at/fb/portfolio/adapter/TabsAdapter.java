package at.fb.portfolio.adapter;

import java.util.Locale;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one
 * of the sections/tabs/pages.
 */
public class TabsAdapter extends FragmentPagerAdapter {

	private Fragment[] frags;
	private String[] tabTitles;

	public TabsAdapter(FragmentManager fm, Fragment[] frags,
			String[] tabTitles) {
		super(fm);
		this.frags = frags;
		this.tabTitles = tabTitles;
	}

	@Override
	public Fragment getItem(int position) {

		return frags[position];
	}

	@Override
	public int getCount() {
		return frags.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		return tabTitles[position].toUpperCase(l);
	}
}
