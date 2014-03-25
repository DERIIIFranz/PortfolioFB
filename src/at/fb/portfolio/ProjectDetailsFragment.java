package at.fb.portfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ProjectDetailsFragment extends Fragment {

	private Project mProject;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mProject = getArguments().getParcelable(Project.PROJECT);

		View rootView = inflater.inflate(R.layout.fragment_project_details,
				container, false);

		LinearLayout linear = (LinearLayout) rootView.findViewById(R.id.ll_project_details);
		for(int i = 0; i < mProject.getProjectItems().size(); i++) {
			linear.addView(mProject.getProjectItems().get(i).getView(rootView));
		}
		
		return rootView;
	}
}