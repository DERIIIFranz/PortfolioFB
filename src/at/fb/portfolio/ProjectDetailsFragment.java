package at.fb.portfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import at.fb.portfolio.adapters.GalleryImageAdapter;

public class ProjectDetailsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Bundle args = getArguments();

		View rootView = inflater.inflate(R.layout.project_details, container,
				false);

		GridView gv = (GridView) rootView
				.findViewById(R.id.gv_project_details_gallery);

		if (args.<GalleryImage> getParcelableArrayList(Project.PROJECT_GALLERY_IMAGES) != null
				&& args.<GalleryImage> getParcelableArrayList(
						Project.PROJECT_GALLERY_IMAGES).size() > 0) {
			gv.setAdapter(new GalleryImageAdapter(
					getActivity(),
					args.<GalleryImage> getParcelableArrayList(Project.PROJECT_GALLERY_IMAGES)));
		}

		if (args.getInt(Project.PROJECT_IMAGE_HEADER) > 0) {
			((ImageView) rootView.findViewById(R.id.img_project_details_header))
					.setImageResource(args.getInt(Project.PROJECT_IMAGE_HEADER));
		}

		((TextView) rootView.findViewById(R.id.tv_project_description))
				.setText(args.getString(Project.PROJECT_DESCRIPTION));

		return rootView;
	}

}
