package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class ProjectCreativeFragment extends ProjectsFragment {

	private List<Project> projects = new ArrayList<Project>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (projects.isEmpty()) {
			this.pageTitle = R.string.pageTitle_activity_project_creative_details;

			projects.add(new Project(R.drawable.ic_picture1, "K13",
					R.layout.project_technical_noomix));
			projects.add(new Project(R.drawable.ic_picture1, "TGTN",
					R.layout.project_technical_noomix));
			projects.add(new Project(R.drawable.ic_picture1, "Pirat",
					R.layout.project_technical_noomix));
			super.setProjects(projects);
		}
	}
}
