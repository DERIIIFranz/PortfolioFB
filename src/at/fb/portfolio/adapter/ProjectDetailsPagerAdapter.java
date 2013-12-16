package at.fb.portfolio.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import at.fb.portfolio.Project;
import at.fb.portfolio.ProjectDetailsFragment;

public class ProjectDetailsPagerAdapter extends FragmentPagerAdapter {
	
	private List<Project> projects;
	
	public ProjectDetailsPagerAdapter(FragmentManager fm, ArrayList<Project> projects) {
        super(fm);
        this.projects = projects;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new ProjectDetailsFragment();
        Bundle args = new Bundle();

        args.putInt(Project.PROJECT_LAYOUT, projects.get(i).getLayout());
        args.putString(Project.PROJECT_TITLE, projects.get(i).getTitle());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return projects.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return projects.get(position).getTitle();
    }

}
