package at.fb.portfolio;

import java.util.List;

public class ProjectGroup {
	
	private String mCategory;
	private List<Project> mProjects;
	
	public ProjectGroup(String category, List<Project> projects) {
		this.mCategory = category;
		this.mProjects = projects;
	}
	
	public String getCategory() {
		return mCategory;
	}
	
	public List<Project> getProjects() {
		return mProjects;
	}

}
