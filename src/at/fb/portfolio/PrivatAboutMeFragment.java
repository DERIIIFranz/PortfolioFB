package at.fb.portfolio;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pagesuite.flowtext.FlowTextView;

public class PrivatAboutMeFragment extends Fragment {
	
	private static boolean sIsVisibleToUser = false;
	
	private FlowTextView mTv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_privat_about_me,
				container, false);

		mTv = (FlowTextView) rootView.findViewById(R.id.tv_about_me);  
		mTv.setColor(Color.LTGRAY);
		mTv.setTextSize(30);
		mTv.setText(getActivity().getString(R.string.about_me)); // using plain text    
		mTv.invalidate(); // call this to render the text

		return rootView;
	}
	
	//used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
	    super.setUserVisibleHint(isVisibleToUser);

	    PrivatAboutMeFragment.sIsVisibleToUser = isVisibleToUser;
	}
	
	public static boolean isVisibleToUser() {
		return sIsVisibleToUser;
	}

	public static PrivatAboutMeFragment newInstance(Context ctx) {
		PrivatAboutMeFragment f = new PrivatAboutMeFragment();
		
		Bundle args = new Bundle();
		args.putString(MainActivity.TAB_TITLE, ctx.getString(R.string.tabTitle_privat_fragment_aboutMe));
		
		f.setArguments(args);
		
		return f;
		
	}

}
