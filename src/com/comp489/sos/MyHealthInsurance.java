package com.comp489.sos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class MyHealthInsurance extends Activity {

	EditText provider, policyNo, policyOwner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_health_insurance);
//		provider = (EditText) findViewById(R.layout.provider);
//		policyNo = (EditText) findViewById(R.layout.policyNo);
//		policyOwner = (EditText) findViewById(R.layout.policyOwner);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_health_insurance, menu);
		return true;
	}

}
