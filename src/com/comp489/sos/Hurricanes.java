package com.comp489.sos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Hurricanes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hurricanes);
		WebView myWebView = (WebView) findViewById(R.id.webview);
		myWebView.loadUrl("http://www.redcross.org/prepare/disaster/hurricane");
		myWebView.setWebViewClient(new WebViewClient());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hurricanes, menu);
		return true;
	}

}
