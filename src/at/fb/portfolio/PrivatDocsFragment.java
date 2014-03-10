package at.fb.portfolio;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PrivatDocsFragment extends Fragment {
	
	private static boolean sIsVisibleToUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_privat_documents, container, false);
		
		return rootView;
	}
	
	//used for testing if fragment is visible
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
	    super.setUserVisibleHint(isVisibleToUser);

	    PrivatDocsFragment.sIsVisibleToUser = isVisibleToUser;
	}
	
	public static boolean isVisibleToUser() {
		return sIsVisibleToUser;
	}

	public static PrivatDocsFragment newInstance(Context ctx) {
		PrivatDocsFragment f = new PrivatDocsFragment();
		
		Bundle args = new Bundle();
		args.putString(MainActivity.TAB_TITLE, ctx.getString(R.string.tabTitle_privat_fragment_documents));
		
		f.setArguments(args);
		
		return f;
		
	}
}
