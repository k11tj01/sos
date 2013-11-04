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
	String[] choices = {"EARTH QUAKE", "FLOODS", "HURRICANES","WILD FIRES","TORNADOS","TSUNAMI", "WINTER-STORM", "VOLCANO"};
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
		//TextView tv = (TextView) row;
				//String selection = tv.getText().toString();
				switch (i)
				{
				case 0:
					Toast t =  Toast.makeText(this,"Activity with Instructions for Earth Quakes",Toast.LENGTH_LONG);
					t.show();
					Intent i1 = new Intent(this,EarthQuakes.class);
					startActivity(i1);
					break;
				case 1:
					Toast t2 =  Toast.makeText(this,"Activity with Instructions for Floods",Toast.LENGTH_LONG);
					t2.show();
					Intent i2 = new Intent(this,Floods.class);
					startActivity(i2);
					break;
				case 2:
					Toast t3 =  Toast.makeText(this,"Activity with Instructions for Hurricanes",Toast.LENGTH_LONG);
					t3.show();
					Intent i3 = new Intent(this,Hurricanes.class);
					startActivity(i3);
					break;
				case 3:
					Toast t4 = Toast.makeText(this, "Activity with Instructions for Wild Fires", Toast.LENGTH_LONG);
					t4.show();
					Intent i4 = new Intent(this,WildFires.class);
					startActivity(i4);
					break;
				case 4:
					Toast t5 = Toast.makeText(this, "Activity with Instructions for Tornados", Toast.LENGTH_LONG);
					t5.show();
					Intent i5 = new Intent(this,Tornados.class);
					startActivity(i5);
					break;
				case 5:
					Toast t6 = Toast.makeText(this, "Activity with Instructions for Tsunamis", Toast.LENGTH_LONG);
					t6.show();
					Intent i6 = new Intent(this,Tsunami.class);
					startActivity(i6);
					break;
				case 6:
					Toast t7 = Toast.makeText(this, "Activity with Instructions for Winter-storms", Toast.LENGTH_LONG);
					t7.show();
					Intent i7 = new Intent(this,WinterStorm.class);
					startActivity(i7);
					break;
				case 7:
					Toast t8 = Toast.makeText(this, "Activity with Instructions for Volcanos", Toast.LENGTH_LONG);
					t8.show();
					Intent i8 = new Intent(this,Volcano.class);
					startActivity(i8);
					break;
			}	
		}
	}


