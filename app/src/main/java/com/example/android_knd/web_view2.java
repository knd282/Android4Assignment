package com.example.android_knd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web_view2 extends AppCompatActivity {

    private WebView webView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);


        webView2 = (WebView) findViewById(R.id.webview2);
        //fit screen
        webView2.setInitialScale(1);
        webView2.getSettings().setLoadWithOverviewMode(true);
        webView2.getSettings().setUseWideViewPort(true);
        webView2.getSettings().setJavaScriptEnabled(true);

        webView2.setWebViewClient(new WebViewClient());


        String launchWish = getIntent().getStringExtra("k");
        webView2.loadUrl(launchWish); //load website

    }
}