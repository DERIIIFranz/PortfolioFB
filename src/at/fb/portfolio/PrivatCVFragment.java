package at.fb.portfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PrivatCVFragment extends Fragment {
	
	private static boolean isVisibleToUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_privat_cv, container, false);
		
		return rootView;
	}
	
	//used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
	    super.setUserVisibleHint(isVisibleToUser);

	    PrivatCVFragment.isVisibleToUser = isVisibleToUser;
	}
	
	public static boolean isVisibleToUser() {
		return isVisibleToUser;
	}

	
}
