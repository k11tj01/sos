package com.comp489.sos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NaturalDisasters extends Activity implements AdapterView.OnItemClickListener {
	
	ListView l;
	String[] choices = {"EARTHQUAKE", "FLOODS", "HURRICANES","WILD FIRES","TORNADOS","TSUNAMI"};
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
		getMenuInflater().inflate(R.menu.natural_disasters, menu);
		return true;
	}
	@SuppressLint("ShowToast")
	@Override
	public void onItemClick(AdapterView<?> listView, View row, int i, long l) {
				switch (i)
				{
				case 0:
					Intent i1 = new Intent(this,EarthQuakes.class);
					startActivity(i1);
					break;
				case 1:
					Intent i2 = new Intent(this,Floods.class);
					startActivity(i2);
					break;
				case 2:
					Intent i3 = new Intent(this,Hurricanes.class);
					startActivity(i3);
					break;
				case 3:
					Intent i4 = new Intent(this,WildFires.class);
					startActivity(i4);
					break;
				case 4:
					Intent i5 = new Intent(this,Tornados.class);
					startActivity(i5);
					break;
				case 5:
					Intent i6 = new Intent(this,Tsunami.class);
					startActivity(i6);
					break;
			}	
		}
	}


