package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;

public class ProjectTechnicalFragment extends ProjectsFragment {

	private List<ProjectGroup> mPartialFragments = new ArrayList<ProjectGroup>();
	private static boolean sIsVisibleToUser;
	private ProjectGroup mPartialFrag1, mPartialFrag2, mPartialFrag3;
	private List<Project> mProjects1, mProjects2, mProjects3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setListView(R.layout.fragment_projects_technical);
		super.setPageTitle(R.string.pageTitle_activity_project_technical_details);

		if (mPartialFragments.isEmpty()) {

			Project.resetId();

			mProjects1 = new ArrayList<Project>();
			mProjects1.add(new Project(R.drawable.portrait1,
					R.drawable.img_project_noomix_header_white,
					getString(R.string.project_noomix_title),
					getString(R.string.project_noomix_description), null));
			mProjects1.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_atikon_title), "", null));
			mProjects1.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_freundlinger_title), "", null));
			mProjects1.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_artconsense_title), "", null));

			mPartialFrag1 = new ProjectGroup("Webentwicklung", mProjects1);

			mProjects2 = new ArrayList<Project>();
			mProjects2.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_sWatchdog_title), "", null));

			mPartialFrag2 = new ProjectGroup("Websicherheit", mProjects2);

			mProjects3 = new ArrayList<Project>();
			mProjects3.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_portfolioFB_title), "", null));
			mProjects3.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_x_title), "", null));

			mPartialFrag3 = new ProjectGroup("Apps", mProjects3);

			mPartialFragments.add(mPartialFrag1);
			mPartialFragments.add(mPartialFrag2);
			mPartialFragments.add(mPartialFrag3);

			super.setProjectGroups(mPartialFragments);

		}
	}

	// used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		ProjectTechnicalFragment.sIsVisibleToUser = isVisibleToUser;
	}

	public static boolean isVisibleToUser() {
		return sIsVisibleToUser;
	}

	public static ProjectTechnicalFragment newInstance(Context ctx) {
		ProjectTechnicalFragment f = new ProjectTechnicalFragment();

		Bundle args = new Bundle();
		args.putString(MainActivity.TAB_TITLE,
				ctx.getString(R.string.tabTitle_project_fragment_technical));

		f.setArguments(args);

		return f;

	}
}
