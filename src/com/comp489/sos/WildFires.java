package com.comp489.sos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WildFires extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wild_fires);
//		WebView myWebView = (WebView) findViewById(R.id.webview);
//		myWebView.loadUrl("http://www.redcross.org/prepare/disaster/wildfire");
//		myWebView.setWebViewClient(new WebViewClient());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wild_fires, menu);
		return true;
	}

}
