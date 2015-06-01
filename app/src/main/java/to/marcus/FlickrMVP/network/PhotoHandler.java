package to.marcus.FlickrMVP.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marcus on 5/12/2015
 * to get bitmaps on a background thread.
 */

public class PhotoHandler<Token> extends HandlerThread {
    private static final String TAG = "PhotoHandler";
    Handler mHandler;
    //passed from main thread: for communication back to the mainthread
    Handler mResponseHandler;
    Listener<Token> mListener;
    Map<Token, String> requestMap = Collections.synchronizedMap(new HashMap<Token, String>());

    //get handler from main thread in constructor
    public PhotoHandler(Handler responseHandler){
        super(TAG);
        mResponseHandler = responseHandler;
    }

    public interface Listener<Token>{
        void onPhotoDownloaded(Token token, Bitmap thumbnail);
    }

    public void setListener(Listener<Token> listener){
        mListener = listener;
    }

    //2.
    protected void onLooperPrepared(){
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if (msg.what == 0){
                    Token token = (Token)msg.obj;
                    Log.i(TAG, "2. Got a request for url: "+ requestMap.get(token));
                    handleRequest(token);
                }
            }
        };
    }

    //3.
    private void handleRequest(final Token token){
        try{
            final String url = requestMap.get(token);
            if(url == null)
                return;
            //decode bytes into bitmap from URL
            byte[] bitmapBytes = new GetBytes().getUrlBytes(url);
            final Bitmap bitmap = BitmapFactory
                    .decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
            Log.i(TAG, "3. Bitmap created");

            mResponseHandler.post(new Runnable(){
               public void run(){
                   if(requestMap.get(token) != url)
                       return;
                   requestMap.remove(token);
                   mListener.onPhotoDownloaded(token, bitmap);
               }
            });
        }catch (IOException ioe){
            Log.e(TAG, "Error dowloading image", ioe);
        }
    }

    //1. add passed-in token URL pair to map, send to msgQueue
    public void queueThumbnail(Token token, String url){
        requestMap.put(token, url);
        //create message
        mHandler.obtainMessage(0, token).sendToTarget();
        Log.i(TAG, "1. Got a URL: " + url);
    }

    public void clearQueue(){
        mHandler.removeMessages(0);
        requestMap.clear();
    }
}
