package com.comp489.sos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterHealthInsurance extends Activity {

	EditText provider, policyNo, policyOwner;
	String prov, polNo, polOwn, storeHI;
	Resources res;
	
	FileOutputStream out;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_health_insurance);
		provider = (EditText) findViewById(R.id.provider);
		policyNo = (EditText) findViewById(R.id.policyNo);
		policyOwner = (EditText) findViewById(R.id.policyOwner);
		
		res = this.getResources();
		
		if (provider == null) { Log.w("", "provider is null"); }
		
		if (policyNo == null) { Log.w("", "policyNo is null"); }
		if (policyOwner == null) { Log.w("", "policyOwner is null"); }
		
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
		
		Button enter = (Button) findViewById(R.id.enter);
		
		enter.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				storeHI = res.getString(R.string.sosFile);
				// TODO Auto-generated method stub
				prov = provider.getText().toString();
				polNo = policyNo.getText().toString();
				polOwn = policyOwner.getText().toString();
				
				String[] content = {prov, polNo, polOwn};
				
				//read in fields and write to file
				String csv = "";
				
				for(int j = 0; j < content.length;j++)
				{
					csv += (content[j] + ",");
				}
				
				try{
					out = openFileOutput(storeHI, Context.MODE_PRIVATE);
					out.write(csv.getBytes());
					//out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
						Toast.makeText(getBaseContext(), "Health Insurance Info Saved", Toast.LENGTH_LONG).show();
						
						String collect = null;
						String loadFile = getResources().getString(R.string.sosFile);
						
						/*
						//working with text, but this also would work with generic bytes
						//instead of PrintWriter
						 FileInputStream in = null;
						
						try 
						{
						
						
								in = openFileInput(loadFile);
							byte[] dataArray = new byte[in.available()];
							
							//sentinel value for bytes
							while(in.read(dataArray) != -1)
							{
								collect = new String(dataArray);
								Toast.makeText(getBaseContext(), collect, Toast.LENGTH_LONG).show();
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
						
						while(tokenizer.hasMoreTokens())
						{
							Toast.makeText(getBaseContext(), tokenizer.nextToken(), Toast.LENGTH_LONG).show();
							 
						}*/
				}
				
			}
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_health_insurance, menu);
		return true;
	}

}
