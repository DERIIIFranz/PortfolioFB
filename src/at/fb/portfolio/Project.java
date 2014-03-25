package at.fb.portfolio;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import at.fb.portfolio.projectItems.ProjectItem;

public class Project implements Parcelable {

	public static final String PROJECT_COLLECTION = "at.fb.portfolio.PROJECT_COLLECTION";
	public static final String PROJECT_POSITION = "at.fb.portfolio.PROJECT_POSITION";
	public static final String PROJECT = "at.fb.portfolio.PROJECT";

	private int mThumb;
	private String mTitle;
	private static int sId = 0;
	private int mProjectID;
	private ArrayList<ProjectItem> mProjectItems;

	public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
		public Project createFromParcel(Parcel in) {
			return new Project(in);
		}

		public Project[] newArray(int size) {
			return new Project[size];
		}
	};

	public Project() {
		mProjectItems = new ArrayList<ProjectItem>();
	//	mGalleryImages = new ArrayList<GalleryImage>();
	}

	public Project(int thumb, String title, ArrayList<ProjectItem> pItems) {
		mThumb = thumb;
//		mImgHeader = imgHeader;
		mTitle = title;
		mProjectItems = pItems;
//		mDescription = description;
//		mGalleryImages = galleryImages;
//		mVideoUrl = vUrl;
		mProjectID = sId++;
	}
	
	

	private Project(Parcel in) {
		this();

		mThumb = in.readInt();
//		mImgHeader = in.readInt();
		mTitle = in.readString();
//		mDescription = in.readString();
		in.readTypedList(mProjectItems, ProjectItem.CREATOR);
//		in.readTypedList(mGalleryImages, GalleryImage.CREATOR);
//		mVideoUrl = in.readString();
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(mThumb);
//		out.writeInt(mImgHeader);
		out.writeString(mTitle);
		out.writeTypedList(mProjectItems);
//		out.writeString(mDescription);
//		out.writeTypedList(mGalleryImages);
//		out.writeString(mVideoUrl);
	}
/*
	public ArrayList<GalleryImage> getGalleryImages() {
		return mGalleryImages;
	}
*/
	public ArrayList<ProjectItem> getProjectItems() {
		return mProjectItems;
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
/*
	public int getImgHeader() {
		return mImgHeader;
	}
*/
	public String getTitle() {
		return mTitle;
	}
/*
	public String getDescription() {
		return mDescription;
	}
	
	public String getVideoUrl() {
		return mVideoUrl;
	}
*/
	@Override
	public int describeContents() {
		return 0;
	}

}
