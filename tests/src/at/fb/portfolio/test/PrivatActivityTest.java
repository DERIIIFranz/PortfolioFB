package at.fb.portfolio.test;

import java.util.Locale;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import at.fb.portfolio.PrivatAboutMeFragment;
import at.fb.portfolio.PrivatActivity;
import at.fb.portfolio.PrivatDocsFragment;
import at.fb.portfolio.R;

import com.pagesuite.flowtext.FlowTextView;
import com.robotium.solo.Solo;

public class PrivatActivityTest extends
		ActivityInstrumentationTestCase2<PrivatActivity> {

	private Solo solo;

	private PrivatActivity pActivity;
	private ViewPager privatPager;
	private Locale l;

	public PrivatActivityTest() {
		super(PrivatActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		solo = new Solo(getInstrumentation(), getActivity());

		l = Locale.getDefault();
		pActivity = getActivity();
		privatPager = (ViewPager) pActivity.findViewById(R.id.privatPager);
	}

	public void testPreConditions() {
		assertNotNull("PrivatActivity is null", pActivity);
		assertNotNull("PrivatActivity is null", privatPager);
	}

	public void testTabBar() {
		// test tab-count
		assertEquals(2, pActivity.getSupportActionBar().getTabCount());

		// test tablabels
		assertEquals(
				pActivity.getString(R.string.tabTitle_privat_fragment_aboutMe)
						.toUpperCase(l), pActivity.getSupportActionBar()
						.getTabAt(0).getText());
		assertEquals(
				pActivity
						.getString(R.string.tabTitle_privat_fragment_documents)
						.toUpperCase(l), pActivity.getSupportActionBar()
						.getTabAt(1).getText());
	}

	public void testContent_layout() {

		assertTrue(PrivatAboutMeFragment.isVisibleToUser());

		solo.clickOnText(pActivity.getString(
				R.string.tabTitle_privat_fragment_documents).toUpperCase(l));

		solo.sleep(50);
		assertFalse(PrivatAboutMeFragment.isVisibleToUser());
		assertTrue(PrivatDocsFragment.isVisibleToUser());
	}

	public void testAboutMe() {
		FlowTextView aboutMe = (FlowTextView) pActivity
				.findViewById(R.id.tv_about_me1);
		ImageView portrait2 = (ImageView) pActivity
				.findViewById(R.id.iv_about_me_portrait2);
		ImageView team1 = (ImageView) pActivity
				.findViewById(R.id.iv_about_me_team1);
		ImageView team = (ImageView) pActivity
				.findViewById(R.id.iv_about_me_team);

		assertTrue(PrivatAboutMeFragment.isVisibleToUser());

		assertEquals(View.VISIBLE, aboutMe.getVisibility());
		assertEquals(View.VISIBLE, portrait2.getVisibility());
		assertEquals(View.VISIBLE, team1.getVisibility());
		assertEquals(View.VISIBLE, team.getVisibility());

		assertEquals(pActivity.getString(R.string.about_me1), aboutMe.getText()
				.toString());
	}

	public void testDocuments() {
		swipeToLeft(1);
		solo.sleep(50);
		assertTrue(PrivatDocsFragment.isVisibleToUser());

		GridView gv = (GridView) pActivity.findViewById(R.id.ngv_pdf_documents);
		assertEquals(5, gv.getChildCount());

		String imgBtn_descr = pActivity
				.getString(R.string.ic_action_download_descr);

		// Test ImageButton's contentDescription
		assertEquals(imgBtn_descr,
				((ImageButton) ((RelativeLayout) gv.getChildAt(0))
						.getChildAt(0)).getContentDescription());
		assertEquals(imgBtn_descr,
				((ImageButton) ((RelativeLayout) gv.getChildAt(1))
						.getChildAt(0)).getContentDescription());
		assertEquals(imgBtn_descr,
				((ImageButton) ((RelativeLayout) gv.getChildAt(2))
						.getChildAt(0)).getContentDescription());

		// Test ImageButton's drawable
		assertEquals(R.drawable.ic_action_download,
				((ImageButton) ((RelativeLayout) gv.getChildAt(0))
						.getChildAt(0)).getTag());
		assertEquals(R.drawable.ic_action_download,
				((ImageButton) ((RelativeLayout) gv.getChildAt(1))
						.getChildAt(0)).getTag());
		assertEquals(R.drawable.ic_action_download,
				((ImageButton) ((RelativeLayout) gv.getChildAt(2))
						.getChildAt(0)).getTag());

		// Test TextViews
		assertEquals(pActivity.getText(R.string.pdf_cv_title),
				((TextView) ((RelativeLayout) gv.getChildAt(0)).getChildAt(1))
						.getText());
		assertEquals(pActivity.getText(R.string.pdf_masterThesis_title),
				((TextView) ((RelativeLayout) gv.getChildAt(1)).getChildAt(1))
						.getText());
		assertEquals(pActivity.getText(R.string.pdf_bakkThesis_title),
				((TextView) ((RelativeLayout) gv.getChildAt(2)).getChildAt(1))
						.getText());
	}

	public void testImageGallery() {
		swipeToLeft(1);
		solo.sleep(50);
		assertTrue(PrivatDocsFragment.isVisibleToUser());

		ImageView iv_urkunde_thumb = (ImageView) pActivity
				.findViewById(R.id.gv_image_gallery).getTouchables().get(0);
		assertEquals(iv_urkunde_thumb.getTag(), R.drawable.urkunde_fhs_thumb);

		solo.clickOnView(iv_urkunde_thumb);

		ImageView iv_urkunde = (ImageView) solo.getView(R.id.iv_image_gallery);
		TextView descr_urkunde = (TextView) solo.getView(R.id.tv_image_gallery);

		assertEquals(iv_urkunde.getTag(), R.drawable.urkunde_fhs);
		assertEquals(descr_urkunde.getText(),
				pActivity.getString(R.string.img_urkunde_fhs_descr));
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	private void swipeToLeft(int stepCount) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		float xStart = width - 10;
		float xEnd = 10;
		solo.drag(xStart, xEnd, height / 2, height / 2, stepCount);
		solo.sleep(100);
	}
}
