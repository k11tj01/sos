package com.comp489.sos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.*;
public class MedicalActivity extends Activity implements AdapterView.OnItemClickListener {

	ListView list;
	String[] choices = {"CPR", "STROKE", "HEIMLICH MANEUVER"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medical);
		
		list = (ListView) findViewById(R.id.listView1);
		
		//adapter sends row data to list view, in android specified format (TextView)
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,choices);
		
		list.setAdapter(adapter);
		
		//this class implements onItemClickListener
		list.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> listView, View row, int i, long l) {
		// TODO Auto-generated method stub
		//TextView tv = (TextView) row;
		//String selection = tv.getText().toString();

		switch (i)
		{
			case 0:
//				Toast t =  Toast.makeText(this,"Activity with CPR Instructions",Toast.LENGTH_LONG);
//				t.show();
//				Intent i1 = new Intent(this, CPRInstr.class);			
//				startActivity(i1);
				break;
			case 1:
				Toast t2 =  Toast.makeText(this,"Activity with Stroke Symptoms",Toast.LENGTH_LONG);
				t2.show();
				break;
			case 2:
				Toast t3 =  Toast.makeText(this,"Activity with Heimlich Instructions",Toast.LENGTH_LONG);
				t3.show();
				break;
				
		}	
	}
}
