package at.fb.portfolio.adapter;

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
import at.fb.portfolio.PartialFragment;
import at.fb.portfolio.Project;
import at.fb.portfolio.ProjectDetailsActivity;
import at.fb.portfolio.ProjectsFragment;
import at.fb.portfolio.R;
import at.fb.portfolio.views.NonScrollableGridView;

public class PartialFragAdapter extends BaseAdapter {
	
	private Activity context;
	private List<PartialFragment> pFrags;
	private String pageTitle;

	public PartialFragAdapter(Activity c, List<PartialFragment> pFrags, String pageTitle) {
		this.pageTitle = pageTitle;
        context = c;
        this.pFrags = pFrags;
    }
	
	@Override
	public int getCount() {
		return pFrags.size();
	}

	@Override
	public Object getItem(int position) {
		return pFrags.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View pFragView = vi.inflate(R.layout.partial_fragment_projects, null);

		TextView label=(TextView)pFragView.findViewById(R.id.text_project_category);
		label.setText(pFrags.get(position).getCategory());

		final NonScrollableGridView gView = (NonScrollableGridView) pFragView.findViewById(R.id.gridview_projects);
		gView.setAdapter(new ProjectAdapter(context, pFrags.get(position).getProjects()));

		gView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				ArrayList<Project> allProjects = new ArrayList<Project>();
				for (int i = 0; i < pFrags.size(); i++) {
					for (int j = 0; j < pFrags.get(i).getProjects().size(); j++) {
						allProjects.add(pFrags.get(i).getProjects().get(j));
					}
				}
				
				Intent intent = new Intent(context,
						ProjectDetailsActivity.class);
				intent.putExtra(Project.PROJECT_LAYOUT,
						R.layout.activity_project_details);
				intent.putExtra(Project.PROJECT_POSITION, (int)id);
				intent.putExtra(ProjectsFragment.FRAGMENT_PAGE_TITLE, pageTitle);
				intent.putParcelableArrayListExtra(Project.PROJECT_COLLECTION,
						allProjects);
				context.startActivity(intent);
			}
		});
		return pFragView;
	}

}
