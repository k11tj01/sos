package com.comp489.sos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class EnterHealthInsurance extends Activity {

	EditText provider, policyNo, policyOwner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_health_insurance);
		provider = (EditText) findViewById(R.id.provider);
		policyNo = (EditText) findViewById(R.id.policyNo);
		policyOwner = (EditText) findViewById(R.id.policyOwner);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_health_insurance, menu);
		return true;
	}

}
