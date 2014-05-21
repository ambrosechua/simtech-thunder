package com.thunder.simtech;

import java.io.Console;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends Activity {
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final WebView webView = (WebView) findViewById(R.id.webView);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url) {
	            String javascript = getString(R.string.injectJS);
	            Log.d(null, javascript);
	            view.loadUrl(javascript);
	        }
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				view.loadUrl(url);
				EditText editText = (EditText) findViewById(R.id.editText);
				editText.setText(webView.getUrl());
				editText.clearFocus();
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
				return false;
			}
		});

		webView.loadUrl("http://www.google.com/");
		
		// You can add other code here if you want.
		
	}
	public void onGo(View view){
		WebView webView = (WebView) findViewById(R.id.webView);
		EditText editText = (EditText) findViewById(R.id.editText);

		if (editText.getText()==null)return;
		String url = editText.getText().toString();
		
		if (!url.contains("http://") && !url.contains("javascript:"))url = "http://"+url;
		webView.loadUrl(url);
		Log.i("AndroidDemo", "Visiting: "+url);
	}
	
	
	
	
	
}