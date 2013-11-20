package com.comp489.sos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHealthInsurance extends Activity {

	TextView provider, policyNo, policyOwner;
	String providerStr, policyNoStr, policyOwnerStr, collect, loadFile;
	FileInputStream in;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_health_insurance);
		provider = (TextView) findViewById(R.id.providerView);
		policyNo = (TextView) findViewById(R.id.policyNoView);
		policyOwner = (TextView) findViewById(R.id.policyOwnerView);
		
		if (provider == null) { Log.w("", "provider is null"); }
		
		if (policyNo == null) { Log.w("", "policyNo is null"); }
		if (policyOwner == null) { Log.w("", "policyOwner is null"); }
		
		 collect = null;
		 loadFile = getResources().getString(R.string.sosFile);
		
		
		//working with text, but this also would work with generic bytes
		//instead of PrintWriter
		 in = null;
		
		try 
		{
			in = openFileInput(loadFile);
			byte[] dataArray = new byte[in.available()];
			
			//sentinel value for bytes
			while(in.read(dataArray) != -1)
			{
				collect = new String(dataArray);
				//Toast.makeText(this, collect, Toast.LENGTH_LONG).show();
			}
			
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				in.close();
				//Toast.makeText(getBaseContext(), collect, Toast.LENGTH_LONG ).show();
				//t.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//load this file to database
		//delimits based on commas
		StringTokenizer tokenizer = new StringTokenizer(collect, ",");
		
		
			 providerStr = tokenizer.nextToken();
			 policyNoStr = tokenizer.nextToken();
			 policyOwnerStr = tokenizer.nextToken();
		
	//Toast.makeText(this, providerStr, Toast.LENGTH_LONG).show();
	//Toast.makeText(this, policyNoStr, Toast.LENGTH_LONG).show();
	//Toast.makeText(this, policyOwnerStr, Toast.LENGTH_LONG).show();
		provider.setText("Provider: " + providerStr);
		policyNo.setText("Policy Number: "+ policyNoStr);
		policyOwner.setText("Policy Owner "+ policyOwnerStr);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_health_insurance, menu);
		return true;
	}

}
