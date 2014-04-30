package at.fb.portfolio;

import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import at.fb.portfolio.projectItems.ProjectItemVideo;

public class ProjectDetailsFragment extends Fragment {

	private Project mProject;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mProject = getArguments().getParcelable(Project.PROJECT);

		View rootView = inflater.inflate(R.layout.fragment_project_details,
				container, false);

		LinearLayout linear = (LinearLayout) rootView
				.findViewById(R.id.ll_project_details);

		TextView label = (TextView) linear
				.findViewById(R.id.category_project_details);

		if (label != null) {
			label.setText(getArguments().getString(Project.PROJECT_CATEGORY));
		}

		if (mProject.getProjectItems() != null) {

			for (int i = 0; i < mProject.getProjectItems().size(); i++) {

				try {
					linear.addView(mProject.getProjectItems().get(i)
							.getView(rootView, savedInstanceState));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// scroll to top, as VideoView requests focus automatically
			if (linear.getChildCount() >= 1) {
				linear.getChildAt(0).setFocusableInTouchMode(true);
				linear.getChildAt(0).requestFocus();
			}
		}
		return rootView;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		if (mProject != null) {

			for (int i = 0; i < mProject.getProjectItems().size(); i++) {
				if (mProject.getProjectItems().get(i).getClass()
						.equals(ProjectItemVideo.class)) {
					outState.putInt(
							ProjectItemVideo.POS,
							((ProjectItemVideo) mProject.getProjectItems().get(
									i)).getVideoView().getCurrentPosition());
				}
			}
		}
	}
}