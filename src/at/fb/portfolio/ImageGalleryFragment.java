package at.fb.portfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageGalleryFragment extends Fragment {
	
	private GalleryImage mGalleryImage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Bundle args = getArguments();
		mGalleryImage = args.getParcelable(GalleryImage.IMAGE);

		View rootView = inflater.inflate(R.layout.fragment_image_gallery, container,
				false);
		
		ImageView iv = (ImageView) rootView.findViewById(R.id.iv_image_gallery);
		iv.setImageResource(mGalleryImage.getDrawable());
		iv.setContentDescription(mGalleryImage.getDescription());
		// 
		// set the Drawables resourceId as the ImageViews tag
		// so that the id can be compared for testing purposes
		//
		iv.setTag(mGalleryImage.getDrawable());
		
		TextView tv = (TextView) rootView.findViewById(R.id.tv_image_gallery);
		tv.setText(mGalleryImage.getDescription());
		
		return rootView;
	}

}
