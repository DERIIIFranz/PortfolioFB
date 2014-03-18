package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;

public class ProjectCreativeFragment extends ProjectsFragment {
	private List<ProjectGroup> mProjectGroups = new ArrayList<ProjectGroup>();

	private ProjectGroup mPartialFrag1, mPartialFrag2;
	private List<Project> mProjects1, mProjects2;
	private static boolean sIsVisibleToUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setListView(R.layout.fragment_projects_creative);
		super.setPageTitle(R.string.pageTitle_activity_project_creative_details);

		if (mProjectGroups.isEmpty()) {

			Project.resetId();

			GalleryImage i1 = new GalleryImage(R.drawable.portrait1,
					R.drawable.portrait1, "Image 1");
			GalleryImage i2 = new GalleryImage(R.drawable.portrait2,
					R.drawable.portrait2, getString(R.string.lorem_ipsum));

			List<GalleryImage> galleryImages = new ArrayList<GalleryImage>();
			galleryImages.add(i1);
			galleryImages.add(i2);

			mProjects1 = new ArrayList<Project>();
			mProjects1.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_K13_title), "", galleryImages));
			mProjects1.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_TGTN_title), "", null));
			mProjects1.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_pirat_title), "", null));
			mProjects1.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_MOCAP_title), "", null));

			mProjects2 = new ArrayList<Project>();
			mProjects2.add(new Project(R.drawable.portrait1, -1,
					getString(R.string.project_clip_title), "", null));

			mPartialFrag1 = new ProjectGroup("3D", mProjects1);
			mPartialFrag2 = new ProjectGroup("2D", mProjects2);

			mProjectGroups.add(mPartialFrag1);
			mProjectGroups.add(mPartialFrag2);

			super.setProjectGroups(mProjectGroups);

		}
	}

	// used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		ProjectCreativeFragment.sIsVisibleToUser = isVisibleToUser;
	}

	public static boolean isVisibleToUser() {
		return sIsVisibleToUser;
	}

	public static ProjectCreativeFragment newInstance(Context ctx) {
		ProjectCreativeFragment f = new ProjectCreativeFragment();

		Bundle args = new Bundle();
		args.putString(MainActivity.TAB_TITLE,
				ctx.getString(R.string.tabTitle_project_fragment_creative));

		f.setArguments(args);

		return f;

	}
}
