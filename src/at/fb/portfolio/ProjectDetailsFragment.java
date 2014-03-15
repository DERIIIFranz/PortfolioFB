package at.fb.portfolio;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import at.fb.portfolio.adapters.GalleryThumbAdapter;

public class ProjectDetailsFragment extends Fragment {

	private ArrayList<GalleryImage> mGalleryImages;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Bundle args = getArguments();

		View rootView = inflater.inflate(R.layout.fragment_project_details,
				container, false);

		GridView gv = (GridView) rootView
				.findViewById(R.id.gv_project_details_gallery);

		mGalleryImages = args
				.<GalleryImage> getParcelableArrayList(Project.PROJECT_GALLERY_IMAGES);

		if (mGalleryImages != null && mGalleryImages.size() > 0) {
			gv.setAdapter(new GalleryThumbAdapter(getActivity(), mGalleryImages));

			/**
			 * start ImageGalleryActivity on galleryImageThumbnail clicked
			 * and pass all galleryImages of this project
			 */
			gv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {

					Intent intent = new Intent(getActivity(),
							ImageGalleryActivity.class);

					intent.putExtra(GalleryImage.IMAGE_POSITION, position);
					intent.putParcelableArrayListExtra(
							GalleryImage.IMAGE_COLLECTION, mGalleryImages);
					startActivity(intent);
				}
			});
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