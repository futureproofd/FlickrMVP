package to.marcus.FlickrMVP.data;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import to.marcus.FlickrMVP.model.Photo;

/**
 * Created by marcus on 8/17/2015
 */

public class PhotoJSONer {
    private Context mContext;
    private String mFilename;
    private static final String JSON_TITLE = "title";
    private static final String JSON_URL = "url";

    public PhotoJSONer(Context c, String f){
        mContext = c;
        mFilename = f;
    }

    //Save to JSON
    public void savePhotos(ArrayList<Photo> photos) throws JSONException, IOException{
        JSONArray array = new JSONArray();
        for(Photo p : photos)
            array.put(toJSON(p));

        Writer writer = null;
        try{
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        }finally {
            if(writer != null)
                writer.close();
        }
    }

    //conversion
    public JSONObject toJSON(Photo photo) throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_TITLE, photo.getTitle());
        json.put(JSON_URL, photo.getUrl());
        return json;
    }

    //load JSON file back into an arrayList of contact objects
    public ArrayList<Photo> loadPhotos() throws IOException, JSONException{
        ArrayList<Photo> photos = new ArrayList<Photo>();
        BufferedReader reader = null;
        try{
            //open and read the file into a stringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            //parse JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            //Build the array of contacts from JSONObjects (call the custom constructor)
            for (int i = 0; i < array.length(); i++){
                photos.add(new Photo(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e){

        } finally{
            if (reader != null)
                reader.close();
        }
        return photos;
    }
}
