package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;

public class ProjectTechnicalFragment extends ProjectsFragment {

	private List<ProjectPartialFragment> partialFragments = new ArrayList<ProjectPartialFragment>();
	private static boolean isVisibleToUser;
	private ProjectPartialFragment pFrag1, pFrag2, pFrag3;
	private List<Project> projects1, projects2, projects3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setListView(R.layout.fragment_projects_technical);
		super.setPageTitle(R.string.pageTitle_activity_project_technical_details);

		if (partialFragments.isEmpty()) {

			Project.resetId();

			projects1 = new ArrayList<Project>();
			projects1.add(new Project(R.drawable.portrait1, getString(R.string.project_noomix),
					R.layout.project_technical_noomix));
			projects1.add(new Project(R.drawable.portrait1, getString(R.string.project_atikon),
					R.layout.project_technical_noomix));
			projects1.add(new Project(R.drawable.portrait1, getString(R.string.project_freundlinger),
					R.layout.project_technical_noomix));
			projects1.add(new Project(R.drawable.portrait1, getString(R.string.project_artconsense),
					R.layout.project_technical_noomix));

			pFrag1 = new ProjectPartialFragment("Webentwicklung", projects1);

			projects2 = new ArrayList<Project>();
			projects2.add(new Project(R.drawable.portrait1, getString(R.string.project_sWatchdog),
					R.layout.project_technical_noomix));

			pFrag2 = new ProjectPartialFragment("Websicherheit", projects2);

			projects3 = new ArrayList<Project>();
			projects3.add(new Project(R.drawable.portrait1, getString(R.string.project_portfolioFB),
					R.layout.project_technical_noomix));
			projects3.add(new Project(R.drawable.portrait1, getString(R.string.project_x),
					R.layout.project_technical_noomix));

			pFrag3 = new ProjectPartialFragment("Apps", projects3);

			partialFragments.add(pFrag1);
			partialFragments.add(pFrag2);
			partialFragments.add(pFrag3);

			super.setPartialFragments(partialFragments);

		}
	}

	// used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		ProjectTechnicalFragment.isVisibleToUser = isVisibleToUser;
	}

	public static boolean isVisibleToUser() {
		return isVisibleToUser;
	}
	
	public static ProjectTechnicalFragment newInstance(Context ctx) {
		ProjectTechnicalFragment f = new ProjectTechnicalFragment();
		
		Bundle args = new Bundle();
		args.putString(MainActivity.TAB_TITLE, ctx.getString(R.string.tabTitle_project_fragment_technical));
		
		f.setArguments(args);
		
		return f;
		
	}
}
