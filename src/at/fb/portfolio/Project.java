package at.fb.portfolio;

import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable {
	
	public static final String PROJECT_TITLE = "at.fb.portfolio.PROJECT_TITLE";
	public static final String PROJECT_LAYOUT = "at.fb.portfolio.PROJECT_LAYOUT";
	public static final String PROJECT_COLLECTION = "at.fb.portfolio.PROJECT_COLLECTION";
	public static final String PROJECT_POSITION = "at.fb.portfolio.PROJECT_POSITION";
	
	private int mThumb;
	private String mTitle;
	private int mLayout;
	private static int sId = 0;
	private int mProjectID;
	
	public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
	
	public Project(int thumb, String title, int layout) {
		this.mThumb = thumb;
		this.mTitle = title;
		this.mLayout = layout;
		mProjectID = sId++;
	}
	
	private Project (Parcel in) {
        mThumb = in.readInt();
        mTitle = in.readString();
        mLayout = in.readInt();
    }
	
	public int getId() {
		return mProjectID;
	}
	
	public static void resetId() {
		sId = 0;
	}
	
	public int getThumb() {
		return mThumb;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public int getLayout() {
		return mLayout;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(mThumb);
		out.writeString(mTitle);
		out.writeInt(mLayout);	
	}
}
