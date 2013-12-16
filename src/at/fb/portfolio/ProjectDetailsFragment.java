package at.fb.portfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProjectDetailsFragment extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
    	Bundle args = getArguments();

        View rootView = inflater.inflate(
                args.getInt(Project.PROJECT_LAYOUT), container, false);

        return rootView;
    }

}
