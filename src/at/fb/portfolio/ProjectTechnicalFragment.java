package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import at.fb.portfolio.projectItems.ProjectItem;
import at.fb.portfolio.projectItems.ProjectItemImageGallery;
import at.fb.portfolio.projectItems.ProjectItemImageHeader;
import at.fb.portfolio.projectItems.ProjectItemPdfDocuments;
import at.fb.portfolio.projectItems.ProjectItemText;
import at.fb.portfolio.projectItems.ProjectItemVideo;

public class ProjectTechnicalFragment extends ProjectsFragment {

	private List<ProjectGroup> mProjectGroups = new ArrayList<ProjectGroup>();
	private static boolean sIsVisibleToUser;
	private List<Project> mProjectGroup1, mProjectGroup2, mProjectGroup3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setListView(R.layout.fragment_projects_technical);
		super.setPageTitle(R.string.pageTitle_activity_project_technical_details);

		if (mProjectGroups.isEmpty()) {

			Project.resetId();
			mProjectGroup1 = new ArrayList<Project>();
			mProjectGroup2 = new ArrayList<Project>();
			mProjectGroup3 = new ArrayList<Project>();

			//
			// PROJECT 1 -- NOOMIX
			// PROJECTGROUP 1
			//

			ArrayList<ProjectItem> pItems = new ArrayList<ProjectItem>();

			ArrayList<GalleryImage> galleryImages1 = new ArrayList<GalleryImage>();
			GalleryImage i1 = new GalleryImage(
					R.drawable.project_noomix_img1_thumb,
					R.drawable.project_noomix_img1,
					getString(R.string.project_noomix_img1_descr));
			GalleryImage i2 = new GalleryImage(
					R.drawable.project_noomix_img2_thumb,
					R.drawable.project_noomix_img2,
					getString(R.string.project_noomix_img2_descr));
			galleryImages1.add(i1);
			galleryImages1.add(i2);

			pItems.add(new ProjectItemImageHeader(
					R.drawable.img_project_noomix_header_white));
			pItems.add(new ProjectItemText(
					getString(R.string.project_noomix_description)));
			pItems.add(new ProjectItemImageGallery(galleryImages1));
			pItems.add(new ProjectItemVideo(
					"http://dl.dropboxusercontent.com/s/p74sgo9r1zh1qoa/test_noomix_trailer.3gp"));
			// "http://dl.dropboxusercontent.com/s/l7bww337o7g5ei1/noomix_trailer.3gp"));

			mProjectGroup1.add(new Project(
					R.drawable.thumb_project_noomix_white,
					getString(R.string.project_noomix_title), pItems));

			//
			// PROJECT 2 -- ATIKON
			//

			pItems = new ArrayList<ProjectItem>();
			ArrayList<PdfDocument> pdfDocuments = new ArrayList<PdfDocument>();
			pdfDocuments
					.add(new PdfDocument(
							getString(R.string.project_atikon_report),
							"http://dl.dropboxusercontent.com/s/unhp64t7speb342/Praktikumsbericht.pdf"));

			pItems.add(new ProjectItemImageHeader(
					R.drawable.img_project_atikon_header));
			pItems.add(new ProjectItemText(
					getString(R.string.project_atikon_description)));
			pItems.add(new ProjectItemPdfDocuments(pdfDocuments));

			mProjectGroup1.add(new Project(
					R.drawable.thumb_project_atikon_white,
					getString(R.string.project_atikon_title), pItems));

			//
			// PROJECT 3 -- FREUNDLINGER
			//

			ArrayList<GalleryImage> galleryImages2 = new ArrayList<GalleryImage>();
			galleryImages2.add(new GalleryImage(
					R.drawable.project_freundlinger_img1_thumb,
					R.drawable.project_freundlinger_img1,
					getString(R.string.project_freundlinger_img1_descr)));
			galleryImages2.add(new GalleryImage(
					R.drawable.project_freundlinger_img2_thumb,
					R.drawable.project_freundlinger_img2,
					getString(R.string.project_freundlinger_img2_descr)));
			galleryImages2.add(new GalleryImage(
					R.drawable.project_freundlinger_img3_thumb,
					R.drawable.project_freundlinger_img3,
					getString(R.string.project_freundlinger_img3_descr)));

			pItems = new ArrayList<ProjectItem>();
			pItems.add(new ProjectItemImageHeader(
					R.drawable.img_project_freundlinger_header));
			pItems.add(new ProjectItemText(
					getString(R.string.project_freundlinger_description)));
			pItems.add(new ProjectItemImageGallery(galleryImages2));

			mProjectGroup1.add(new Project(
					R.drawable.thumb_project_freundlinger_white,
					getString(R.string.project_freundlinger_title), pItems));

			//
			// PROJECT 4 -- ARCTCONSENSE
			//
			
			ArrayList<GalleryImage> galleryImages3 = new ArrayList<GalleryImage>();
			galleryImages3.add(new GalleryImage(
					R.drawable.project_artconsense_img1_thumb,
					R.drawable.project_artconsense_img1,
					getString(R.string.project_artconsense_img1_descr)));
			galleryImages3.add(new GalleryImage(
					R.drawable.project_artconsense_img2_thumb,
					R.drawable.project_artconsense_img2,
					getString(R.string.project_artconsense_img2_descr)));
			galleryImages3.add(new GalleryImage(
					R.drawable.project_artconsense_img3_thumb,
					R.drawable.project_artconsense_img3,
					getString(R.string.project_artconsense_img3_descr)));

			pItems = new ArrayList<ProjectItem>();
			pItems.add(new ProjectItemImageHeader(
					R.drawable.img_project_artconsense_header));
			pItems.add(new ProjectItemText(
					getString(R.string.project_artconsense_description)));
			pItems.add(new ProjectItemImageGallery(galleryImages3));

			mProjectGroup1.add(new Project(
					R.drawable.thumb_project_artconsense_white,
					getString(R.string.project_artconsense_title), pItems));

			//
			// PROJECT 5 -- SWATCHDOG
			// PROJECTGROUP 2
			//
			
			pdfDocuments = new ArrayList<PdfDocument>();
			pdfDocuments.add(new PdfDocument(getActivity().getString(
						R.string.pdf_masterThesis_title),
						"http://www.pdf-archive.com/2014/03/05/masterthesis/masterthesis.pdf"));
			
			pItems = new ArrayList<ProjectItem>();
			pItems.add(new ProjectItemImageHeader(
					R.drawable.img_project_swatchdog_header_white));
			pItems.add(new ProjectItemText(
					getString(R.string.project_swatchdog_description)));
			pItems.add(new ProjectItemPdfDocuments(pdfDocuments));

			mProjectGroup2.add(new Project(
					R.drawable.thumb_project_swatchdog_white,
					getString(R.string.project_swatchdog_title), pItems));

			//
			// PROJECT 6 -- PORTFOLIOFB
			// PROJECTGROUP 3
			//
			
			pItems = new ArrayList<ProjectItem>();
			pItems.add(new ProjectItemText(getString(R.string.project_portfolioFB_description)));

			mProjectGroup3.add(new Project(
					R.drawable.thumb_project_portfoliofb_white,
					getString(R.string.project_portfolioFB_title), pItems));

			//
			// PROJECT 7 -- PROJECTX
			//
			
			pItems = new ArrayList<ProjectItem>();
			pItems.add(new ProjectItemText(getString(R.string.project_x_description)));

			mProjectGroup3.add(new Project(
					R.drawable.thumb_project_x_white,
					getString(R.string.project_x_title), pItems));

			// add all projectGroups
			mProjectGroups
					.add(new ProjectGroup(
							getString(R.string.project_group_technical_web_development),
							mProjectGroup1));
			mProjectGroups.add(new ProjectGroup(
					getString(R.string.project_group_technical_web_security),
					mProjectGroup2));
			mProjectGroups.add(new ProjectGroup(
					getString(R.string.project_group_technical_apps),
					mProjectGroup3));
			super.setProjectGroups(mProjectGroups);

		}
	}

	// used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		ProjectTechnicalFragment.sIsVisibleToUser = isVisibleToUser;
	}

	public static boolean isVisibleToUser() {
		return sIsVisibleToUser;
	}

	public static ProjectTechnicalFragment newInstance(Context ctx) {
		ProjectTechnicalFragment f = new ProjectTechnicalFragment();

		Bundle args = new Bundle();
		args.putString(MainActivity.TAB_TITLE,
				ctx.getString(R.string.tabTitle_project_fragment_technical));

		f.setArguments(args);

		return f;

	}
}
