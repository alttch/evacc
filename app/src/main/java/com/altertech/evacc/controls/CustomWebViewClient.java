package com.altertech.evacc.controls;

import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.altertech.evacc.core.BaseApplication;

public class CustomWebViewClient extends WebViewClient {
    private BaseApplication application;

    public CustomWebViewClient(BaseApplication application) {
        this.application = application;
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view,
                                          HttpAuthHandler handler, String host, String realm) {
        handler.proceed(this.application.getUserName(), this.application.getUserPassword());
    }
}