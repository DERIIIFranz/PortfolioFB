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
import at.fb.portfolio.ProjectPartialFragment;
import at.fb.portfolio.Project;
import at.fb.portfolio.ProjectDetailsActivity;
import at.fb.portfolio.ProjectsFragment;
import at.fb.portfolio.R;
import at.fb.portfolio.views.NonScrollableGridView;

public class PartialFragAdapter extends BaseAdapter {
	
	private Activity mContext;
	private List<ProjectPartialFragment> mPartialFrags;
	private String mPageTitle;

	/**
	 * Supplies projectpage with several PartialFragments
	 * @param c corresponding activity
	 * @param pFrags list of groups (PartialFragments) of projects
	 * @param pageTitle
	 */
	public PartialFragAdapter(Activity c, List<ProjectPartialFragment> pFrags, String pageTitle) {
		this.mPageTitle = pageTitle;
        mContext = c;
        this.mPartialFrags = pFrags;
    }
	
	@Override
	public int getCount() {
		return mPartialFrags.size();
	}

	@Override
	public Object getItem(int position) {
		return mPartialFrags.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View pFragView = vi.inflate(R.layout.partial_fragment_projects, null);

		TextView label=(TextView)pFragView.findViewById(R.id.text_project_category);
		label.setText(mPartialFrags.get(position).getCategory());

		final NonScrollableGridView gView = (NonScrollableGridView) pFragView.findViewById(R.id.gridview_projects);
		gView.setAdapter(new ProjectAdapter(mContext, mPartialFrags.get(position).getProjects()));

		gView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				ArrayList<Project> allProjects = new ArrayList<Project>();
				for (int i = 0; i < mPartialFrags.size(); i++) {
					for (int j = 0; j < mPartialFrags.get(i).getProjects().size(); j++) {
						allProjects.add(mPartialFrags.get(i).getProjects().get(j));
					}
				}
				
				Intent intent = new Intent(mContext,
						ProjectDetailsActivity.class);
				intent.putExtra(Project.PROJECT_LAYOUT,
						R.layout.activity_project_details);
				intent.putExtra(Project.PROJECT_POSITION, (int)id);
				intent.putExtra(ProjectsFragment.FRAGMENT_PAGE_TITLE, mPageTitle);
				intent.putParcelableArrayListExtra(Project.PROJECT_COLLECTION,
						allProjects);
				mContext.startActivity(intent);
			}
		});
		return pFragView;
	}

}
