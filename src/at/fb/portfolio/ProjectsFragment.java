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
import at.fb.portfolio.projectItems.ProjectItemVideo;

/**
 * 
 * Overview of all Project(groups) of technical or creative projects
 * 
 */
public abstract class ProjectsFragment extends Fragment {

	private List<ProjectGroup> mProjectGroups;
	private int mPageTitle;
	private int mCurrentProjectPos, mCurrentProjectGroupPos,
			mCurrentVideoPos = 0;

	private LinearLayout rootView;
	private View placeholder;

	public static final String FRAGMENT_PAGE_TITLE = "at.fb.portfolio.FRAGMENT_PAGE_TITLE";
	public static final String FRAGMENT_CLASS_NAME = "at.fb.portfolio.FRAGMENT_CLASS_NAME";

	public List<ProjectGroup> getProjectGroups() {
		return mProjectGroups;
	}

	protected void setProjectGroups(List<ProjectGroup> projectGroups) {
		mProjectGroups = projectGroups;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = (LinearLayout) inflater.inflate(R.layout.fragment_projects,
				container, false);

		ListView lView = (ListView) rootView.findViewById(R.id.lv_projects);
		lView.setAdapter(new ProjectGroupAdapter(getActivity(),
				this.getClass(), mProjectGroups, mPageTitle));

		// if placeholder is defined (e.g. within layout for landscape-mode)
		// add additional fragment in order to avoid an extra detailActivity
		// and instead combine the project overview with the project's details

		placeholder = rootView.findViewById(R.id.placeholder_project_fragment);

		if (placeholder != null) {
			// assign the placeholder a unique id
			placeholder.setId(getPageTitle());
			placeholder.setTag("placeholder");

			replaceFragment();
		}

		restoreVideoPos();

		return rootView;
	}

	private void restoreVideoPos() {
		Project p = mProjectGroups.get(mCurrentProjectGroupPos).getProjects()
				.get(mCurrentProjectPos);

		for (int i = 0; i < p.getProjectItems().size(); i++) {
			if (p.getProjectItems().get(i).getClass()
					.equals(ProjectItemVideo.class)) {
				((ProjectItemVideo) p.getProjectItems().get(i))
						.setPos(mCurrentVideoPos);
			}
		}
	}

	private void replaceFragment() {

		Bundle args = new Bundle();

		args.putParcelable(
				Project.PROJECT,
				mProjectGroups.get(mCurrentProjectGroupPos).getProjects()
						.get(mCurrentProjectPos));
		args.putString(Project.PROJECT_CATEGORY,
				mProjectGroups.get(mCurrentProjectGroupPos).getCategory());

		Fragment projectDetailsFragment = new ProjectDetailsFragment();
		projectDetailsFragment.setArguments(args);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(rootView.findViewWithTag("placeholder").getId(),
				projectDetailsFragment);
		ft.commit();

	}

	/**
	 * set the default project that is shown on startup
	 * 
	 * @param groupPos
	 *            Number of ProjectGroup, the project is located in
	 * @param projectPos
	 *            relative position within ProjectGroup
	 */
	public void setCurrentProject(int groupPos, int projectPos) {
		mCurrentProjectGroupPos = groupPos;
		mCurrentProjectPos = projectPos;
	}

	/**
	 * set current video position. Has only effect if current project uses a
	 * ProjectItemVideo
	 * 
	 * @param pos
	 *            recent videoposition
	 */
	public void setCurrentVideoPos(int pos) {
		mCurrentVideoPos = pos;
	}

	public int getPageTitle() {
		return mPageTitle;
	}

	public void setPageTitle(int pageTitle) {
		mPageTitle = pageTitle;
	}
}
