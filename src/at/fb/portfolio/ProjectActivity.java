package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import at.fb.portfolio.adapters.ProjectGroupAdapter;
import at.fb.portfolio.adapters.TabsAdapter;
import at.fb.portfolio.projectItems.ProjectItemVideo;

/**
 * 
 * shows an overview of all projects
 * 
 */
public class ProjectActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private TabsAdapter mTabsAdapter;

	public static final String SWITCHED_TO_PORTRAIT = "at.fb.portfoliob.ProjectActivity.SWITCHED_TO_PORTRAIT";

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;
	private List<Fragment> mFrags = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_projects);

		// Show the Up button in the action bar.
		final ActionBar actionBar = setupActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Fragments to be shown as Tabs
		mFrags.add(ProjectTechnicalFragment.newInstance(this));
		mFrags.add(ProjectCreativeFragment.newInstance(this));

		mTabsAdapter = new TabsAdapter(getSupportFragmentManager(), mFrags);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.projectPager);
		mViewPager.setAdapter(mTabsAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mTabsAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar
					.addTab(actionBar.newTab()
							.setText(mTabsAdapter.getPageTitle(i))
							.setTabListener(this));
		}

		showActiveProject();

	}

	/**
	 * If switched to landscape, jump to recently viewed project
	 */
	private void showActiveProject() {
		Bundle extras = getIntent().getExtras();
		if (extras != null
				&& extras.getString(ProjectsFragment.FRAGMENT_CLASS_NAME) != null) {
			String hostClassName = extras
					.getString(ProjectsFragment.FRAGMENT_CLASS_NAME);

			for (int i = 0; i < mFrags.size(); i++) {
				if (hostClassName.equals(mFrags.get(i).getClass().getName())) {
					mViewPager.setCurrentItem(i);
					((ProjectsFragment) mFrags.get(i)).setCurrentProject(
							extras.getInt(Project.PROJECT_GROUP_POSITION),
							extras.getInt(Project.PROJECT_REL_POSITION));

					((ProjectsFragment) mFrags.get(i))
							.setCurrentVideoPos(extras
									.getInt(ProjectItemVideo.POS));
				}
			}
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

			ProjectGroupAdapter curAdapter = (ProjectGroupAdapter) ((ListView) mViewPager
					.getFocusedChild().findViewById(R.id.lv_projects))
					.getAdapter();

			int pos = curAdapter.getCurrentProjectAbsPos();

			//
			// pos is -1 (default) unless an item has been clicked.
			// if pos is still default, refer to the stored value from extras
			//
			if (pos == -1) {
				if (getIntent().getExtras() != null
						&& curAdapter.getHostClass().getName().equals(
								getIntent().getExtras().getString(
										ProjectsFragment.FRAGMENT_CLASS_NAME))) {
					pos = getIntent().getExtras().getInt(
							Project.PROJECT_ABS_POSITION);
				} else {
					pos = 0;
				}
			}

			Intent intent = new Intent(this, ProjectDetailsActivity.class);

			intent.putExtra(Project.PROJECT_ABS_POSITION, pos);
			intent.putExtra(
					ProjectsFragment.FRAGMENT_PAGE_TITLE,
					((ProjectsFragment) mFrags.get(mViewPager.getCurrentItem()))
							.getPageTitle());
			intent.putExtra(ProjectsFragment.FRAGMENT_CLASS_NAME,
					mFrags.get(mViewPager.getCurrentItem()).getClass()
							.getName());
			intent.putParcelableArrayListExtra(
					Project.PROJECT_GROUPS,
					(ArrayList<? extends Parcelable>) ((ProjectsFragment) mFrags
							.get(mViewPager.getCurrentItem()))
							.getProjectGroups());

			// store VideoPos

			Project curPro = ((ProjectsFragment) mFrags.get(mViewPager
					.getCurrentItem())).getProjectAtAbsPos(pos);

			for (int i = 0; i < curPro.getProjectItems().size(); i++) {
				if (curPro.getProjectItems().get(i).getClass()
						.equals(ProjectItemVideo.class)
						&& ((ProjectItemVideo) curPro.getProjectItems().get(i))
								.getVideoView() != null) {
					intent.putExtra(
							ProjectItemVideo.POS,
							((ProjectItemVideo) curPro.getProjectItems().get(i))
									.getVideoView().getCurrentPosition());
				}
			}

			// recreate activity
			finish();
			startActivity(new Intent(this, this.getClass()));

			startActivity(intent);
		} else {
			// recreate activity
			finish();
			startActivity(new Intent(this, this.getClass()));
		}
		super.onConfigurationChanged(newConfig);
	}

	private ActionBar setupActionBar() {
		ActionBar aBar = getSupportActionBar();
		aBar.setDisplayHomeAsUpEnabled(true);
		return aBar;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;

		case R.id.action_contact_mail:
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_EMAIL,
					new String[] { getString(R.string.email_address) });
			i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
			i.putExtra(Intent.EXTRA_TEXT, "");
			try {
				startActivity(Intent.createChooser(i,
						getString(R.string.title_message_chooser)));
			} catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(this, "Es wurde kein E-Mail-Client gefunden.",
						Toast.LENGTH_SHORT).show();
			}
			return super.onOptionsItemSelected(item);

		case R.id.action_contact_phone:
			Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ getString(R.string.phone_number)));
			startActivity(callIntent);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		mViewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

}
