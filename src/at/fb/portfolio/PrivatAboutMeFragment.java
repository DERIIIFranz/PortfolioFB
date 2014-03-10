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
	
	private static boolean isVisibleToUser = false;
	
	private FlowTextView tv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_privat_about_me,
				container, false);

		tv = (FlowTextView) rootView.findViewById(R.id.tv_about_me);  
		tv.setColor(Color.LTGRAY);
		tv.setTextSize(30);
		tv.setText(getActivity().getString(R.string.about_me)); // using plain text    
		tv.invalidate(); // call this to render the text

		return rootView;
	}
	
	//used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
	    super.setUserVisibleHint(isVisibleToUser);

	    PrivatAboutMeFragment.isVisibleToUser = isVisibleToUser;
	}
	
	public static boolean isVisibleToUser() {
		return isVisibleToUser;
	}

	public static PrivatAboutMeFragment newInstance(Context ctx) {
		PrivatAboutMeFragment f = new PrivatAboutMeFragment();
		
		Bundle args = new Bundle();
		args.putString(MainActivity.TAB_TITLE, ctx.getString(R.string.tabTitle_privat_fragment_aboutMe));
		
		f.setArguments(args);
		
		return f;
		
	}

}
