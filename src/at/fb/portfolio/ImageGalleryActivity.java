package at.fb.portfolio;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
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
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			setFinishOnTouch(true);
		}
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setFinishOnTouch(boolean bool) {
		setFinishOnTouchOutside(true);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		case R.id.action_contact_mail:
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_EMAIL,
					new String[] { getString(R.string.email_address) });
			i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
			i.putExtra(Intent.EXTRA_TEXT, "");
			try {
				startActivity(Intent.createChooser(i,
						getString(R.string.title_message_chooser)));
			} catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(this, "Es wurde kein E-Mail-Client gefunden.",
						Toast.LENGTH_SHORT).show();
			}
			return super.onOptionsItemSelected(item);

		case R.id.action_contact_phone:
			Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ getString(R.string.phone_number)));
			startActivity(callIntent);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * this is a bug-fix for the SupportLibrary v7 the bug might be fixed in
	 * their next release
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			// do nothing
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
