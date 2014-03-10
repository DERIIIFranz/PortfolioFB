package at.fb.portfolio;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import at.fb.portfolio.adapter.PartialFragAdapter;

public abstract class ProjectsFragment extends Fragment {

	private int mListView;
	private List<ProjectPartialFragment> mPartialFragments;
	private int mPageTitle;

	public static final String FRAGMENT_PAGE_TITLE = "at.fb.portfolio.FRAGMENT_PAGE_TITLE";

	public int getListView() {
		return mListView;
	}

	public void setListView(int listView) {
		this.mListView = listView;
	}

	public List<ProjectPartialFragment> getPartialFragments() {
		return mPartialFragments;
	}

	public void setPartialFragments(List<ProjectPartialFragment> partialFragments) {
		this.mPartialFragments = partialFragments;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(mListView, container, false);

		ListView lView = (ListView) rootView;
		lView.setAdapter(new PartialFragAdapter(getActivity(),
				mPartialFragments, getActivity().getString(mPageTitle)));

		return rootView;
	}

	public int getPageTitle() {
		return mPageTitle;
	}

	public void setPageTitle(int pageTitle) {
		this.mPageTitle = pageTitle;
	}
}
