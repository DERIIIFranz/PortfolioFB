package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class ProjectCreativeFragment extends ProjectsFragment {

	private List<Project> projects = new ArrayList<Project>();
	private static boolean isVisibleToUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setProjectsGrid(R.layout.partial_fragment_projects_creative);
		super.setPageTitle(R.string.pageTitle_activity_project_creative_details);
		
		if (projects.isEmpty()) {

			projects.add(new Project(R.drawable.ic_picture1, "K13",
					R.layout.project_technical_noomix));
			projects.add(new Project(R.drawable.ic_picture1, "TGTN",
					R.layout.project_technical_noomix));
			projects.add(new Project(R.drawable.ic_picture1, "Pirat",
					R.layout.project_technical_noomix));
			super.setProjects(projects);
		}
	}
	
	//used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
	    super.setUserVisibleHint(isVisibleToUser);

	    ProjectCreativeFragment.isVisibleToUser = isVisibleToUser;
	}
	
	public static boolean isVisibleToUser() {
		return isVisibleToUser;
	}
}
