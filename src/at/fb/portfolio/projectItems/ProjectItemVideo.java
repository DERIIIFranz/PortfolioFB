package at.fb.portfolio.projectItems;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

	public ProjectItemVideo(String uri) {
		mUri = uri;
		mPos = initPos;
	}

	protected ProjectItemVideo(Parcel in) {
		mUri = in.readString();
		mPos = initPos;
	}
	
	// used for delayed call to hide MediaController
	Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			mMc.setVisibility(View.INVISIBLE);
		}
	};

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public View getView(final View rootView, Bundle savedInstanceState, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) rootView.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View relativeLayout = inflater.inflate(R.layout.project_item_video,
				parent, false);

		mVideoView = (CustomVideoView) relativeLayout
				.findViewById(R.id.vv_project_item_video);

		mVideoView.setVideoURI(Uri.parse(mUri));
		mMc = new MediaController(mVideoView.getContext(), false);

		bindMediaControllerToLayout();

		mVideoView.setMediaController(mMc);
		mMc.setAnchorView(mVideoView);

		// resume video
		if (savedInstanceState != null) {
			setPos(savedInstanceState.getInt(ProjectItemVideo.POS));
		}
		
		if (mPos != initPos && mPos > 0) {
			mVideoView.start();
		}

		//
		// ProgressBar
		//
		mProgressBar = ((ProgressBar) relativeLayout
				.findViewById(R.id.pbar_project_item_video));
		mProgressBar.setVisibility(View.VISIBLE);
		mProgressBar.bringToFront();
		mProgressBar.invalidate();
		relativeLayout.requestLayout();
		
		

		mVideoView
				.setPlayPauseListener(new CustomVideoView.PlayPauseListener() {

					@SuppressWarnings("deprecation")
					@Override
					public void onPlay() {
						mVideoView.setBackgroundDrawable(null);

						hideMediaController(3000);
					}

					@Override
					public void onPause() {
						handler.removeCallbacks(runnable);
					}
				});

		mVideoView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				v.performClick();
				if (mMc.isShowing()) {
					mMc.setVisibility(View.INVISIBLE);
				}

				else {
					mMc.setVisibility(View.VISIBLE);
					
					if (mVideoView.isPlaying())
					hideMediaController(3000);
				}

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

				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);

				mVideoView.setLayoutParams(params);

				mProgressBar.setVisibility(View.GONE);

				// restore playback-position
				mp.seekTo(mPos);
			}

		});
		
		return relativeLayout;
	}

	private void bindMediaControllerToLayout() {
		FrameLayout f = (FrameLayout) mMc.getParent();
		// if not LinearLayout, Layout has already been added
		if (f.getParent().getClass().equals(LinearLayout.class)) {
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.vv_project_item_video);

			((LinearLayout) f.getParent()).removeView(f);
			((RelativeLayout) mVideoView.getParent()).addView(f, lp);
		}
	}

	private void hideMediaController(int timeout) {
		handler.postDelayed(runnable, timeout);
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
