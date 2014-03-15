package at.fb.portfolio.adapters;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import at.fb.portfolio.GalleryImage;
import at.fb.portfolio.ImageGalleryFragment;

public class ImageGalleryPagerAdapter extends FragmentPagerAdapter {
	
	private List<GalleryImage> mGalleryImages;

	public ImageGalleryPagerAdapter(FragmentManager fm,
			ArrayList<GalleryImage> galleryImages) {
		super(fm);
		mGalleryImages = galleryImages;
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new ImageGalleryFragment();
		Bundle args = new Bundle();

		args.putParcelable(GalleryImage.IMAGE, mGalleryImages.get(i));

		fragment.setArguments(args);
		
		return fragment;
	}

	@Override
	public int getCount() {
		return mGalleryImages.size();
	}

}
