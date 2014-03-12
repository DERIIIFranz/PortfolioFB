package at.fb.portfolio.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import at.fb.portfolio.GalleryImage;

public class GalleryImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<GalleryImage> mGalleryImages;
    

    public GalleryImageAdapter(Context c, ArrayList<GalleryImage> galleryImages) {
        mContext = c;
        mGalleryImages = galleryImages;
    }

    public int getCount() {
        return mGalleryImages.size();
    }

    public GalleryImage getItem(int position) {
        return mGalleryImages.get(position);
    }

    public long getItemId(int position) {
        return position;
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

        imageView.setImageResource(mGalleryImages.get(position).getThumb());
        imageView.setContentDescription(mGalleryImages.get(position).getDescription());
        return imageView;
    }
}