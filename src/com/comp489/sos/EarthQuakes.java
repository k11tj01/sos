package com.comp489.sos;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EarthQuakes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_earth_quakes);
		WebView myWebView = (WebView) findViewById(R.id.webview);
		myWebView.loadUrl("http://http://www.ugr.es/~iag/divulgacion/div_d_e.html");
		myWebView.setWebViewClient(new WebViewClient());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.earth_quakes, menu);
		return true;
	}

}
