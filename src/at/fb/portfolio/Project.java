package at.fb.portfolio;

import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable {
	
	public static final String PROJECT_TITLE = "at.fb.portfolio.PROJECT_TITLE";
	public static final String PROJECT_LAYOUT = "at.fb.portfolio.PROJECT_LAYOUT";
	public static final String PROJECT_COLLECTION = "at.fb.portfolio.PROJECT_COLLECTION";
	public static final String PROJECT_POSITION = "at.fb.portfolio.PROJECT_POSITION";
	
	private int thumb;
	private String title;
	private int layout;
	private static int id = 0;
	private int projectID;
	
	public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
	
	public Project(int thumb, String title, int layout) {
		this.thumb = thumb;
		this.title = title;
		this.layout = layout;
		projectID = id++;
	}
	
	private Project (Parcel in) {
        thumb = in.readInt();
        title = in.readString();
        layout = in.readInt();
    }
	
	public int getId() {
		return projectID;
	}
	
	public static void resetId() {
		id = 0;
	}
	
	public int getThumb() {
		return thumb;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getLayout() {
		return layout;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(thumb);
		out.writeString(title);
		out.writeInt(layout);	
	}
}
