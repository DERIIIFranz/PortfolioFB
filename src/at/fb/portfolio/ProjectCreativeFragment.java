package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import at.fb.portfolio.projectItems.ProjectItem;
import at.fb.portfolio.projectItems.ProjectItemImageHeader;
import at.fb.portfolio.projectItems.ProjectItemObj;
import at.fb.portfolio.projectItems.ProjectItemPdfDocuments;
import at.fb.portfolio.projectItems.ProjectItemText;
import at.fb.portfolio.projectItems.ProjectItemVideo;

public class ProjectCreativeFragment extends ProjectsFragment {
	private List<ProjectGroup> mProjectGroups = new ArrayList<ProjectGroup>();

	private List<Project> mProjectGroup1, mProjectGroup2;
	private static boolean sIsVisibleToUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setPageTitle(R.string.pageTitle_activity_project_creative_details);

		if (mProjectGroups.isEmpty()) {

			Project.resetId();

			ArrayList<ProjectItem> pItems = new ArrayList<ProjectItem>();
			mProjectGroup1 = new ArrayList<Project>();
			mProjectGroup2 = new ArrayList<Project>();

			//
			// PROJECT 1 -- K13
			//

			pItems.add(new ProjectItemImageHeader(
					R.drawable.img_project_k13_header_white));
			pItems.add(new ProjectItemText(
					getString(R.string.project_K13_description)));
			pItems.add(new ProjectItemVideo(
					"http://dl.dropboxusercontent.com/s/kdvhdg4acwgdpfa/k13_small.3gp"));

			mProjectGroup1.add(new Project(R.drawable.thumb_project_k13_white,
					getString(R.string.project_K13_title), pItems));

			//
			// PROJECT 2 -- TGTN
			//

			pItems = new ArrayList<ProjectItem>();
			pItems.add(new ProjectItemImageHeader(
					R.drawable.img_project_tgtn_header));
			pItems.add(new ProjectItemText(
					getString(R.string.project_TGTN_description)));
			pItems.add(new ProjectItemVideo(
					"http://dl.dropboxusercontent.com/s/g1ljrxblzuktdwe/tgtn_small_short.mp4"));

			mProjectGroup1.add(new Project(R.drawable.thumb_project_tgtn_white,
					getString(R.string.project_TGTN_title), pItems));

			//
			// PROJECT 3 -- PIRAT
			//
			pItems = new ArrayList<ProjectItem>();
			pItems.add(new ProjectItemImageHeader(
					R.drawable.img_project_pirat_header_white));
			pItems.add(new ProjectItemText(getString(R.string.project_pirat_description)));
			pItems.add(new ProjectItemObj("at.fb.portfolio:raw/pirat_obj"));

			mProjectGroup1.add(new Project(
					R.drawable.thumb_project_pirat_white,
					getString(R.string.project_pirat_title), pItems));

			//
			// PROJECT 4 -- MOCAP
			//

			ArrayList<PdfDocument> pdfDocuments = new ArrayList<PdfDocument>();
			pdfDocuments
					.add(new PdfDocument(
							getString(R.string.pdf_bakkThesis_title),
							"http://www.pdf-archive.com/2014/03/05/bachelorthesis/bachelorthesis.pdf"));

			pItems = new ArrayList<ProjectItem>();
			pItems.add(new ProjectItemImageHeader(
					R.drawable.img_project_mocap_header_white));
			pItems.add(new ProjectItemText(
					getString(R.string.project_MOCAP_description)));
			pItems.add(new ProjectItemPdfDocuments(pdfDocuments));
			pItems.add(new ProjectItemVideo(
					"http://dl.dropboxusercontent.com/s/91aiv7h9o4huyfn/mocap.3gp"));
	
			mProjectGroup1.add(new Project(
					R.drawable.thumb_project_mocap_white,
					getString(R.string.project_MOCAP_title), pItems));

			//
			// PROJECT 5 -- CLIP
			//
			pItems = new ArrayList<ProjectItem>();
			pItems.add(new ProjectItemImageHeader(
					R.drawable.img_project_clip_header_white));
			pItems.add(new ProjectItemText(
					getString(R.string.project_clip_description)));
			pItems.add(new ProjectItemVideo(
					"http://dl.dropboxusercontent.com/s/5iphdvbnhuenxd7/clip.3gp"));

			mProjectGroup2.add(new Project(R.drawable.thumb_project_clip_white,
					getString(R.string.project_clip_title), pItems));

			//
			// add all projectGroups
			//

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
