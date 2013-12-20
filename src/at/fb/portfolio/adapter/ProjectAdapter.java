package at.fb.portfolio.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import at.fb.portfolio.Project;

public class ProjectAdapter extends BaseAdapter {
    private Context mContext;
    private List<Project> projects;
    

    public ProjectAdapter(Context c, List<Project> projects) {
        mContext = c;
        this.projects = projects;
    }

    public int getCount() {
        return projects.size();
    }

    public Project getItem(int position) {
        return projects.get(position);
    }

    public long getItemId(int position) {
        return projects.get(position).getId();
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(projects.get(position).getThumb());
        imageView.setContentDescription(projects.get(position).getTitle());
        return imageView;
    }
}