package at.fb.portfolio.projectItems;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import at.fb.portfolio.GalleryImage;
import at.fb.portfolio.ImageGalleryActivity;
import at.fb.portfolio.R;
import at.fb.portfolio.adapters.GalleryThumbAdapter;

public class ProjectItemImageGallery extends ProjectItem {

	public static final String TYPE = "at.fb.portfolio.ProjectItemImageGallery.TYPE";
	private ArrayList<GalleryImage> mGalleryImages;

	private ProjectItemImageGallery() {
		mGalleryImages = new ArrayList<GalleryImage>();
	}

	public ProjectItemImageGallery(ArrayList<GalleryImage> galleryImages) {
		mGalleryImages = galleryImages;
	}

	@Override
	public View getView(final View rootView, final Bundle savedInstanceState, final ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) rootView.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		GridView view = (GridView) inflater.inflate(
				R.layout.project_item_image_gallery, parent, false);

		view.setAdapter(new GalleryThumbAdapter(rootView.getContext(),
				mGalleryImages));

		/**
		 * start ImageGalleryActivity on galleryImageThumbnail clicked and pass
		 * all galleryImages of this project
		 */

		view.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Intent intent = new Intent(rootView.getContext(),
						ImageGalleryActivity.class);

				intent.putExtra(GalleryImage.IMAGE_POSITION, position);
				intent.putParcelableArrayListExtra(
						GalleryImage.IMAGE_COLLECTION, mGalleryImages);
				rootView.getContext().startActivity(intent);
			}
		});
		return view;
	}

	public static final Parcelable.Creator<ProjectItemImageGallery> CREATOR = new Parcelable.Creator<ProjectItemImageGallery>() {
		public ProjectItemImageGallery createFromParcel(Parcel in) {
			return new ProjectItemImageGallery(in);
		}

		public ProjectItemImageGallery[] newArray(int size) {
			return new ProjectItemImageGallery[size];
		}
	};

	protected ProjectItemImageGallery(Parcel in) {
		this();
		in.readTypedList(mGalleryImages, GalleryImage.CREATOR);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(TYPE);
		dest.writeTypedList(mGalleryImages);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
