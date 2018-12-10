package com.example.mrhjs.ocha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {
    private WebView mWebview;
    private WebSettings mWebSettings;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String url = getIntent().getExtras().getString("shop_url");
        Toolbar wtoolbar = (Toolbar) findViewById(R.id.wtoolbar);
        setSupportActionBar(wtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        super.setTitle("Webview");
        mWebview = (WebView) findViewById(R.id.webview);
        mWebview.setWebViewClient(new WebViewClient());
        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);

        mWebview.loadUrl(url);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
