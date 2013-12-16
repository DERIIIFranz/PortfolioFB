package at.fb.portfolio.test;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import at.fb.portfolio.PrivatActivity;
import at.fb.portfolio.R;

public class PrivatActivityTest extends
		ActivityInstrumentationTestCase2<PrivatActivity> {

	PrivatActivity pActivity;
	ViewPager privatPager;

	public PrivatActivityTest() {
		super(PrivatActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		pActivity = getActivity();
		privatPager = (ViewPager) pActivity.findViewById(R.id.privatPager);
	}

	public void testPreConditions() {
		assertNotNull("PrivatActivity is null", pActivity);
	}
	
	public void testTabs() {
		
	}

}
