package com.comp489.sos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Tsunami extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tsunami);
		WebView myWebView = (WebView) findViewById(R.id.webview);
		myWebView.loadUrl("http://www.redcross.org/prepare/disaster/tsunami");
		myWebView.setWebViewClient(new WebViewClient());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tsunami, menu);
		return true;
	}

}
