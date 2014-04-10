package at.fb.portfolio.projectItems;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;
import at.fb.portfolio.R;
import at.fb.portfolio.views.CustomVideoView;

public class ProjectItemVideo extends ProjectItem {

	public static final String TYPE = "at.fb.portfolio.ProjectItemVideo.TYPE";
	public static final String POS = "at.fb.portfolio.ProjectItemVideo.POS";

	private final int initPos = 10;

	private String mUri;
	private CustomVideoView mVideoView;
	private MediaController mMc;
	private ProgressBar mProgressBar;
	private int mPos;
	private boolean mIsMcVisible;

	public ProjectItemVideo(String uri) {
		mUri = uri;
		mPos = initPos;
		mIsMcVisible = true;
	}

	protected ProjectItemVideo(Parcel in) {
		mUri = in.readString();
		mPos = initPos;
		mIsMcVisible = true;
	}

	@Override
	public View getView(final View rootView, Bundle savedInstanceState) {
		LayoutInflater inflater = (LayoutInflater) rootView.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View relativeLayout = inflater.inflate(R.layout.project_item_video,
				null);

		mVideoView = (CustomVideoView) relativeLayout
				.findViewById(R.id.vv_project_item_video);

		mVideoView.setVideoURI(Uri.parse(mUri));

		mMc = new MediaController(mVideoView.getContext(), false);
		
		//
		// append MediaController
		//
		FrameLayout f = (FrameLayout) mMc.getParent();
		// if not LinearLayout, Layout has already been added
		if (f.getParent().getClass().equals(LinearLayout.class)) {
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.ALIGN_BOTTOM,
					R.id.vv_project_item_video);

			((LinearLayout) f.getParent()).removeView(f);
			((RelativeLayout) mVideoView.getParent()).addView(f, lp);
		}
		
		
		mVideoView.setMediaController(mMc);
		mMc.setAnchorView(mVideoView);

		// resume video
		if (savedInstanceState != null) {
			setPos(savedInstanceState.getInt(ProjectItemVideo.POS));
		}

		//
		// ProgressBar
		//
		mProgressBar = ((ProgressBar) relativeLayout
				.findViewById(R.id.pbar_project_item_video));
		mProgressBar.setVisibility(View.VISIBLE);
		mProgressBar.bringToFront();

		mVideoView
				.setPlayPauseListener(new CustomVideoView.PlayPauseListener() {

					@SuppressWarnings("deprecation")
					@Override
					public void onPlay() {
						mVideoView.setBackgroundDrawable(null);

					}

					@Override
					public void onPause() {
						// TODO Auto-generated method stub

					}
				});

		mVideoView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (mIsMcVisible) {
					mMc.setVisibility(View.INVISIBLE);
				}

				else {
					mMc.setVisibility(View.VISIBLE);
				}

				mIsMcVisible = mIsMcVisible ? false : true;

				return false;
			}
		});

		mVideoView.setOnPreparedListener(new OnPreparedListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onPrepared(MediaPlayer mp) {
				// set placeholder until video gets started which is
				// necessary for streamed videos only (seekTo-method is buggy)
				mVideoView.setBackgroundDrawable(rootView.getContext()
						.getResources()
						.getDrawable(R.drawable.placeholder_video_white));

				mProgressBar.setVisibility(View.GONE);

				// restore playback-position
				mp.seekTo(mPos);
			}

		});

		return relativeLayout;
	}

	public VideoView getVideoView() {
		return mVideoView;
	}

	public void setPos(int p) {
		mPos = p;
	}

	public static final Parcelable.Creator<ProjectItemVideo> CREATOR = new Parcelable.Creator<ProjectItemVideo>() {
		public ProjectItemVideo createFromParcel(Parcel in) {
			return new ProjectItemVideo(in);
		}

		public ProjectItemVideo[] newArray(int size) {
			return new ProjectItemVideo[size];
		}
	};

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
