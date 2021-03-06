package co.kogi.imageapiapp.device;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import co.kogi.imageapiapp.util.AppContext;


/**
 * Created by oscar on 15/10/15.
 */
public class NetworkService {

    public static boolean hasInternetAccess() {
        ConnectivityManager connMgr = (ConnectivityManager) AppContext.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
    }

}
