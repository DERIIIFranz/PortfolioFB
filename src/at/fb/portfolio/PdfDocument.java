package at.fb.portfolio;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import at.fb.portfolio.tools.PDFTools;

public class PdfDocument implements Parcelable{

	private String mTitle;
	private String mUrl;
	private int mDownloadIcon = R.drawable.ic_action_download;
	private int mDownloadIconDescr = R.string.ic_action_download_descr;

	public PdfDocument(String title, String url) {
		mTitle = title;
		mUrl = url;	
	}
	
	public static final Parcelable.Creator<PdfDocument> CREATOR = new Parcelable.Creator<PdfDocument>() {
		public PdfDocument createFromParcel(Parcel in) {
			return new PdfDocument(in);
		}

		public PdfDocument[] newArray(int size) {
			return new PdfDocument[size];
		}
	};

	public String getTitle() {
		return mTitle;
	}

	public String getUrl() {
		return mUrl;
	}

	public int getDownloadIcon() {
		return mDownloadIcon;
	}

	public String getDownloadIconDescr(Context ctx) {
		return ctx.getString(mDownloadIconDescr);
	}

	public void show(Context ctx) {
		PDFTools.showPDFUrl(ctx, mUrl);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private PdfDocument(Parcel in) {
		mTitle = in.readString();
		mUrl = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mTitle);
		dest.writeString(mUrl);
	}
}
