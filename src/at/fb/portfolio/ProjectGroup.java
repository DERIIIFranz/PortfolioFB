package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class ProjectGroup implements Parcelable {
	
	private String mCategory;
	private List<Project> mProjects;
	
	private ProjectGroup() {
		mProjects = new ArrayList<Project>();
	}
	
	public ProjectGroup(String category, List<Project> projects) {
		this();
		
		this.mCategory = category;
		this.mProjects = projects;
	}
	
	private ProjectGroup(Parcel in) {
		this();
		
		mCategory = in.readString();
		in.readTypedList(mProjects, Project.CREATOR);
	}
	
	public static final Parcelable.Creator<ProjectGroup> CREATOR = new Parcelable.Creator<ProjectGroup>() {
		public ProjectGroup createFromParcel(Parcel in) {
			return new ProjectGroup(in);
		}

		public ProjectGroup[] newArray(int size) {
			return new ProjectGroup[size];
		}
	};
	
	public String getCategory() {
		return mCategory;
	}
	
	public List<Project> getProjects() {
		return mProjects;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mCategory);
		dest.writeTypedList(mProjects);
		
	}

}
