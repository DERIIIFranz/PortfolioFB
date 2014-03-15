package at.fb.portfolio.test;

import java.util.Locale;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import at.fb.portfolio.PrivatAboutMeFragment;
import at.fb.portfolio.PrivatActivity;
import at.fb.portfolio.PrivatDocsFragment;
import at.fb.portfolio.R;

import com.jayway.android.robotium.solo.Solo;
import com.pagesuite.flowtext.FlowTextView;

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

		assertFalse(PrivatAboutMeFragment.isVisibleToUser());
		assertTrue(PrivatDocsFragment.isVisibleToUser());
	}

	public void testAboutMe() {
		FlowTextView aboutMe = (FlowTextView) pActivity
				.findViewById(R.id.tv_about_me);
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

		assertEquals(pActivity.getString(R.string.about_me), aboutMe.getText()
				.toString());
	}

	public void testDocuments() {
		swipeToLeft(1);
		assertTrue(PrivatDocsFragment.isVisibleToUser());

		ImageView btnCV = (ImageView) pActivity.findViewById(R.id.btn_docs_cv);
		ImageView btnMasterThesis = (ImageView) pActivity
				.findViewById(R.id.btn_docs_masterThesis);
		ImageView btnBakkThesis = (ImageView) pActivity
				.findViewById(R.id.btn_docs_bakkThesis);

		TextView tvCV = (TextView) pActivity.findViewById(R.id.tv_docs_cv);
		TextView tvMasterThesis = (TextView) pActivity
				.findViewById(R.id.tv_docs_masterThesis);
		TextView tvBakkThesis = (TextView) pActivity
				.findViewById(R.id.tv_docs_bakkThesis);

		assertEquals(View.VISIBLE, btnCV.getVisibility());
		assertEquals(View.VISIBLE, btnMasterThesis.getVisibility());
		assertEquals(View.VISIBLE, btnBakkThesis.getVisibility());
		assertEquals(View.VISIBLE, tvCV.getVisibility());
		assertEquals(View.VISIBLE, tvMasterThesis.getVisibility());
		assertEquals(View.VISIBLE, tvBakkThesis.getVisibility());
	}

	public void testImageGallery() {
		swipeToLeft(1);
		assertTrue(PrivatDocsFragment.isVisibleToUser());

		ImageView iv_urkunde_thumb = (ImageView) pActivity
				.findViewById(R.id.gv_privat_docs_gallery).getTouchables()
				.get(0);
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
