package at.fb.portfolio.test;

import java.util.Locale;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import at.fb.portfolio.PrivatAboutMeFragment;
import at.fb.portfolio.PrivatActivity;
import at.fb.portfolio.PrivatCVFragment;
import at.fb.portfolio.R;

import com.jayway.android.robotium.solo.Solo;

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
		assertEquals(pActivity.getString(R.string.tabTitle_privat_fragment_cv)
				.toUpperCase(l), pActivity.getSupportActionBar().getTabAt(1)
				.getText());
	}

	public void testContent_layout() {
		
		assertTrue(PrivatAboutMeFragment.isVisibleToUser());

		solo.clickOnText(pActivity.getString(R.string.tabTitle_privat_fragment_cv).toUpperCase(l)); 

		assertFalse(PrivatAboutMeFragment.isVisibleToUser());
		assertTrue(PrivatCVFragment.isVisibleToUser());
	}
	
	@Override
	   public void tearDown() throws Exception {
	        solo.finishOpenedActivities();
	  }
}
