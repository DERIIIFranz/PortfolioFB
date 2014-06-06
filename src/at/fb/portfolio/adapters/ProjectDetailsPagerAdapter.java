package at.fb.portfolio.adapters;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import at.fb.portfolio.Project;
import at.fb.portfolio.ProjectDetailsFragment;
import at.fb.portfolio.ProjectGroup;

public class ProjectDetailsPagerAdapter extends FragmentPagerAdapter {

	private List<ProjectGroup> mProjectGroups;
	private int mGroupId, mProjectId;

	public ProjectDetailsPagerAdapter(FragmentManager fm,
			ArrayList<ProjectGroup> projectGroups) {
		super(fm);
		this.mProjectGroups = projectGroups;
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new ProjectDetailsFragment();
		Bundle args = new Bundle();

		// determine projectGroupId for requested item
		mProjectId = i;
		for (mGroupId = 0; (mProjectId - mProjectGroups.get(mGroupId)
				.getProjects().size()) >= 0; mGroupId++) {
			mProjectId -= mProjectGroups.get(mGroupId).getProjects().size();
		}

		args.putParcelable(Project.PROJECT, mProjectGroups.get(mGroupId)
				.getProjects().get(mProjectId));
		args.putString(Project.PROJECT_CATEGORY, mProjectGroups.get(mGroupId)
				.getCategory());
		fragment.setArguments(args);
		return fragment;
	}
	
	public List<ProjectGroup> getProjectGroups() {
		return mProjectGroups;
	}

	@Override
	public int getCount() {
		int items = 0;
		for (int i = 0; i < mProjectGroups.size(); i++) {
			for (int j = 0; j < mProjectGroups.get(i).getProjects().size(); j++) {
				items++;
			}
		}
		return items;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// determine projectGroupId for requested item
		mProjectId = position;
		for (mGroupId = 0; (mProjectId - mProjectGroups.get(mGroupId)
				.getProjects().size()) >= 0; mGroupId++) {
			mProjectId -= mProjectGroups.get(mGroupId).getProjects().size();
		}

		return mProjectGroups.get(mGroupId).getProjects().get(mProjectId)
				.getTitle();
	}
}