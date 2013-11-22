package com.comp489.sos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Fire extends Activity implements OnItemClickListener {
	// A: organic solid (cloth, paper, wood)
	// B: liquid (Fuels, Grease, Thinners, Melting Plastic)
	// C: Electric
	// D: Flammable Metals (magnesium, aluminum, titanium)
	// https://hwb.wales.gov.uk/cms/hwbcontent/Shared%20Documents/vtc/ngfl/science/103_new/asc1/firetype.htm
	ListView choicelist;
	String[] choices = {"A: Cloth, Paper, Wood", "B: Oil, Gasoline, Plastic", "C: Electric", "D: Magnesium, Aluminum", "K: Cooking Oil, Grease"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fire);
		choicelist = (ListView) findViewById(R.id.listView1);
		//adapter sends row data to list view, in android specified format (TextView)
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,choices);

		choicelist.setAdapter(adapter);
		//this class implements onItemClickListener
		choicelist.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fire, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int i, long l) {
		// TODO Auto-generated method stub
		switch (i)
		{
		case 0:
			Intent i1 = new Intent(this,FireTypeA.class);
			startActivity(i1);
			break;
		case 1:
			Intent i2 = new Intent(this,FireTypeB.class);
			startActivity(i2);
			break;
		case 2:
			Intent i3 = new Intent(this,FireTypeC.class);
			startActivity(i3);
			break;
		case 3:
			Intent i4 = new Intent(this,FireTypeD.class);
			startActivity(i4);
			break;
		}

	}
}
