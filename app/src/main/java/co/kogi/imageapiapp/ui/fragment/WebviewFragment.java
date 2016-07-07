package co.kogi.imageapiapp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import co.kogi.imageapiapp.R;
import co.kogi.imageapiapp.util.UIHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebviewFragment extends Fragment {


    WebView webView;
    public WebviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIHelper.changeAppBarTitle(getActivity(),"");
        webView = (WebView) getActivity().findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setDomStorageEnabled(true);
        String profile = getArguments().getString("profile");
        if (profile != null) {
            UIHelper.changeAppBarTitle(getActivity(), profile + " " +UIHelper.getStringResource(R.string.profile));
            String url = "http://" + profile + ".imgur.com";
            webView.loadUrl(url);
        }


    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }
    }
}
