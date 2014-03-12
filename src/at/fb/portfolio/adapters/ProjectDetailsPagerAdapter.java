package at.fb.portfolio.adapters;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import at.fb.portfolio.Project;
import at.fb.portfolio.ProjectDetailsFragment;

public class ProjectDetailsPagerAdapter extends FragmentPagerAdapter {

	private List<Project> mProjects;

	public ProjectDetailsPagerAdapter(FragmentManager fm,
			ArrayList<Project> projects) {
		super(fm);
		this.mProjects = projects;
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new ProjectDetailsFragment();
		Bundle args = new Bundle();

		args.putString(Project.PROJECT_DESCRIPTION, mProjects.get(i)
				.getDescription());
		args.putString(Project.PROJECT_TITLE, mProjects.get(i).getTitle());
		args.putInt(Project.PROJECT_IMAGE_HEADER, mProjects.get(i)
				.getImgHeader());
		args.putParcelableArrayList(Project.PROJECT_GALLERY_IMAGES,
				(ArrayList<? extends Parcelable>) mProjects.get(i)
						.getGalleryImages());

		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return mProjects.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mProjects.get(position).getTitle();
	}

}
