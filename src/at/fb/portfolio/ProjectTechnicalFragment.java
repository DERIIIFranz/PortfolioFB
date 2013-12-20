package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class ProjectTechnicalFragment extends ProjectsFragment {

	private List<PartialFragment> partialFragments = new ArrayList<PartialFragment>();
	private static boolean isVisibleToUser;
	private PartialFragment pFrag1, pFrag2, pFrag3;
	private List<Project> projects1, projects2, projects3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setListView(R.layout.fragment_projects_technical);
		super.setPageTitle(R.string.pageTitle_activity_project_technical_details);

		if (partialFragments.isEmpty()) {

			Project.resetId();

			projects1 = new ArrayList<Project>();
			projects1.add(new Project(R.drawable.ic_picture1, "Noomix",
					R.layout.project_technical_noomix));
			projects1.add(new Project(R.drawable.ic_picture1, "Atikon",
					R.layout.project_technical_noomix));
			projects1.add(new Project(R.drawable.ic_picture1, "Freundlinger",
					R.layout.project_technical_noomix));
			projects1.add(new Project(R.drawable.ic_picture1, "Artconsense",
					R.layout.project_technical_noomix));

			pFrag1 = new PartialFragment("Webentwicklung", projects1);

			projects2 = new ArrayList<Project>();
			projects2.add(new Project(R.drawable.ic_picture1, "S-Watchdog",
					R.layout.project_technical_noomix));

			pFrag2 = new PartialFragment("Websicherheit", projects2);

			projects3 = new ArrayList<Project>();
			projects3.add(new Project(R.drawable.ic_picture1, "PortfolioFB",
					R.layout.project_technical_noomix));
			projects3.add(new Project(R.drawable.ic_picture1, "?",
					R.layout.project_technical_noomix));

			pFrag3 = new PartialFragment("Apps", projects3);

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
}
