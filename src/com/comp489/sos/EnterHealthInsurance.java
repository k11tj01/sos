package com.comp489.sos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class EnterHealthInsurance extends Activity {

	EditText provider, policyNo, policyOwner;
	String prov, polNo, polOwn, storeHI = "sosHI.csv";
	
	FileOutputStream out;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_health_insurance);
		provider = (EditText) findViewById(R.id.provider);
		policyNo = (EditText) findViewById(R.id.policyNo);
		policyOwner = (EditText) findViewById(R.id.policyOwner);
		
		

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		
		AlertDialog alertD = alert.create();
		alertD.setTitle("Please enter your Health Insurance Info");
		alertD.show();
		
		prov = provider.getText().toString();
		polNo = policyNo.getText().toString();
		polOwn = policyOwner.getText().toString();
		
		String[] content = {prov, polNo, polOwn};
		
		String csv = "";
		
		for(int j = 0; j < content.length;j++)
		{
			csv += (content[j] + ",");
		}
		
		try {
			out = openFileOutput(storeHI, Context.MODE_PRIVATE);
			out.write(csv.getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_health_insurance, menu);
		return true;
	}

}
