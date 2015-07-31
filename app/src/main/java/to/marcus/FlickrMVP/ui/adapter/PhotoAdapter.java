package to.marcus.FlickrMVP.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.network.PhotoHandler;

/**
 * Created by marcus on 5/11/2015
 * Simple ArrayAdapter to hold images
 * HandlerThread builds images from url portion of Array
 */

public class PhotoAdapter extends ArrayAdapter<Photo> {

    private final Context context;
    private LayoutInflater mLayoutInflater;
    private final ArrayList<Photo> mPhotos;
    private ViewHolder viewHolder;
    PhotoHandler<ImageView> mPhotoHandler;
    private static final String TAG = PhotoAdapter.class.getSimpleName();

    public PhotoAdapter(Context context, ArrayList<Photo> photos, PhotoHandler handler){
        super(context, android.R.layout.simple_list_item_1, photos);
        this.context = context;
        this.mPhotos = photos;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mPhotoHandler = handler;
    }

    static class ViewHolder{
        ImageView imageView;
    }

    @Override
    public int getCount() {
        return mPhotos == null ? 0 : mPhotos.size();
    }

    @Override
    public Photo getItem(int position) {
        return mPhotos == null ? null : mPhotos.get(position);
    }

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = mLayoutInflater.inflate(R.layout.photo_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.photo_item_imageView);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        Photo photo = getItem(position);
        //Start handler thread to get Bitmaps
        mPhotoHandler.queueThumbnail(viewHolder.imageView, photo.getUrl());
        return view;
    }
}
