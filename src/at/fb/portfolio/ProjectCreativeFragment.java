package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;

public class ProjectCreativeFragment extends ProjectsFragment {
	private List<ProjectPartialFragment> partialFragments = new ArrayList<ProjectPartialFragment>();

	private ProjectPartialFragment pFrag1, pFrag2;
	private List<Project> projects1, projects2;
	private static boolean isVisibleToUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setListView(R.layout.fragment_projects_creative);
		super.setPageTitle(R.string.pageTitle_activity_project_creative_details);

		if (partialFragments.isEmpty()) {

			Project.resetId();

			projects1 = new ArrayList<Project>();
			projects1.add(new Project(R.drawable.portrait1, getString(R.string.project_K13),
					R.layout.project_technical_noomix));
			projects1.add(new Project(R.drawable.portrait1, getString(R.string.project_TGTN),
					R.layout.project_technical_noomix));
			projects1.add(new Project(R.drawable.portrait1, getString(R.string.project_pirat),
					R.layout.project_technical_noomix));
			projects1.add(new Project(R.drawable.portrait1, getString(R.string.project_MOCAP),
					R.layout.project_technical_noomix));

			pFrag1 = new ProjectPartialFragment("3D", projects1);

			projects2 = new ArrayList<Project>();
			projects2.add(new Project(R.drawable.portrait1, getString(R.string.project_clip),
					R.layout.project_technical_noomix));

			pFrag2 = new ProjectPartialFragment("2D", projects2);

			partialFragments.add(pFrag1);
			partialFragments.add(pFrag2);

			super.setPartialFragments(partialFragments);

		}
	}

	// used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		ProjectCreativeFragment.isVisibleToUser = isVisibleToUser;
	}

	public static boolean isVisibleToUser() {
		return isVisibleToUser;
	}

	public static ProjectCreativeFragment newInstance(Context ctx) {
		ProjectCreativeFragment f = new ProjectCreativeFragment();
		
		Bundle args = new Bundle();
		args.putString(MainActivity.TAB_TITLE, ctx.getString(R.string.tabTitle_project_fragment_creative));
		
		f.setArguments(args);
		
		return f;
		
	}
}
