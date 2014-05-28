package at.fb.portfolio.test;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Instrumentation;
import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import at.fb.portfolio.Project;
import at.fb.portfolio.ProjectActivity;
import at.fb.portfolio.ProjectCreativeFragment;
import at.fb.portfolio.ProjectDetailsActivity;
import at.fb.portfolio.ProjectGroup;
import at.fb.portfolio.ProjectTechnicalFragment;
import at.fb.portfolio.ProjectsFragment;
import at.fb.portfolio.R;

import com.robotium.solo.Solo;

public class ProjectActivityTest extends
		ActivityInstrumentationTestCase2<ProjectActivity> {

	private Solo solo;

	private ProjectActivity pActivity;
	private ViewPager projectPager;
	private Locale l;
	private ListView listProjectGroups;

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
		listProjectGroups = (ListView) pActivity.findViewById(R.id.lv_projects);

		final View decorView = pActivity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, listProjectGroups);
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

		listProjectGroups = (ListView) pActivity.findViewById(R.id.lv_projects);

		assertTrue(ProjectTechnicalFragment.isVisibleToUser());

		ProjectsFragment pFragment = (ProjectTechnicalFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 0);

		// count displayed projects
		int projectCountView = 0;
		for (int i = 0; i < listProjectGroups.getChildCount(); i++) {
			View v = listProjectGroups.getChildAt(i);
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
		for (int i = 0; i < listProjectGroups.getChildCount(); i++) {
			for (int j = 0; j < ((GridView) listProjectGroups.getChildAt(i)
					.findViewById(R.id.gridview_projects)).getCount(); j++) {
				Project p = pFragment.getProjectGroups().get(i).getProjects()
						.get(j);
				View projectItemView = ((GridView) listProjectGroups
						.getChildAt(i).findViewById(R.id.gridview_projects))
						.getChildAt(j);
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

		solo.clickOnText(pActivity.getString(
				R.string.tabTitle_project_fragment_creative).toUpperCase(l));

		listProjectGroups = (ListView) solo.getView(R.id.lv_projects, 1);

		assertFalse(ProjectTechnicalFragment.isVisibleToUser());
		assertTrue(ProjectCreativeFragment.isVisibleToUser());

		ProjectsFragment pFragment = (ProjectCreativeFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 1);

		// count displayed projects
		int projectCountView = 0;
		for (int i = 0; i < listProjectGroups.getChildCount(); i++) {
			View v = listProjectGroups.getChildAt(i);
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
		for (int i = 0; i < listProjectGroups.getChildCount(); i++) {
			for (int j = 0; j < ((GridView) listProjectGroups.getChildAt(i)
					.findViewById(R.id.gridview_projects)).getCount(); j++) {
				Project p = pFragment.getProjectGroups().get(i).getProjects()
						.get(j);
				View projectItemView = ((GridView) listProjectGroups
						.getChildAt(i).findViewById(R.id.gridview_projects))
						.getChildAt(j);
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
		listProjectGroups = (ListView) pActivity.findViewById(R.id.lv_projects);

		ProjectsFragment tFragment = (ProjectTechnicalFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 0);

		View v = ((GridView) listProjectGroups.getChildAt(0).findViewById(
				R.id.gridview_projects)).getChildAt(0);

		solo.clickOnView(v);
		assertTrue(solo.searchText(tFragment.getProjectGroups().get(0)
				.getProjects().get(0).getTitle()));

		// swipe through all projects and check title
		for (int i = 0; i < listProjectGroups.getChildCount(); i++) {
			for (int j = 0; j < ((GridView) listProjectGroups.getChildAt(i)
					.findViewById(R.id.gridview_projects)).getCount(); j++) {
				Project p = tFragment.getProjectGroups().get(i).getProjects()
						.get(j);
				assertTrue(solo.searchText(p.getTitle()));
				swipeToLeft(1);
			}
		}
	}

	public void testProjectCreativeDetails() {

		solo.clickOnText(pActivity.getString(
				R.string.tabTitle_project_fragment_creative).toUpperCase(l));

		listProjectGroups = (ListView) solo.getView(R.id.lv_projects, 1);

		ProjectsFragment cFragment = (ProjectCreativeFragment) projectPager
				.getAdapter().instantiateItem(projectPager, 1);

		View v = ((GridView) listProjectGroups.getChildAt(0).findViewById(
				R.id.gridview_projects)).getChildAt(0);

		solo.clickOnView(v);
		assertTrue(solo.searchText(cFragment.getProjectGroups().get(0)
				.getProjects().get(0).getTitle()));

		// swipe through all projects and check title
		for (int i = 0; i < listProjectGroups.getChildCount(); i++) {
			for (int j = 0; j < ((GridView) listProjectGroups.getChildAt(i)
					.findViewById(R.id.gridview_projects)).getCount(); j++) {
				Project p = cFragment.getProjectGroups().get(i).getProjects()
						.get(j);
				assertTrue(solo.searchText(p.getTitle()));
				swipeToLeft(1);
			}
		}
	}

	public void testHorizontalLayout_technicalProjects() {
		solo.setActivityOrientation(Solo.LANDSCAPE);

		swipeToRight(1); // get focus

		ListView listProjectGroups = (ListView) solo.getView(R.id.lv_projects,
				0);

		ArrayList<Project> projectList = new ArrayList<Project>();

		for (int i = 0; i < listProjectGroups.getAdapter().getCount(); i++) {
			for (int j = 0; j < ((ProjectGroup) listProjectGroups.getAdapter()
					.getItem(i)).getProjects().size(); j++) {

				projectList.add(((ProjectGroup) listProjectGroups.getAdapter()
						.getItem(i)).getProjects().get(j));
			}
		}

		int helper = 0;
		ArrayList<ImageView> views = solo.getCurrentViews(ImageView.class,
				listProjectGroups);
		for (int i = 0; i < projectList.size(); i++) {

			if (views.size() == helper) {
				solo.scrollDown();
				solo.sleep(50);
				views = solo
						.getCurrentViews(ImageView.class, listProjectGroups);
				helper = 0;
				solo.sleep(50);
			}
			solo.clickOnView(views.get(helper));
			solo.sleep(50);

			assertNotNull(
					"First view of Project " + projectList.get(i).getTitle()
							+ " is not found in current window",
					solo.getViews(projectList
							.get(i)
							.getProjectItems()
							.get(0)
							.getView(
									solo.getCurrentActivity()
											.findViewById(R.id.ll_project_details),
									null)));

			helper++;
		}
	}

	public void testHorizontalLayout_creativeProjects() {
		solo.setActivityOrientation(Solo.LANDSCAPE);

		swipeToLeft(1);

		ListView listProjectGroups = (ListView) solo.getView(R.id.lv_projects,
				1);

		ArrayList<Project> projectList = new ArrayList<Project>();

		for (int i = 0; i < listProjectGroups.getAdapter().getCount(); i++) {
			for (int j = 0; j < ((ProjectGroup) listProjectGroups.getAdapter()
					.getItem(i)).getProjects().size(); j++) {

				projectList.add(((ProjectGroup) listProjectGroups.getAdapter()
						.getItem(i)).getProjects().get(j));
			}
		}

		int helper = 0;
		ArrayList<ImageView> views = solo.getCurrentViews(ImageView.class,
				listProjectGroups);
		for (int i = 0; i < projectList.size(); i++) {

			if (views.size() == helper) {
				solo.scrollDown();
				solo.sleep(50);
				views = solo
						.getCurrentViews(ImageView.class, listProjectGroups);
				helper = 0;
				solo.sleep(50);
			}
			solo.clickOnView(views.get(helper));
			solo.sleep(250);

			assertNotNull(
					"First view of Project " + projectList.get(i).getTitle()
							+ " is not found in current window",
					solo.getViews(projectList
							.get(i)
							.getProjectItems()
							.get(0)
							.getView(
									solo.getCurrentActivity()
											.findViewById(R.id.ll_project_details),
									null)));

			helper++;
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
		solo.sleep(100);
	}

	private void swipeToRight(int stepCount) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		float xStart = 10;
		float xEnd = width - 10;
		solo.drag(xStart, xEnd, height / 2, height / 2, stepCount);
		solo.sleep(100);
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	public View getViewAtIndex(final ListView listElement,
			final int indexInList, Instrumentation instrumentation) {
		ListView parent = listElement;
		if (parent != null) {
			if (indexInList <= parent.getAdapter().getCount()) {
				scrollListTo(parent, indexInList, instrumentation);
				int indexToUse = indexInList - parent.getFirstVisiblePosition();
				return parent.getChildAt(indexToUse);
			}
		}
		return null;
	}

	public <T extends AbsListView> void scrollListTo(final T listView,
			final int index, Instrumentation instrumentation) {
		instrumentation.runOnMainSync(new Runnable() {
			@Override
			public void run() {
				listView.setSelection(index);
			}
		});
		instrumentation.waitForIdleSync();
	}
}
