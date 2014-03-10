package at.fb.portfolio.adapters;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import at.fb.portfolio.Project;
import at.fb.portfolio.ProjectDetailsFragment;

public class ProjectDetailsPagerAdapter extends FragmentPagerAdapter {
	
	private List<Project> mProjects;
	
	public ProjectDetailsPagerAdapter(FragmentManager fm, ArrayList<Project> projects) {
        super(fm);
        this.mProjects = projects;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new ProjectDetailsFragment();
        Bundle args = new Bundle();

        args.putInt(Project.PROJECT_LAYOUT, mProjects.get(i).getLayout());
        args.putString(Project.PROJECT_TITLE, mProjects.get(i).getTitle());
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
