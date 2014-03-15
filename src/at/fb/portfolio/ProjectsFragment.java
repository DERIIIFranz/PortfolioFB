package at.fb.portfolio;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import at.fb.portfolio.adapters.ProjectGroupAdapter;

public abstract class ProjectsFragment extends Fragment {

	private int mListView;
	private List<ProjectGroup> mProjectGroups;
	private int mPageTitle;

	public static final String FRAGMENT_PAGE_TITLE = "at.fb.portfolio.FRAGMENT_PAGE_TITLE";

	public int getListView() {
		return mListView;
	}

	public void setListView(int listView) {
		mListView = listView;
	}

	public List<ProjectGroup> getProjectGroups() {
		return mProjectGroups;
	}

	public void setProjectGroups(List<ProjectGroup> projectGroups) {
		mProjectGroups = projectGroups;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(mListView, container, false);

		ListView lView = (ListView) rootView;
		lView.setAdapter(new ProjectGroupAdapter(getActivity(), mProjectGroups,
				getActivity().getString(mPageTitle)));

		return rootView;
	}

	public int getPageTitle() {
		return mPageTitle;
	}

	public void setPageTitle(int pageTitle) {
		mPageTitle = pageTitle;
	}
}
