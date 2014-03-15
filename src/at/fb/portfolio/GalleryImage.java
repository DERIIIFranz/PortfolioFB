package at.fb.portfolio;

import android.os.Parcel;
import android.os.Parcelable;

public class GalleryImage implements Parcelable {
	private int mThumb;
	private int mDrawable;
	private String mDescription;
	
	public static final String IMAGE_DESCRIPTION = "at.fb.portfolio.IMAGE_DESCRIPTION";
	public static final String IMAGE_POSITION = "at.fb.portfolio.IMAGE_POSITION";
	public static final String IMAGE_COLLECTION = "at.fb.portfolio.IMAGE_COLLECTION";
	public static final String IMAGE = "at.fb.portfolio.IMAGE";
	
	public static final Parcelable.Creator<GalleryImage> CREATOR = new Parcelable.Creator<GalleryImage>() {
        public GalleryImage createFromParcel(Parcel in) {
            return new GalleryImage(in);
        }

        public GalleryImage[] newArray(int size) {
            return new GalleryImage[size];
        }
    };

	public GalleryImage(int thumb, int drawable, String description) {
		mThumb = thumb;
		mDrawable = drawable;
		mDescription = description;
	}
	
	public GalleryImage(Parcel in) {
		mThumb = in.readInt();
		mDrawable = in.readInt();
		mDescription = in.readString();
	}

	public int getThumb() {
		return mThumb;
	}

	public int getDrawable() {
		return mDrawable;
	}

	public String getDescription() {
		return mDescription;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mThumb);
		dest.writeInt(mDrawable);
		dest.writeString(mDescription);
		
	}
}