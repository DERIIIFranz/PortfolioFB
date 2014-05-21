package at.fb.portfolio;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import at.fb.portfolio.adapters.ImageGalleryPagerAdapter;

public class ImageGalleryActivity extends FragmentActivity {

	private ImageGalleryPagerAdapter mGalleryAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_gallery);

		Bundle extras = getIntent().getExtras();
		ArrayList<GalleryImage> galleryImages = getIntent()
				.getParcelableArrayListExtra(GalleryImage.IMAGE_COLLECTION);

		mGalleryAdapter = new ImageGalleryPagerAdapter(
				getSupportFragmentManager(), galleryImages);
		mViewPager = (ViewPager) findViewById(R.id.projectImageGalleryPager);
		mViewPager.setAdapter(mGalleryAdapter);
		mViewPager.setCurrentItem(extras.getInt(GalleryImage.IMAGE_POSITION));
	}
}
