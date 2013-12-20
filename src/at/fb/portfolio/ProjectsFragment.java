package at.fb.portfolio;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import at.fb.portfolio.adapter.PartialFragAdapter;

public class ProjectsFragment extends Fragment {
	
	private int listView;
	private List<PartialFragment> partialFragments;
	private int pageTitle;
	
	public static final String FRAGMENT_PAGE_TITLE = "at.fb.portfolio.FRAGMENT_PAGE_TITLE";

	public int getListView() {
		return listView;
	}

	public void setListView(int listView) {
		this.listView = listView;
	}

	public List<PartialFragment> getPartialFragments() {
		return partialFragments;
	}

	public void setPartialFragments(List<PartialFragment> partialFragments) {
		this.partialFragments = partialFragments;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(listView, container, false);
		
		ListView lView = (ListView) rootView;
		lView.setAdapter(new PartialFragAdapter(getActivity(), partialFragments, getActivity().getString(pageTitle)));
		
		return rootView;
	}

	public int getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(int pageTitle) {
		this.pageTitle = pageTitle;
	}
}
