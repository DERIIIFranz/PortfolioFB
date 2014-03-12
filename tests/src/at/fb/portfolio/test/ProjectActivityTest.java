package at.fb.portfolio.test;

import java.util.Locale;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import at.fb.portfolio.Project;
import at.fb.portfolio.ProjectActivity;
import at.fb.portfolio.ProjectCreativeFragment;
import at.fb.portfolio.ProjectDetailsActivity;
import at.fb.portfolio.ProjectTechnicalFragment;
import at.fb.portfolio.ProjectsFragment;
import at.fb.portfolio.R;

import com.jayway.android.robotium.solo.Solo;

public class ProjectActivityTest extends
		ActivityInstrumentationTestCase2<ProjectActivity> {

	private Solo solo;

	private ProjectActivity pActivity;
	private ViewPager projectPager;
	private Locale l;
	private ListView listFragPartials;

	public ProjectActivityTest() {
		super(ProjectActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		solo = new Solo(getInstrumentation(), getActivity());

		l = Locale.getDefault();
		pActivity = getActivity();
		projectPager = (ViewPager) pActivity.findViewById(R.id.projectPager);
	}

	public void testPreConditions() {
		assertNotNull("ProjectActivity is null", pActivity);
		assertNotNull("ProjectActivity is null", projectPager);
	}

	public void testProjectsList_layout() {
		listFragPartials = (ListView) pActivity
				.findViewById(R.id.ListView_projects_technical);

		final View decorView = pActivity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, listFragPartials);
	}

	public void testTabBar() {
		// test tab-count
		assertEquals(2, pActivity.getSupportActionBar().getTabCount());

		// test tablabels
		assertEquals(
				pActivity.getString(
						R.string.tabTitle_project_fragment_technical)
						.toUpperCase(l), pActivity.getSupportActionBar()
						.getTabAt(0).getText());
		assertEquals(
				pActivity
						.getString(R.string.tabTitle_project_fragment_creative)
						.toUpperCase(l), pActivity.getSupportActionBar()
						.getTabAt(1).getText());
	}

	public void testTechnicalContent() {

		listFragPartials = (ListView) pActivity
				.findViewById(R.id.ListView_projects_technical);

		assertTrue(ProjectTechnicalFragment.isVisibleToUser());

		ProjectsFragment pFragment = (ProjectTechnicalFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 0);

		// count displayed projects
		int projectCountView = 0;
		for (int i = 0; i < listFragPartials.getChildCount(); i++) {
			View v = listFragPartials.getChildAt(i);
			projectCountView += ((GridView) v
					.findViewById(R.id.gridview_projects)).getCount();
		}

		int projectCountObject = 0;
		for (int i = 0; i < pFragment.getProjectGroups().size(); i++) {
			projectCountObject += pFragment.getProjectGroups().get(i)
					.getProjects().size();
		}

		assertTrue("Not all technical projects displayed",
				projectCountObject == projectCountView);

		/*
		 * click on every item and verify if its title is displayed in
		 * ProjectDetailsActivity
		 */
		for (int i = 0; i < listFragPartials.getChildCount(); i++) {
			for (int j = 0; j < ((GridView) listFragPartials.getChildAt(i)
					.findViewById(R.id.gridview_projects)).getCount(); j++) {
				Project p = pFragment.getProjectGroups().get(i)
						.getProjects().get(j);
				View projectItemView = ((GridView) listFragPartials.getChildAt(
						i).findViewById(R.id.gridview_projects)).getChildAt(j);
				solo.clickOnView(projectItemView);

				assertTrue(solo.searchText(p.getTitle()));
				solo.assertCurrentActivity(
						"activity should be ProjectDetailsActivity",
						ProjectDetailsActivity.class);
				solo.goBack();
			}
		}
	}

	public void testCreativeContent() {
		listFragPartials = (ListView) pActivity
				.findViewById(R.id.ListView_projects_creative);

		solo.clickOnText(pActivity.getString(
				R.string.tabTitle_project_fragment_creative).toUpperCase(l));

		assertFalse(ProjectTechnicalFragment.isVisibleToUser());
		assertTrue(ProjectCreativeFragment.isVisibleToUser());

		ProjectsFragment pFragment = (ProjectCreativeFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 1);

		// count displayed projects
		int projectCountView = 0;
		for (int i = 0; i < listFragPartials.getChildCount(); i++) {
			View v = listFragPartials.getChildAt(i);
			projectCountView += ((GridView) v
					.findViewById(R.id.gridview_projects)).getCount();
		}

		int projectCountObject = 0;
		for (int i = 0; i < pFragment.getProjectGroups().size(); i++) {
			projectCountObject += pFragment.getProjectGroups().get(i)
					.getProjects().size();
		}

		assertTrue("Not all creative projects displayed",
				projectCountObject == projectCountView);

		/*
		 * click on every item and verify if its title is displayed in
		 * ProjectDetailsActivity
		 */
		for (int i = 0; i < listFragPartials.getChildCount(); i++) {
			for (int j = 0; j < ((GridView) listFragPartials.getChildAt(i)
					.findViewById(R.id.gridview_projects)).getCount(); j++) {
				Project p = pFragment.getProjectGroups().get(i)
						.getProjects().get(j);
				View projectItemView = ((GridView) listFragPartials.getChildAt(
						i).findViewById(R.id.gridview_projects)).getChildAt(j);
				solo.clickOnView(projectItemView);

				assertTrue(solo.searchText(p.getTitle()));
				solo.assertCurrentActivity(
						"activity should be ProjectDetailsActivity",
						ProjectDetailsActivity.class);
				solo.goBack();
			}
		}
	}

	public void testProjectTechnicalDetails() {
		listFragPartials = (ListView) pActivity
				.findViewById(R.id.ListView_projects_technical);

		ProjectsFragment tFragment = (ProjectTechnicalFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 0);

		View v = ((GridView) listFragPartials.getChildAt(0).findViewById(
				R.id.gridview_projects)).getChildAt(0);

		solo.clickOnView(v);
		assertTrue(solo.searchText(tFragment.getProjectGroups().get(0)
				.getProjects().get(0).getTitle()));

		// swipe through all projects and check title
		for (int i = 0; i < listFragPartials.getChildCount(); i++) {
			for (int j = 0; j < ((GridView) listFragPartials.getChildAt(i)
					.findViewById(R.id.gridview_projects)).getCount(); j++) {
				Project p = tFragment.getProjectGroups().get(i)
						.getProjects().get(j);
				assertTrue(solo.searchText(p.getTitle()));
				swipeToLeft(10);
			}
		}
	}

	public void testProjectCreativeDetails() {

		solo.clickOnText(pActivity.getString(
				R.string.tabTitle_project_fragment_creative).toUpperCase(l));

		listFragPartials = (ListView) pActivity
				.findViewById(R.id.ListView_projects_creative);

		ProjectsFragment cFragment = (ProjectCreativeFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 1);

		View v = ((GridView) listFragPartials.getChildAt(0).findViewById(
				R.id.gridview_projects)).getChildAt(0);

		solo.clickOnView(v);
		assertTrue(solo.searchText(cFragment.getProjectGroups().get(0)
				.getProjects().get(0).getTitle()));

		// swipe through all projects and check title
		for (int i = 0; i < listFragPartials.getChildCount(); i++) {
			for (int j = 0; j < ((GridView) listFragPartials.getChildAt(i)
					.findViewById(R.id.gridview_projects)).getCount(); j++) {
				Project p = cFragment.getProjectGroups().get(i)
						.getProjects().get(j);
				assertTrue(solo.searchText(p.getTitle()));
				swipeToLeft(10);
			}
		}
	}

	private void swipeToLeft(int stepCount) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		float xStart = width - 10;
		float xEnd = 10;
		solo.drag(xStart, xEnd, height / 2, height / 2, stepCount);
	}

	@SuppressWarnings("unused")
	private void swipeToRight(int stepCount) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		float xStart = 10;
		float xEnd = width - 10;
		solo.drag(xStart, xEnd, height / 2, height / 2, stepCount);
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
