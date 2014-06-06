package at.fb.portfolio;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import at.fb.portfolio.adapters.ProjectDetailsPagerAdapter;

public class ProjectDetailsActivity extends ActionBarActivity {

	private ProjectDetailsPagerAdapter mPagerAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Need to check if Activity has been switched to landscape mode
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Intent intent = new Intent(this, ProjectActivity.class);
			intent.putExtra(Project.PROJECT_REL_POSITION,
					savedInstanceState.getInt(Project.PROJECT_REL_POSITION));
			intent.putExtra(Project.PROJECT_GROUP_POSITION,
					savedInstanceState.getInt(Project.PROJECT_GROUP_POSITION));
			intent.putExtra(ProjectsFragment.FRAGMENT_CLASS_NAME, getIntent()
					.getExtras()
					.getString(ProjectsFragment.FRAGMENT_CLASS_NAME));
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return;
		}

		Bundle extras = getIntent().getExtras();
		ArrayList<ProjectGroup> projectGroups = getIntent()
				.getParcelableArrayListExtra(Project.PROJECT_GROUPS);

		setTitle(getString(extras.getInt(ProjectsFragment.FRAGMENT_PAGE_TITLE)));
		setContentView(R.layout.activity_project_details);

		mPagerAdapter = new ProjectDetailsPagerAdapter(
				getSupportFragmentManager(), projectGroups);
		mViewPager = (ViewPager) findViewById(R.id.projectDetailsPager);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setCurrentItem(extras.getInt(Project.PROJECT_ABS_POSITION));

		// Show the Up button in the action bar.
		setupActionBar();
	}

	private void setupActionBar() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mViewPager != null) {

			// determine projectGroupId for requested item
			int projectId = mViewPager.getCurrentItem();
			int groupId;
			for (groupId = 0; (projectId - mPagerAdapter.getProjectGroups()
					.get(groupId).getProjects().size()) >= 0; groupId++) {
				projectId -= mPagerAdapter.getProjectGroups().get(groupId)
						.getProjects().size();
			}

			outState.putInt(Project.PROJECT_REL_POSITION, projectId);
			outState.putInt(Project.PROJECT_GROUP_POSITION, groupId);
		}
	}
}