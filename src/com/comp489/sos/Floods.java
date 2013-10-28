package com.comp489.sos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Floods extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_floods);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.floods, menu);
		return true;
	}

}
