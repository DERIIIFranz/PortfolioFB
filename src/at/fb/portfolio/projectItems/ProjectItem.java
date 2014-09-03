package at.fb.portfolio.projectItems;

import java.io.IOException;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public abstract class ProjectItem implements Parcelable {

	/**
	 * Creates the specific view that can be added to a layout.
	 * @param ctx The context the view should base on.
	 * @return a new view
	 * @throws IOException 
	 */
	public abstract View getView(final View rootView, final Bundle savedInstanceState, final ViewGroup parent);
	
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
			
			else if(type.equals(ProjectItemPdfDocuments.TYPE))
				return new ProjectItemPdfDocuments(in);
			
			else if(type.equals(ProjectItemObj.TYPE))
				return new ProjectItemObj(in);
			
			else
				throw new RuntimeException("ProjectItemTYPE not handled in ProjectItem.java");
		}

		public ProjectItem[] newArray(int size) {
			return new ProjectItem[size];
		}
	};
}
