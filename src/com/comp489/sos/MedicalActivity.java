package com.comp489.sos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
public class MedicalActivity extends Activity implements AdapterView.OnItemClickListener {

	ListView list;
	Button alert;
	String[] choices = {"UNCONSCIOUS", "CPR", "STROKE", "CHOKING", "HEAD/NECK/SPINE INJURY", "SEVERE BLEEDING"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medical);
		
		list = (ListView) findViewById(R.id.listView1);
		
		OnClickListener listener = new View.OnClickListener()
		{

			@Override
			public void onClick(View b) {
				// TODO Auto-generated method stub
				
				
				
			}
			
		};
		
		alert.setOnClickListener(listener);
		
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
				startActivity(new Intent(this, Unconscious.class));
				break;	
			case 1:
				Intent i1 = new Intent(this, CPR.class);			
				startActivity(i1);
				break;
			case 2:
				startActivity(new Intent(this, Stroke.class));
				break;
			case 3:
				startActivity(new Intent(this, Choking.class));
				break;
			case 4:
				startActivity(new Intent(this, SpineNeckHeadInjury.class));
				break;	
			case 5:
				startActivity(new Intent(this, SevereBleeding.class));
				break;	
		}	
	}
}
