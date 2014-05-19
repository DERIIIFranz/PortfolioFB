package at.fb.portfolio.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import at.fb.portfolio.PdfDocument;
import at.fb.portfolio.R;

public class DocumentAdapter extends BaseAdapter {
	
	private Context mContext;
	private ArrayList<PdfDocument> mPdfDocuments;
	
	public DocumentAdapter(Context c, ArrayList<PdfDocument> pdfDocuments) {
		mContext = c;
		mPdfDocuments = pdfDocuments;
	}

	@Override
	public int getCount() {
		return mPdfDocuments.size();
	}

	@Override
	public Object getItem(int i) {
		return mPdfDocuments.get(i);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater vi = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View documentView = vi.inflate(R.layout.document_download, null);
		
		ImageButton imgBtn = (ImageButton) documentView.findViewById(R.id.btn_document);		

		// set the Drawables resourceId as the ImageButtons tag
		// so that the id can be compared for testing purposes
		imgBtn.setTag(mPdfDocuments.get(position).getDownloadIcon());
		
		final int currentPosition = position;
		
		imgBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPdfDocuments.get(currentPosition).show(mContext);
			}
		});
		
		TextView tvDoc = (TextView) documentView.findViewById(R.id.tv_document);
		tvDoc.setText(mPdfDocuments.get(position).getTitle());

		return documentView;
	}

}
