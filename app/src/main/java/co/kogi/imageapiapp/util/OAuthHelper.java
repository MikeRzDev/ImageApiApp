package co.kogi.imageapiapp.util;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.Lists;
import com.wuman.android.auth.OAuthManager;

import java.io.IOException;
import java.util.concurrent.CancellationException;

/**
 * Created by user on 5/07/2016.
 */
public class OAuthHelper {

    public static void getAuthToken(FragmentActivity activity, final TokenListener tokenListener){

        OAuth oAuth = OAuth.newInstance(activity.getApplicationContext(),
                activity.getSupportFragmentManager(),
                new ClientParametersAuthentication(ImgurAccountParameters.CLIENT_ID,
                        ImgurAccountParameters.CLIENT_SECRET),
                ImgurAccountParameters.AUTH_ENDPOINT,
                ImgurAccountParameters.TOKEN_SERVER_URL,
                ImgurAccountParameters.REDIRECT_URL,
                Lists.<String> newArrayList());



        oAuth.authorizeExplicitly("imgur", new OAuthManager.OAuthCallback<Credential>() {
            @Override
            public void run(OAuthManager.OAuthFuture<Credential> future) {
                try{
                    String token = future.getResult().getAccessToken();
                    tokenListener.onTokenObtained(token);
                }
                catch (CancellationException ex){
                    tokenListener.onTokenRequestFailed();
                    Log.e("exception","CancellationException");

                } catch (IOException e) {
                    tokenListener.onTokenRequestFailed();
                    Log.e("exception","CancellationException");
                }

            }
        });
    }

    public interface TokenListener {
        void onTokenObtained(String token);
        void onTokenRequestFailed();
    }

}
