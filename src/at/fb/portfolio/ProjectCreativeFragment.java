package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import at.fb.portfolio.projectItems.ProjectItem;
import at.fb.portfolio.projectItems.ProjectItemImageGallery;

public class ProjectCreativeFragment extends ProjectsFragment {
	private List<ProjectGroup> mProjectGroups = new ArrayList<ProjectGroup>();

	private List<Project> mProjectGroup1, mProjectGroup2;
	private static boolean sIsVisibleToUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setListView(R.layout.fragment_projects_creative);
		super.setPageTitle(R.string.pageTitle_activity_project_creative_details);

		if (mProjectGroups.isEmpty()) {

			Project.resetId();

			ArrayList<ProjectItem> pItems = new ArrayList<ProjectItem>();
			mProjectGroup1 = new ArrayList<Project>();
			mProjectGroup2 = new ArrayList<Project>();

			//
			// PROJECT 1 -- K13
			//

			GalleryImage i1 = new GalleryImage(R.drawable.portrait1,
					R.drawable.portrait1, "Image 1");
			GalleryImage i2 = new GalleryImage(R.drawable.portrait2,
					R.drawable.portrait2, getString(R.string.lorem_ipsum));

			ArrayList<GalleryImage> galleryImages = new ArrayList<GalleryImage>();
			galleryImages.add(i1);
			galleryImages.add(i2);

			pItems.add(new ProjectItemImageGallery(galleryImages));
			mProjectGroup1.add(new Project(
					R.drawable.thumb_project_noomix_white,
					getString(R.string.project_K13_title), pItems));

			//
			// PROJECT 2 -- TGTN
			//

			mProjectGroup1.add(new Project(
					R.drawable.thumb_project_noomix_white,
					getString(R.string.project_TGTN_title), null));

			//
			// PROJECT 3 -- PIRAT
			//

			mProjectGroup1.add(new Project(
					R.drawable.thumb_project_noomix_white,
					getString(R.string.project_pirat_title), null));

			//
			// PROJECT 4 -- MOCAP
			//

			mProjectGroup1.add(new Project(
					R.drawable.thumb_project_noomix_white,
					getString(R.string.project_MOCAP_title), null));

			//
			// PROJECT 5 -- CLIP
			//

			mProjectGroup2.add(new Project(
					R.drawable.thumb_project_noomix_white,
					getString(R.string.project_clip_title), null));

			mProjectGroups.add(new ProjectGroup(
					getString(R.string.project_group_creative_3d),
					mProjectGroup1));
			mProjectGroups.add(new ProjectGroup(
					getString(R.string.project_group_creative_2d),
					mProjectGroup2));

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
