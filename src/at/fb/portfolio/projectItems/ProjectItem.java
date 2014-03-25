package at.fb.portfolio.projectItems;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public abstract class ProjectItem implements Parcelable {

	/**
	 * Inflates the specific view that can be added to a layout.
	 * @param ctx The context the view should base on.
	 * @return a new view
	 */
	public abstract View getView(View rootView);
	
	public static final Parcelable.Creator<ProjectItem> CREATOR = new Parcelable.Creator<ProjectItem>() {
		public ProjectItem createFromParcel(Parcel in) {

			String type = in.readString();

			if (type.equals(ProjectItemImageHeader.TYPE))
				return new ProjectItemImageHeader(in);

			else if (type.equals(ProjectItemText.TYPE))
				return new ProjectItemText(in);
			
			else if(type.equals(ProjectItemImageGallery.TYPE))
				return new ProjectItemImageGallery(in);
			
			else if(type.equals(ProjectItemVideo.TYPE))
				return new ProjectItemVideo(in);
			
			else
				return null;
		}

		public ProjectItem[] newArray(int size) {
			return new ProjectItem[size];
		}
	};
}
