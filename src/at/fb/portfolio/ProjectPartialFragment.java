package at.fb.portfolio;

import java.util.List;

public class ProjectPartialFragment {
	
	private String mCategory;
	private List<Project> mProjects;
	
	public ProjectPartialFragment(String category, List<Project> projects) {
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
