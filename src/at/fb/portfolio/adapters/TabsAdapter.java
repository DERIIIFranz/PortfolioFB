package at.fb.portfolio.adapters;

import java.util.List;
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

	private List<Fragment> mFrags;

	public TabsAdapter(FragmentManager fm, List<Fragment> frags) {
		super(fm);
		this.mFrags = frags;
	}

	@Override
	public Fragment getItem(int position) {

		return mFrags.get(position);
	}

	@Override
	public int getCount() {
		return mFrags.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		return mFrags.get(position).getArguments().getString(MainActivity.TAB_TITLE).toUpperCase(l);
	}
}
