package com.comp489.sos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

public class CPRInstr extends Activity {
	WebView wv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cprinstr);
		//wv = (WebView) findViewById(R.id.webView1);
		//wv.loadUrl("http://depts.washington.edu/learncpr/quickcpr.html");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cprinstr, menu);
		return true;
	}
}
