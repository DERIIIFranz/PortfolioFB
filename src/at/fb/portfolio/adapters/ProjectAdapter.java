package at.fb.portfolio.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import at.fb.portfolio.Project;

public class ProjectAdapter extends BaseAdapter {
    private Context mContext;
    private List<Project> mProjects;
    

    public ProjectAdapter(Context c, List<Project> projects) {
        mContext = c;
        this.mProjects = projects;
    }

    public int getCount() {
        return mProjects.size();
    }

    public Project getItem(int position) {
        return mProjects.get(position);
    }

    public long getItemId(int position) {
        return mProjects.get(position).getId();
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
     //       imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mProjects.get(position).getThumb());
        imageView.setContentDescription(mProjects.get(position).getTitle());
        return imageView;
    }
}