package co.kogi.imageapiapp.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by user on 5/07/2016.
 */
public class AppContext extends Application {

    private static DisplayImageOptions imageOptions;
    private static Context context;
    private static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        if (imageOptions == null) {
            imageOptions = new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .build();
        }

        ImageLoaderConfiguration conf = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(conf);

        if (context == null){
            context = getApplicationContext();
        }

        if (gson == null){
            gson = new Gson();
        }

        Dexter.initialize(this);

    }

    public static Context getContext(){
        return context;
    }

    public static DisplayImageOptions getImageOptions(){
        return imageOptions;
    }

    public static Gson getGson(){
        return gson;
    }

    public static void addPreference(String key, String value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.edit().putString(key,value).commit();
    }

    public static String getPreference(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        return prefs.getString(key,"");
    }
}
