package at.fb.portfolio.test;

import java.util.Locale;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
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
	private GridView projectsGrid;

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

	public void testProjectsGrid_layout() {
		projectsGrid = (GridView) pActivity
				.findViewById(R.id.gridview_technical_projects);
		final View decorView = pActivity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, projectsGrid);
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
		
		projectsGrid = (GridView) pActivity
				.findViewById(R.id.gridview_technical_projects);

		assertTrue(ProjectTechnicalFragment.isVisibleToUser());

		ProjectsFragment pFragment = (ProjectTechnicalFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 0);

		assertTrue("Not all technical projects displayed", pFragment.getProjects()
				.size() == projectsGrid.getCount());

		/*
		 * click on every item and verify if its title is displayed
		 * in ProjectDetailsActivity
		 */
		for (int i = 0; i < pFragment.getProjects().size(); i++) {
			Project p = pFragment.getProjects().get(i);
			View projectItemView = projectsGrid.getChildAt(i);
			solo.clickOnView(projectItemView);

			assertTrue(solo.searchText(p.getTitle()));
			solo.assertCurrentActivity(
					"activity should be ProjectDetailsActivity",
					ProjectDetailsActivity.class);
			solo.goBack();
		}
	}

	public void testCreativeContent() {
		projectsGrid = (GridView) pActivity
				.findViewById(R.id.gridview_creative_projects);
		
		solo.clickOnText(pActivity.getString(
				R.string.tabTitle_project_fragment_creative).toUpperCase(l));

		assertFalse(ProjectTechnicalFragment.isVisibleToUser());
		assertTrue(ProjectCreativeFragment.isVisibleToUser());
		
		ProjectsFragment pFragment = (ProjectCreativeFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 1);
		
		assertTrue("Not all creative projects displayed", pFragment.getProjects()
				.size() == projectsGrid.getCount());

		/*
		 * click on every item and verify if its title is displayed
		 * in ProjectDetailsActivity
		 */
		for (int i = 0; i < pFragment.getProjects().size(); i++) {
			Project p = pFragment.getProjects().get(i);
			View projectItemView = projectsGrid.getChildAt(i);
			solo.clickOnView(projectItemView);

			assertTrue(solo.searchText(p.getTitle()));
			solo.assertCurrentActivity(
					"activity should be ProjectDetailsActivity",
					ProjectDetailsActivity.class);
			solo.goBack();
		}
	}
	
	public void testProjectTechnicalDetails() {
		ProjectsFragment tFragment = (ProjectTechnicalFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 0);
		
		GridView projectsTGrid = (GridView) pActivity
				.findViewById(R.id.gridview_technical_projects);
		
		solo.clickOnView(projectsTGrid.getChildAt(0));
		assertTrue(solo.searchText(tFragment.getProjects().get(0).getTitle()));
		
		for(int i = 1; i < projectsTGrid.getCount(); i++) {
			swipeToLeft(10);
			assertTrue(solo.searchText(tFragment.getProjects().get(i).getTitle()));
		}
	}
	
	public void testProjectCreativeDetails() {
		ProjectsFragment cFragment = (ProjectCreativeFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 1);
		
		GridView projectsCGrid = (GridView) pActivity
				.findViewById(R.id.gridview_technical_projects);
		
		solo.clickOnView(projectsCGrid.getChildAt(0));
		assertTrue(solo.searchText(cFragment.getProjects().get(0).getTitle()));
		
		for(int i = 1; i < projectsCGrid.getCount(); i++) {
			swipeToLeft(10);
			assertTrue(solo.searchText(cFragment.getProjects().get(i).getTitle()));
		}
	}
	
	private void swipeToLeft(int stepCount) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
	    float xStart = width - 10 ;
	    float xEnd = 10;
	    solo.drag(xStart, xEnd, height / 2, height / 2, stepCount);
	}

	@SuppressWarnings("unused")
	private void swipeToRight(int stepCount) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
	    float xStart = 10 ;
	    float xEnd = width - 10;
	    solo.drag(xStart, xEnd, height / 2, height / 2, stepCount);
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
