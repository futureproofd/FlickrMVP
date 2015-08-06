package to.marcus.FlickrMVP.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import java.util.ArrayList;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.network.PhotoHandler;

/**
 * Created by marcus on 8/4/2015
 * HandlerThread builds images from url portion of Array
 */

public class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoRecyclerAdapter.PhotoViewHolder>{
    private static final String TAG = PhotoRecyclerAdapter.class.getSimpleName();
    private final Context context;
    private LayoutInflater mLayoutInflater;
    private final ArrayList<Photo> mPhotos;
    public PhotoHandler<ImageView> mPhotoHandler;
    public RecyclerViewItemClickListener mListener;
    private int lastPosition = RecyclerView.NO_POSITION;

    public PhotoRecyclerAdapter(Context context, ArrayList<Photo> photos, PhotoHandler handler
            ,RecyclerViewItemClickListener listener){
        this.context = context;
        this.mPhotos = photos;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mPhotoHandler = handler;
        this.mListener = listener;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        final PhotoViewHolder mViewHolder = new PhotoViewHolder(v);
        v.setOnClickListener(new View.OnClickListener(){
            //listen for clicks on Fragment listener
            @Override
            public void onClick(View view){
                mListener.onItemClick(view, mViewHolder.getPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position){
        Photo photo = getItem(position);
        //Start handler thread to get Bitmaps:
        holder.imageView.setImageDrawable(null);
        mPhotoHandler.queueThumbnail(holder.imageView, photo.getUrl());
        setAnimation(holder.imageView, position);
    }

    @Override
    public int getItemCount(){
        return mPhotos == null ? 0 : mPhotos.size();
    }

    /*
    * ViewHolder inner class
    */
    public static class PhotoViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public PhotoViewHolder(View v){
            super(v);
            imageView = (ImageView)v.findViewById(R.id.photo_item_imageView);
        }
    }

    /*
    Helpers
    */
    public Photo getItem(int position) {
        return mPhotos == null ? null : mPhotos.get(position);
    }

    public void setAnimation(View viewToAnimate, int position){
       if (position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
       }
    }

    public interface RecyclerViewItemClickListener{
        public void onItemClick(View v, int position);
    }

}
