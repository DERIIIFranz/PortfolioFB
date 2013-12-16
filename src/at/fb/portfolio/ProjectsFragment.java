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
	protected int pageTitle;
	protected int tabTitle;
	
	public static final String FRAGMENT_PAGE_TITLE = "at.fb.portfolio.FRAGMENT_PAGE_TITLE";
	
	public int getPageTitle() {
		return pageTitle;
	}
	
	public int getTabTitle() {
		return tabTitle;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_projects_grid,
				container, false);

		GridView gridview = (GridView) rootView
				.findViewById(R.id.gridview_projects);
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
