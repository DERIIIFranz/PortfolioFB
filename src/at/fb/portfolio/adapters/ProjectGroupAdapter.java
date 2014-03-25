package at.fb.portfolio.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import at.fb.portfolio.ProjectGroup;
import at.fb.portfolio.Project;
import at.fb.portfolio.ProjectDetailsActivity;
import at.fb.portfolio.ProjectsFragment;
import at.fb.portfolio.R;
import at.fb.portfolio.views.NonScrollableGridView;

public class ProjectGroupAdapter extends BaseAdapter {

	private Activity mContext;
	private List<ProjectGroup> mProjectGroups;
	private String mPageTitle;

	/**
	 * Supplies projectpage with several ProjectGroups
	 * 
	 * @param act
	 *            corresponding activity
	 * @param projectGroups
	 *            list of groups
	 * @param pageTitle
	 */
	public ProjectGroupAdapter(Activity act, List<ProjectGroup> projectGroups,
			String pageTitle) {
		this.mPageTitle = pageTitle;
		mContext = act;
		this.mProjectGroups = projectGroups;
	}

	@Override
	public int getCount() {
		return mProjectGroups.size();
	}

	@Override
	public Object getItem(int position) {
		return mProjectGroups.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater vi = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View projectGroupView = vi.inflate(R.layout.group_project, null);

		TextView label = (TextView) projectGroupView
				.findViewById(R.id.text_project_category);
		label.setText(mProjectGroups.get(position).getCategory());

		final NonScrollableGridView gView = (NonScrollableGridView) projectGroupView
				.findViewById(R.id.gridview_projects);
		gView.setAdapter(new ProjectAdapter(mContext, mProjectGroups.get(
				position).getProjects()));

		gView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				ArrayList<Project> allProjects = new ArrayList<Project>();
				for (int i = 0; i < mProjectGroups.size(); i++) {
					for (int j = 0; j < mProjectGroups.get(i).getProjects()
							.size(); j++) {
						allProjects.add(mProjectGroups.get(i).getProjects()
								.get(j));
					}
				}

				Intent intent = new Intent(mContext,
						ProjectDetailsActivity.class);
				// use id as index here, as position only determines position
				// within projectGroup
				intent.putExtra(Project.PROJECT_POSITION, (int) id);
				intent.putExtra(ProjectsFragment.FRAGMENT_PAGE_TITLE,
						mPageTitle);
				intent.putParcelableArrayListExtra(Project.PROJECT_COLLECTION,
						allProjects);
				mContext.startActivity(intent);
			}
		});
		return projectGroupView;
	}

}
