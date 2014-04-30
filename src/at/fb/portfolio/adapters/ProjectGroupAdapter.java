package at.fb.portfolio.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import at.fb.portfolio.Project;
import at.fb.portfolio.ProjectDetailsActivity;
import at.fb.portfolio.ProjectDetailsFragment;
import at.fb.portfolio.ProjectGroup;
import at.fb.portfolio.ProjectsFragment;
import at.fb.portfolio.R;
import at.fb.portfolio.views.NonScrollableGridView;

public class ProjectGroupAdapter extends BaseAdapter {

	private FragmentActivity mActivity;
	private List<ProjectGroup> mProjectGroups;
	private int mPageTitle;

	/**
	 * Supplies projectpage with several ProjectGroups
	 * 
	 * @param act
	 *            corresponding activity
	 * @param projectGroups
	 *            list of groups
	 * @param pageTitle
	 */
	public ProjectGroupAdapter(FragmentActivity act,
			List<ProjectGroup> projectGroups, int pageTitle) {
		this.mPageTitle = pageTitle;
		mActivity = act;
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
		LayoutInflater vi = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View projectGroupView = vi.inflate(R.layout.group_project, null);

		TextView label = (TextView) projectGroupView
				.findViewById(R.id.text_project_category);

		label.setText(mProjectGroups.get(position).getCategory());

		final NonScrollableGridView gView = (NonScrollableGridView) projectGroupView
				.findViewById(R.id.gridview_projects);
		gView.setAdapter(new ProjectAdapter(mActivity, mProjectGroups.get(
				position).getProjects()));

		gView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				// determine projectGroupId for requested item
				int projectId = (int) id;
				int groupId;
				for (groupId = 0; (projectId - mProjectGroups.get(groupId)
						.getProjects().size()) >= 0; groupId++) {
					projectId -= mProjectGroups.get(groupId).getProjects()
							.size();
				}

				// use the unique id assigned to the placeholder in
				// ProjectFragments.java
				View placeholder = mActivity.findViewById(mPageTitle);

				// if placeholder is defined (e.g. within layout for
				// landscape-mode) add additional fragment in order to avoid an
				// extra detailActivity and instead combine the project overview
				// with the project's details
				if (placeholder != null) {

					Bundle args = new Bundle();
					args.putParcelable(
							Project.PROJECT,
							mProjectGroups.get(groupId).getProjects()
									.get(projectId));
					args.putString(Project.PROJECT_CATEGORY, mProjectGroups
							.get(groupId).getCategory());

					Fragment projectDetailsFragment = new ProjectDetailsFragment();
					projectDetailsFragment.setArguments(args);

					FragmentTransaction ft = mActivity
							.getSupportFragmentManager().beginTransaction();
					ft.replace(placeholder.getId(), projectDetailsFragment);
					ft.commit();
				}

				else {

					Intent intent = new Intent(mActivity,
							ProjectDetailsActivity.class);
					// use "id" as index here, as "position" only determines
					// position within projectGroup
					intent.putExtra(Project.PROJECT_POSITION, (int) id);
					intent.putExtra(ProjectsFragment.FRAGMENT_PAGE_TITLE,
							mPageTitle);
					intent.putParcelableArrayListExtra(Project.PROJECT_GROUPS,
							(ArrayList<? extends Parcelable>) mProjectGroups);
					mActivity.startActivity(intent);
				}
			}
		});
		return projectGroupView;
	}

}
