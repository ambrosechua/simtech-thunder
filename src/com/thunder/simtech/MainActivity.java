package com.thunder.simtech;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends Activity {

    // The onCreate method is called when the Activity is created. Code to instantiate the UI should be placed here.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// This call tells the system to inflate the user interface defined in main.xml as the User Interface for this Activity.
		setContentView(R.layout.activity_main); 

        // The WebView needs to use a modified WebViewClient that does not delegate the URL to the default browser.
		final WebView webView = (WebView) findViewById(R.id.webView);
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				view.loadUrl(url);
				
				// Gets the editText
				EditText editText = (EditText) findViewById(R.id.editText);
				// Updates the URL
				editText.setText(webView.getUrl());
				
				// Hides the soft keyboard
				editText.clearFocus();
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

				return false; // Tells the system not to continue propagating the event.
			}
		});

		webView.loadUrl("http://beta.typega.me:4006/dl/");
		
		// You can add other code here if you want.
	}
    
    // Tell the Button you create to call this method, by setting the android:onCreate attribute.
	public void onGo(View view){
		WebView webView = (WebView) findViewById(R.id.webView);
		EditText editText = (EditText) findViewById(R.id.editText);

		if (editText.getText()==null)return;
		String url = editText.getText().toString();
		
//		if (url.equals("man man")) {
//			Intent i = new Intent(this, AboutActivity.class);
//		    startActivity(i);
//			return;
//		}
		
		if (!url.contains("http://"))url = "http://"+url;
		webView.loadUrl(url);
		Log.i("AndroidDemo", "Visiting: "+url);
	}
	
	
	
	
	
}