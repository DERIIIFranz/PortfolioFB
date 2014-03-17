package at.fb.portfolio;

import android.content.Context;
import at.fb.portfolio.tools.PDFTools;

public class PdfDocument {

	private String mTitle;
	private String mUrl;
	private int mDownloadIcon;
	private int mDownloadIconDescr;
	private Context mContext;

	public PdfDocument(Context c, String title, String url) {
		mTitle = title;
		mUrl = url;
		mContext = c;

		mDownloadIcon = R.drawable.ic_action_download;
		mDownloadIconDescr = R.string.ic_action_download_descr;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getUrl() {
		return mUrl;
	}

	public int getDownloadIcon() {
		return mDownloadIcon;
	}

	public String getDownloadIconDescr() {
		return mContext.getString(mDownloadIconDescr);
	}

	public void show() {
		PDFTools.showPDFUrl(mContext, mUrl);
	}
}
