package at.fb.portfolio.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import at.fb.portfolio.MainActivity;
import at.fb.portfolio.PrivatActivity;
import at.fb.portfolio.ProjectActivity;
import at.fb.portfolio.R;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private static final int TIMEOUT_IN_MS = 500;

	private MainActivity mMainActivity;
	private TextView name, profession, slogan;
	private Button mPrivatButton, mProjectsButton;
	private ImageView picture;

	public MainActivityTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(true);
		mMainActivity = getActivity();
		name = (TextView) mMainActivity.findViewById(at.fb.portfolio.R.id.name);
		profession = (TextView) mMainActivity.findViewById(R.id.profession);
		mPrivatButton = (Button) mMainActivity.findViewById(R.id.btn_private);
		mProjectsButton = (Button) mMainActivity
				.findViewById(R.id.btn_projects);
		picture = (ImageView) mMainActivity.findViewById(R.id.picture);
		slogan = (TextView) mMainActivity.findViewById(R.id.slogan);

	}

	public void testPreconditions() {
		assertNotNull("mMainActivity is null", mMainActivity);
		assertNotNull("Name is null", name);
		assertNotNull("Profession is null", profession);
		assertNotNull("Picture is null", picture);
		assertNotNull("PrivatButton is null", mPrivatButton);
		assertNotNull("ProjectsButton is null", mProjectsButton);
	}

	public void testMainActivityTextViews_labelText() {
		String expected = mMainActivity.getString(R.string.name);
		String actual = name.getText().toString();
		assertEquals(expected, actual);

		expected = mMainActivity.getString(R.string.profession);
		actual = profession.getText().toString();
		assertEquals(expected, actual);

		expected = mMainActivity.getString(R.string.btn_private);
		actual = mPrivatButton.getText().toString();
		assertEquals(expected, actual);

		expected = mMainActivity.getString(R.string.btn_projects);
		actual = mProjectsButton.getText().toString();
		assertEquals(expected, actual);

		expected = mMainActivity.getString(R.string.slogan);
		actual = slogan.getText().toString();
		assertEquals(expected, actual);
	}

	@MediumTest
	public void testPicture_layout() {
		final View decorView = mMainActivity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, picture);
	}
	
	@MediumTest
	public void testButtons_layout() {
		final View decorView = mMainActivity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, mPrivatButton);
		ViewAsserts.assertOnScreen(decorView, mProjectsButton);
	}

	@MediumTest
	public void testPrivatButton_click() {

		// Set up an ActivityMonitor
		ActivityMonitor privatActivityMonitor = getInstrumentation()
				.addMonitor(PrivatActivity.class.getName(), null, false);

		TouchUtils.clickView(this, mPrivatButton);
		PrivatActivity privatActivity = (PrivatActivity) privatActivityMonitor
				.waitForActivityWithTimeout(TIMEOUT_IN_MS);
		assertNotNull("PrivatActivity is null", privatActivity);
		assertEquals("Monitor for PrivatActivity has not been called", 1,
				privatActivityMonitor.getHits());
		assertEquals("Activity is of wrong type", PrivatActivity.class,
				privatActivity.getClass());

		if (privatActivity != null)
			privatActivity.finish();

		// Remove the ActivityMonitor
		getInstrumentation().removeMonitor(privatActivityMonitor);
	}

	@MediumTest
	public void testProjectsButton_click() {

		// Set up an ActivityMonitor
		ActivityMonitor projectActivityMonitor = getInstrumentation()
				.addMonitor(ProjectActivity.class.getName(), null, false);

		// Validate that ReceiverActivity is started
		TouchUtils.clickView(this, mProjectsButton);
		ProjectActivity projectActivity = (ProjectActivity) projectActivityMonitor
				.waitForActivityWithTimeout(TIMEOUT_IN_MS);
		assertNotNull("ProjectActivity is null", projectActivity);
		assertEquals("Monitor for ProjectActivity has not been called", 1,
				projectActivityMonitor.getHits());
		assertEquals("Activity is of wrong type", ProjectActivity.class,
				projectActivity.getClass());

		if (projectActivity != null)
			projectActivity.finish();

		// Remove the ActivityMonitor
		getInstrumentation().removeMonitor(projectActivityMonitor);
	}

}
