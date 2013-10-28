package com.comp489.sos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class NaturalDisasters extends Activity implements AdapterView.OnItemClickListener {
	
	ListView l;
	String[] choices = {"EARTH QUAKE", "FLOODS", "HURRICANES","WILD FIRES","TORNADOS","TSUNAMI"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_natural_disasters);
		l = (ListView) findViewById(R.id.listView1);
		//adapter sends row data to list view, in android specified format (TextView)
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,choices);

		l.setAdapter(adapter);
		//this class implements onItemClickListener
				l.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}
	@SuppressLint("ShowToast")
	@Override
	public void onItemClick(AdapterView<?> listView, View row, int i, long l) {
		//TextView tv = (TextView) row;
				//String selection = tv.getText().toString();
				switch (i)
				{
				case 0:
					Toast t =  Toast.makeText(this,"Activity with Instructions for Earth Quakes",Toast.LENGTH_LONG);
					t.show();
					break;
				case 1:
					Toast t2 =  Toast.makeText(this,"Activity with Instructions for Tornados",Toast.LENGTH_LONG);
					t2.show();
					break;
				case 2:
					Toast t3 =  Toast.makeText(this,"Activity with Instructions for Tsunamis",Toast.LENGTH_LONG);
					t3.show();
					break;
				case 3:
					Toast t4 = Toast.makeText(this, "Activity with Instructions for Floods", Toast.LENGTH_LONG);
				case 4:
					Toast t5 = Toast.makeText(this, "Activity with Instructions for Hurricanes", Toast.LENGTH_LONG);
				case 5:
					Toast t6 = Toast.makeText(this, "Activity with Instructions for Wild Fires", Toast.LENGTH_LONG);	
			}	
		}

	}


