package com.comp489.sos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Tsunami extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tsunami);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tsunami, menu);
		return true;
	}

}
