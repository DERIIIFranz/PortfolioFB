package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;

public class ProjectCreativeFragment extends ProjectsFragment {
	private List<ProjectGroup> partialFragments = new ArrayList<ProjectGroup>();

	private ProjectGroup mPartialFrag1, mPartialFrag2;
	private List<Project> mProjects1, mProjects2;
	private static boolean sIsVisibleToUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setListView(R.layout.fragment_projects_creative);
		super.setPageTitle(R.string.pageTitle_activity_project_creative_details);

		if (partialFragments.isEmpty()) {

			Project.resetId();

			mProjects1 = new ArrayList<Project>();
			mProjects1.add(new Project(R.drawable.portrait1, getString(R.string.project_K13),
					R.layout.project_technical_noomix));
			mProjects1.add(new Project(R.drawable.portrait1, getString(R.string.project_TGTN),
					R.layout.project_technical_noomix));
			mProjects1.add(new Project(R.drawable.portrait1, getString(R.string.project_pirat),
					R.layout.project_technical_noomix));
			mProjects1.add(new Project(R.drawable.portrait1, getString(R.string.project_MOCAP),
					R.layout.project_technical_noomix));

			mPartialFrag1 = new ProjectGroup("3D", mProjects1);

			mProjects2 = new ArrayList<Project>();
			mProjects2.add(new Project(R.drawable.portrait1, getString(R.string.project_clip),
					R.layout.project_technical_noomix));

			mPartialFrag2 = new ProjectGroup("2D", mProjects2);

			partialFragments.add(mPartialFrag1);
			partialFragments.add(mPartialFrag2);

			super.setPartialFragments(partialFragments);

		}
	}

	// used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		ProjectCreativeFragment.sIsVisibleToUser = isVisibleToUser;
	}

	public static boolean isVisibleToUser() {
		return sIsVisibleToUser;
	}

	public static ProjectCreativeFragment newInstance(Context ctx) {
		ProjectCreativeFragment f = new ProjectCreativeFragment();
		
		Bundle args = new Bundle();
		args.putString(MainActivity.TAB_TITLE, ctx.getString(R.string.tabTitle_project_fragment_creative));
		
		f.setArguments(args);
		
		return f;
		
	}
}
