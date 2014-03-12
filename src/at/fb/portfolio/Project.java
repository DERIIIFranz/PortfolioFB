package at.fb.portfolio;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable {

	public static final String PROJECT_TITLE = "at.fb.portfolio.PROJECT_TITLE";
	public static final String PROJECT_LAYOUT = "at.fb.portfolio.PROJECT_LAYOUT";
	public static final String PROJECT_COLLECTION = "at.fb.portfolio.PROJECT_COLLECTION";
	public static final String PROJECT_POSITION = "at.fb.portfolio.PROJECT_POSITION";
	public static final String PROJECT_GALLERY_IMAGES = "at.fb.portfolio.PROJECT_GALLERY_IMAGES";
	public static final String PROJECT_IMAGE_HEADER = "at.fb.portfolio.PROJECT_IMAGE_HEADER";
	public static final String PROJECT_DESCRIPTION = "at.fb.portfolio.PROJECT_DESCRIPTION";

	private int mThumb, mImgHeader;
	private String mTitle, mDescription;
	private static int sId = 0;
	private int mProjectID;
	private List<GalleryImage> mGalleryImages;

	public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
		public Project createFromParcel(Parcel in) {
			return new Project(in);
		}

		public Project[] newArray(int size) {
			return new Project[size];
		}
	};

	public Project() {
		mGalleryImages = new ArrayList<GalleryImage>();
	}

	public Project(int thumb, int imgHeader, String title, String description,
			List<GalleryImage> galleryImages) {
		mThumb = thumb;
		mImgHeader = imgHeader;
		mTitle = title;
		mDescription = description;
		mGalleryImages = galleryImages;
		mProjectID = sId++;
	}

	private Project(Parcel in) {
		this();

		mThumb = in.readInt();
		mImgHeader = in.readInt();
		mTitle = in.readString();
		mDescription = in.readString();
		in.readTypedList(mGalleryImages, GalleryImage.CREATOR);
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(mThumb);
		out.writeInt(mImgHeader);
		out.writeString(mTitle);
		out.writeString(mDescription);
		out.writeTypedList(mGalleryImages);
	}

	public List<GalleryImage> getGalleryImages() {
		return mGalleryImages;
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

	public int getImgHeader() {
		return mImgHeader;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getDescription() {
		return mDescription;
	}

	@Override
	public int describeContents() {
		return 0;
	}

}
