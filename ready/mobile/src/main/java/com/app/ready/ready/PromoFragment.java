package com.app.ready.ready;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PromoFragment extends Fragment {
    private WebView wbView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_promo, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wbView = (WebView) view.findViewById(R.id.wbView);
        wbView.setWebViewClient(new MyBrowser());
        wbView.getSettings().setLoadsImagesAutomatically(true);
        wbView.getSettings().setJavaScriptEnabled(true);
        wbView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wbView.loadUrl("https://www.pelando.com.br/grupo/restaurantes");
    }

    private class MyBrowser extends WebViewClient {
        public boolean overrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
