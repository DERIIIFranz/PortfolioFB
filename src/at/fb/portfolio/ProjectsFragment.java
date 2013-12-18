package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import at.fb.portfolio.adapter.ProjectAdapter;

public abstract class ProjectsFragment extends Fragment {

	List<Project> projects;
	private int pageTitle;
	private int tabTitle;
	private int projectsGrid;

	public static final String FRAGMENT_PAGE_TITLE = "at.fb.portfolio.FRAGMENT_PAGE_TITLE";

	public int getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(int pageTitle) {
		this.pageTitle = pageTitle;
	}

	public int getTabTitle() {
		return tabTitle;
	}

	public void setTabTitle(int tabTitle) {
		this.tabTitle = tabTitle;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjectsGrid(int projectsGrid) {
		this.projectsGrid = projectsGrid;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(projectsGrid, container, false);

	//	GridView gridview = (GridView) rootView.findViewById(projectsGrid);
		
		GridView gridview = (GridView) rootView;
		gridview.setAdapter(new ProjectAdapter(this.getActivity(), projects));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent intent = new Intent(getActivity(),
						ProjectDetailsActivity.class);
				intent.putExtra(Project.PROJECT_LAYOUT,
						R.layout.activity_project_details);
				intent.putExtra(Project.PROJECT_POSITION, position);
				intent.putExtra(ProjectsFragment.FRAGMENT_PAGE_TITLE, pageTitle);
				intent.putParcelableArrayListExtra(Project.PROJECT_COLLECTION,
						(ArrayList<Project>) projects);
				startActivity(intent);
			}
		});

		return rootView;
	}
}
