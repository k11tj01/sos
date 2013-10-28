package com.comp489.sos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.*;
public class NaturalDisasters extends Activity implements AdapterView.OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_natural_disasters);
		ListView l;
		String[] choices = {"EARTH QUAKE", "FLOODS", "HURRICANES","WILD FIRES","TORNADOS","TSUNAMI"};
		l = (ListView) findViewById(R.id.listView1);
		//adapter sends row data to list view, in android specified format (TextView)
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,choices);

		l.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.natural_disasters, menu);
		return true;
	}
	public void earthqlistener(View view)
	{
		Intent intent = new Intent(NaturalDisasters.this, EarthQuakes.class);
		startActivity(intent);
	}
	public void wildflistener(View view)
	{
		Intent intent = new Intent(NaturalDisasters.this, WildFires.class);
		startActivity(intent);
	}
	public void tsunamilistener(View view)
	{
		Intent intent = new Intent(NaturalDisasters.this, Tsunami.class);
		startActivity(intent);
	}
	public void hurricanelistener(View view)
	{
		Intent intent = new Intent(NaturalDisasters.this, Hurricanes.class);
		startActivity(intent);
	}
	public void tornadolistener(View view)
	{
		Intent intent = new Intent(NaturalDisasters.this, Tornados.class);
		startActivity(intent);
	}
	public void floodslistener(View view)
	{
		Intent intent = new Intent(NaturalDisasters.this, EarthQuakes.class);
		startActivity(intent);
	}
}

