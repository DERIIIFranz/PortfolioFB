package at.fb.portfolio.projectItems;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import at.fb.portfolio.R;

public class ProjectItemVideo extends ProjectItem {

	public static final String TYPE = "at.fb.portfolio.ProjectItemVideo.TYPE";
	private String mUri;

	public ProjectItemVideo(String uri) {
		mUri = uri;
	}

	@Override
	public View getView(final View rootView) {
		LayoutInflater inflater = (LayoutInflater) rootView.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View relativeLayout = inflater.inflate(
				R.layout.project_item_video, null);

		final VideoView videoView = (VideoView) relativeLayout
				.findViewById(R.id.vv_project_item_video);
		videoView.setVideoPath(mUri);

		//
		// ProgressBar
		//
		final ProgressBar pBar = ((ProgressBar) relativeLayout
				.findViewById(R.id.pbar_project_item_video));
		pBar.setVisibility(View.VISIBLE);
		pBar.bringToFront();

		videoView.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {

				MediaController mc = new MediaController(rootView.getContext(), false);

				videoView.setMediaController(mc);
				mc.setAnchorView(videoView);
				pBar.setVisibility(View.GONE);
				mc.show(0);
				
				// set correct height
				RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
			    params.height =  mp.getVideoHeight();
			    videoView.setLayoutParams(params);
			}
		});

		return relativeLayout;
	}

	public static final Parcelable.Creator<ProjectItemVideo> CREATOR = new Parcelable.Creator<ProjectItemVideo>() {
		public ProjectItemVideo createFromParcel(Parcel in) {
			return new ProjectItemVideo(in);
		}

		public ProjectItemVideo[] newArray(int size) {
			return new ProjectItemVideo[size];
		}
	};

	public ProjectItemVideo(Parcel in) {
		mUri = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(TYPE);
		dest.writeString(mUri);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
