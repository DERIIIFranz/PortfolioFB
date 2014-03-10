package at.fb.portfolio.adapter;

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

	private Fragment[] frags;

	public TabsAdapter(FragmentManager fm, Fragment[] frags) {
		super(fm);
		this.frags = frags;
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
		return frags[position].getArguments().getString(MainActivity.TAB_TITLE).toUpperCase(l);
	}
}
