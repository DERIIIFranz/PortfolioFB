package at.fb.portfolio;

import java.util.List;

public class PartialFragment {
	
	private String category;
	private List<Project> projects;
	
	public PartialFragment(String category, List<Project> projects) {
		this.category = category;
		this.projects = projects;
	}
	
	public String getCategory() {
		return category;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

}
