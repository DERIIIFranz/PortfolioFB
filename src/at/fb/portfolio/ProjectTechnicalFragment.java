package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class ProjectTechnicalFragment extends ProjectsFragment {

	private List<Project> projects = new ArrayList<Project>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (projects.isEmpty()) {
			//wozu pageTitle wenn schon in xml definiert?
			this.pageTitle = R.string.pageTitle_activity_project_technical_details;

			projects.add(new Project(R.drawable.ic_picture1, "Noomix",
					R.layout.project_technical_noomix));
			projects.add(new Project(R.drawable.ic_picture1, "Projekt 2",
					R.layout.project_technical_noomix));
			projects.add(new Project(R.drawable.ic_picture1, "Projekt 3",
					R.layout.project_technical_noomix));
			projects.add(new Project(R.drawable.ic_picture1, "Projekt 4",
					R.layout.project_technical_noomix));
			projects.add(new Project(R.drawable.ic_picture1, "Projekt 5",
					R.layout.project_technical_noomix));
			super.setProjects(projects);
		}
	}

}
