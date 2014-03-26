package at.fb.portfolio;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import at.fb.portfolio.adapters.DocumentAdapter;
import at.fb.portfolio.adapters.GalleryThumbAdapter;

public class PrivatDocsFragment extends Fragment {

	private static boolean sIsVisibleToUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_privat_documents,
				container, false);

		GridView gvDownloads = (GridView) rootView
				.findViewById(R.id.gv_privat_docs_download);

		if (getPdfDocuments() != null && getPdfDocuments().size() > 0) {
			gvDownloads.setAdapter(new DocumentAdapter(getActivity(),
					getPdfDocuments()));
		}

		GridView gvGallery = (GridView) rootView
				.findViewById(R.id.gv_privat_docs_gallery);

		if (getGalleryImages() != null && getGalleryImages().size() > 0) {
			gvGallery.setAdapter(new GalleryThumbAdapter(getActivity(),
					getGalleryImages()));

			/**
			 * start ImageGalleryActivity on galleryImageThumbnail clicked and
			 * pass all galleryImages of this project
			 */
			gvGallery.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {

					Intent intent = new Intent(getActivity(),
							ImageGalleryActivity.class);

					intent.putExtra(GalleryImage.IMAGE_POSITION, position);
					intent.putParcelableArrayListExtra(
							GalleryImage.IMAGE_COLLECTION, getGalleryImages());
					startActivity(intent);
				}
			});
		}
		return rootView;
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

		pdfDocuments
				.add(new PdfDocument(getActivity().getString(
						R.string.privat_docs_cv_title),
						"http://www.pdf-archive.com/2014/03/05/lebenslauf/lebenslauf.pdf"));
		pdfDocuments
				.add(new PdfDocument(getActivity().getString(
						R.string.privat_docs_masterThesis_title),
						"http://www.pdf-archive.com/2014/03/05/masterthesis/masterthesis.pdf"));
		pdfDocuments
				.add(new PdfDocument(getActivity().getString(
						R.string.privat_docs_bakkThesis_title),
						"http://www.pdf-archive.com/2014/03/05/bachelorthesis/bachelorthesis.pdf"));

		return pdfDocuments;
	}
}
