package at.fb.portfolio;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import at.fb.portfolio.adapters.ImageGalleryPagerAdapter;

public class ImageGalleryActivity extends ActionBarActivity {

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_gallery, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			// NavUtils.navigateUpFromSameTask(this);
			finish(); // this way, ProjectDetailsActivity isn't recreated
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * this is a bug-fix for the SupportLibrary v7
	 * the bug might be fixed in their next release
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ( keyCode == KeyEvent.KEYCODE_MENU ) {
	        // do nothing
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}   
}
