package at.fb.portfolio;

import java.util.ArrayList;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import at.fb.portfolio.adapters.ProjectDetailsPagerAdapter;

public class ProjectDetailsActivity extends ActionBarActivity {

	private ProjectDetailsPagerAdapter mPagerAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Need to check if Activity has been switched to landscape mode
	    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
	      finish();
	      return;
	    }

		Bundle extras = getIntent().getExtras();
		ArrayList<ProjectGroup> projectGroups = getIntent().getParcelableArrayListExtra(
				Project.PROJECT_GROUPS);
		
		setTitle(extras.getString(ProjectsFragment.FRAGMENT_PAGE_TITLE));
		setContentView(R.layout.activity_project_details);

		mPagerAdapter = new ProjectDetailsPagerAdapter(
				getSupportFragmentManager(), projectGroups);
		mViewPager = (ViewPager) findViewById(R.id.projectDetailsPager);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setCurrentItem(extras.getInt(Project.PROJECT_POSITION));
		

		// Show the Up button in the action bar.
		setupActionBar();
	}

	private void setupActionBar() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.project_details, menu);
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
		}
		return super.onOptionsItemSelected(item);
	}

}
