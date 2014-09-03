package at.fb.portfolio.projectItems;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import at.fb.portfolio.PdfDocument;
import at.fb.portfolio.R;
import at.fb.portfolio.adapters.DocumentAdapter;
import at.fb.portfolio.views.NonScrollableGridView;

public class ProjectItemPdfDocuments extends ProjectItem {

	public static final String TYPE = "at.fb.portfolio.ProjectItemPdfDocuments.TYPE";
	private ArrayList<PdfDocument> mPdfDocuments;

	private ProjectItemPdfDocuments() {
		mPdfDocuments = new ArrayList<PdfDocument>();
	}

	public ProjectItemPdfDocuments(ArrayList<PdfDocument> pdfDocuments) {
		mPdfDocuments = pdfDocuments;
	}

	@Override
	public View getView(final View rootView, final Bundle savedInstanceState, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) rootView.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		NonScrollableGridView view = (NonScrollableGridView) inflater.inflate(
				R.layout.project_item_pdf_documents, parent, false);

		view.setAdapter(new DocumentAdapter(rootView.getContext(),
				mPdfDocuments));

		return view;
	}

	public static final Parcelable.Creator<ProjectItemPdfDocuments> CREATOR = new Parcelable.Creator<ProjectItemPdfDocuments>() {
		public ProjectItemPdfDocuments createFromParcel(Parcel in) {
			return new ProjectItemPdfDocuments(in);
		}

		public ProjectItemPdfDocuments[] newArray(int size) {
			return new ProjectItemPdfDocuments[size];
		}
	};

	protected ProjectItemPdfDocuments(Parcel in) {
		this();
		in.readTypedList(mPdfDocuments, PdfDocument.CREATOR);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(TYPE);
		dest.writeTypedList(mPdfDocuments);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
