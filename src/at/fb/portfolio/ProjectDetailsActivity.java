package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

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
import at.fb.portfolio.projectItems.ProjectItem;
import at.fb.portfolio.projectItems.ProjectItemVideo;

public class ProjectDetailsActivity extends ActionBarActivity {

	private ProjectDetailsPagerAdapter mPagerAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		ArrayList<ProjectGroup> projectGroups = getIntent()
				.getParcelableArrayListExtra(Project.PROJECT_GROUPS);

		setCurrentVideoPos(extras, projectGroups);

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

	private void setCurrentVideoPos(Bundle extras,
			ArrayList<ProjectGroup> projectGroups) {

		List<ProjectItem> projectItems = projectGroups
				.get(ProjectGroup.calcRelGroupPos(
						extras.getInt(Project.PROJECT_ABS_POSITION),
						projectGroups))
				.getProjects()
				.get(ProjectGroup.calcRelProjectPos(
						extras.getInt(Project.PROJECT_ABS_POSITION),
						projectGroups)).getProjectItems();

		for (int i = 0; i < projectItems.size(); i++) {
			if (projectItems.get(i).getClass().equals(ProjectItemVideo.class))
				((ProjectItemVideo) projectItems.get(i)).setPos(extras
						.getInt(ProjectItemVideo.POS));
		}
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
	public void onConfigurationChanged(Configuration newConfig) {

		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

			if (mViewPager != null) {
				int videoPos = 0;
				int projectAbsPos = mViewPager.getCurrentItem();
				int projectRelPos = ProjectGroup.calcRelProjectPos(
						projectAbsPos, mPagerAdapter.getProjectGroups());
				int projectGroupPos = ProjectGroup.calcRelGroupPos(
						projectAbsPos, mPagerAdapter.getProjectGroups());

				ArrayList<ProjectItem> projectItems = mPagerAdapter
						.getProjectGroups().get(projectGroupPos).getProjects()
						.get(projectRelPos).getProjectItems();

				for (int i = 0; i < projectItems.size(); i++) {
					if (projectItems.get(i).getClass()
							.equals(ProjectItemVideo.class)) {
						videoPos = ((ProjectItemVideo) projectItems.get(i))
								.getVideoView().getCurrentPosition();
					}
				}

				Intent intent = new Intent(this, ProjectActivity.class);
				intent.putExtra(Project.PROJECT_REL_POSITION, projectRelPos);
				intent.putExtra(Project.PROJECT_GROUP_POSITION, projectGroupPos);
				intent.putExtra(Project.PROJECT_ABS_POSITION, projectAbsPos);
				intent.putExtra(
						ProjectsFragment.FRAGMENT_CLASS_NAME,
						getIntent().getExtras().getString(
								ProjectsFragment.FRAGMENT_CLASS_NAME));
				intent.putExtra(ProjectItemVideo.POS, videoPos);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				finish();
				startActivity(intent);
			}
		}
		super.onConfigurationChanged(newConfig);
	}

}