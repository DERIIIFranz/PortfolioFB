package at.fb.portfolio.projectItems;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import at.fb.portfolio.R;
import at.fb.portfolio.views.ResizableImageView;

public class ProjectItemImageHeader extends ProjectItem {

	public static final String TYPE = "at.fb.portfolio.ProjectItemImageHeader.TYPE";
	private int mImgHeader;

	public ProjectItemImageHeader(int img) {
		mImgHeader = img;
	}

	@Override
	public View getView(View rootView) {
		LayoutInflater inflater = (LayoutInflater) rootView.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ResizableImageView view = (ResizableImageView) inflater.inflate(
				R.layout.project_item_image_header, null);

		view.setImageResource(mImgHeader);
		return view;
	}

	public static final Parcelable.Creator<ProjectItemImageHeader> CREATOR = new Parcelable.Creator<ProjectItemImageHeader>() {
		public ProjectItemImageHeader createFromParcel(Parcel in) {
			return new ProjectItemImageHeader(in);
		}

		public ProjectItemImageHeader[] newArray(int size) {
			return new ProjectItemImageHeader[size];
		}
	};

	public ProjectItemImageHeader(Parcel in) {
		mImgHeader = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(TYPE);
		dest.writeInt(mImgHeader);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
