package at.fb.portfolio;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import at.fb.portfolio.projectItems.ProjectItemImageGallery;
import at.fb.portfolio.projectItems.ProjectItemPdfDocuments;
import at.fb.portfolio.views.NonScrollableGridView;

public class PrivatDocsFragment extends Fragment {

	private static boolean sIsVisibleToUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout rootView = (LinearLayout) inflater.inflate(
				R.layout.fragment_privat_documents, container, false);

		if (getPdfDocuments() != null && getPdfDocuments().size() > 0) {
			rootView.addView((NonScrollableGridView) (new ProjectItemPdfDocuments(
					getPdfDocuments())).getView(rootView, savedInstanceState,
					container));
		}

		if (getGalleryImages() != null && getGalleryImages().size() > 0) {
			rootView.addView((GridView) (new ProjectItemImageGallery(
					getGalleryImages())).getView(rootView, savedInstanceState,
					container));
		}

		/**
		 * wrap the LinearLayout with a ScrollView to enable
		 * scrolling-capabilities
		 */
		ScrollView sv = new ScrollView(rootView.getContext());
		sv.addView(rootView);
		return sv;
	}

	// used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		PrivatDocsFragment.sIsVisibleToUser = isVisibleToUser;
	}

	public static boolean isVisibleToUser() {
		return sIsVisibleToUser;
	}

	public static PrivatDocsFragment newInstance(Context ctx) {
		PrivatDocsFragment f = new PrivatDocsFragment();

		Bundle args = new Bundle();
		args.putString(MainActivity.TAB_TITLE,
				ctx.getString(R.string.tabTitle_privat_fragment_documents));

		f.setArguments(args);

		return f;

	}

	private ArrayList<GalleryImage> getGalleryImages() {
		ArrayList<GalleryImage> galleryImages = new ArrayList<GalleryImage>();

		GalleryImage i1 = new GalleryImage(R.drawable.urkunde_fhs_thumb,
				R.drawable.urkunde_fhs,
				getString(R.string.img_urkunde_fhs_descr));

		galleryImages.add(i1);

		return galleryImages;
	}

	private ArrayList<PdfDocument> getPdfDocuments() {
		ArrayList<PdfDocument> pdfDocuments = new ArrayList<PdfDocument>();

		pdfDocuments.add(new PdfDocument(getActivity().getString(
				R.string.pdf_cv_title), getActivity().getString(
				R.string.pdf_cv_url)));
		pdfDocuments.add(new PdfDocument(getActivity().getString(
				R.string.pdf_masterThesis_title), getActivity().getString(
				R.string.pdf_masterThesis_url)));
		pdfDocuments.add(new PdfDocument(getActivity().getString(
				R.string.pdf_bakkThesis_title), getActivity().getString(
				R.string.pdf_bakkThesis_url)));
		pdfDocuments.add(new PdfDocument(getActivity().getString(
				R.string.pdf_bakk_report_title), getActivity().getString(
				R.string.pdf_bakk_report_url)));
		pdfDocuments.add(new PdfDocument(getActivity().getString(
				R.string.pdf_master_report_title), getActivity().getString(
				R.string.pdf_master_report_url)));
		return pdfDocuments;
	}
}