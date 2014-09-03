package at.fb.portfolio.projectItems;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import at.fb.portfolio.ObjActivity;
import at.fb.portfolio.R;

public class ProjectItemObj extends ProjectItem {

	public static final String TYPE = "at.fb.portfolio.ProjectItemObj.TYPE";
	public static final String PATH = "at.fb.portfolio.ProjectItemObj.PATH";
	private String mPath;

	public ProjectItemObj(String path) {
		mPath = path;
	}

	public static final Parcelable.Creator<ProjectItemObj> CREATOR = new Parcelable.Creator<ProjectItemObj>() {
		public ProjectItemObj createFromParcel(Parcel in) {
			return new ProjectItemObj(in);
		}

		public ProjectItemObj[] newArray(int size) {
			return new ProjectItemObj[size];
		}
	};

	protected ProjectItemObj(Parcel in) {
		mPath = in.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(TYPE);
		dest.writeString(mPath);
	}

	@Override
	public View getView(final View rootView, Bundle savedInstanceState, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) rootView.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Button btn = (Button) inflater.inflate(R.layout.project_item_obj, parent, false);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(rootView.getContext(),
						ObjActivity.class);

				Bundle bundle = new Bundle();
				bundle.putString(PATH, mPath);

				intent.putExtras(bundle);

				rootView.getContext().startActivity(intent);
			}
		});

		return btn;
	}
}
