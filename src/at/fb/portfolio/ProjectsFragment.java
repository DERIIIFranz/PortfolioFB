package at.fb.portfolio;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import at.fb.portfolio.adapters.ProjectGroupAdapter;

/**
 * 
 * Overview of all Project(groups) of technical or creative projects
 * 
 */
public abstract class ProjectsFragment extends Fragment {

	private List<ProjectGroup> mProjectGroups;
	private int mPageTitle;

	public static final String FRAGMENT_PAGE_TITLE = "at.fb.portfolio.FRAGMENT_PAGE_TITLE";

	public List<ProjectGroup> getProjectGroups() {
		return mProjectGroups;
	}

	public void setProjectGroups(List<ProjectGroup> projectGroups) {
		mProjectGroups = projectGroups;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout rootView = (LinearLayout) inflater.inflate(
				R.layout.fragment_projects, container, false);

		ListView lView = (ListView) rootView.findViewById(R.id.lv_projects);
		lView.setAdapter(new ProjectGroupAdapter(getActivity(), mProjectGroups,
				mPageTitle));

		// if placeholder is defined (e.g. within layout for landscape-mode)
		// add additional fragment in order to avoid an extra detailActivity
		// and instead combine the project overview with the project's details

		View placeholder = rootView
				.findViewById(R.id.placeholder_project_fragment);

		if (placeholder != null) { 
			
			//assign the placeholder a unique id
			placeholder.setId(getPageTitle());
			
//TODO use savedInstanceState to store current project-id (positon) when orientation changed
			Bundle args = new Bundle();
			args.putParcelable(Project.PROJECT, mProjectGroups.get(0)
					.getProjects().get(0));
			args.putString(Project.PROJECT_CATEGORY, mProjectGroups.get(0)
					.getCategory());

			Fragment projectDetailsFragment = new ProjectDetailsFragment();
			projectDetailsFragment.setArguments(args);

			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(placeholder.getId(), projectDetailsFragment);
			ft.commit();
		}

		return rootView;
	}

	public int getPageTitle() {
		return mPageTitle;
	}

	public void setPageTitle(int pageTitle) {
		mPageTitle = pageTitle;
	}
}
