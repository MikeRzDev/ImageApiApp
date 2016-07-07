package co.kogi.imageapiapp.util;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

/**
 * Created by user on 29/05/2016.
 */
public class PermissionHelper {

    public interface PermissionGrantedListener{
        void onPermissionGranted();
    }

    private static final void requestPermission(final Activity activity,
                                                final PermissionGrantedListener listener,
                                                final String permission,
                                                final String rationaleMsg,
                                                final String permanentDeniedMsg
                                                ){
        if (Dexter.isRequestOngoing()) {
            return;
        }


        Dexter.checkPermission(new PermissionListener() {
            @Override public void onPermissionGranted(PermissionGrantedResponse response) {
             listener.onPermissionGranted();
            }
            @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                if (response.isPermanentlyDenied()) {
                    UIHelper.showInformationDialog(activity, "Information",
                            permanentDeniedMsg
                            , null);
                }
            }
            @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, final PermissionToken token) {
                UIHelper.showInformationDialog(activity, "Information",
                        rationaleMsg, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                token.continuePermissionRequest();
                            }
                        });

            }
        }, permission);
    }

    public static final void requestStoragePermission(Activity activity, PermissionGrantedListener listener){
        requestPermission(activity,listener,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                "In order to download the article feed you must enable this permission",
                "In order to download the article feed you must follow this steps:" +
                        " Go to Settings > Apps > Feedster > Permissions > Storage");
    }

}
