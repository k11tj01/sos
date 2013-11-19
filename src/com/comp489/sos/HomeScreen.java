package com.comp489.sos;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class HomeScreen extends Activity implements OnClickListener{
	ImageButton med;
	Button naturalDisasters;
	Button hi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		med = (ImageButton) findViewById(R.id.imageButtonMed);
		med.setOnClickListener(this);
		naturalDisasters = (Button) findViewById(R.id.Natural_Disasters);
		naturalDisasters.setOnClickListener(this);
		hi = (Button) findViewById(R.id.hi);
		
		hi.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		switch(view.getId())
		{
			case R.id.imageButtonMed:
				Intent i1 = new Intent(this, MedicalActivity.class);			
				startActivity(i1);
				break;
			case R.id.Natural_Disasters:
				Intent i2 = new Intent(this, NaturalDisasters.class);
				startActivity(i2);
		//	case R.id.Fire:
			//	Intent i3 = new Intent(this, FireActivity.class)
				break;
			case R.id.hi:
				startActivity(new Intent(this, EnterHealthInsurance.class));
		}
	}
	/*public void onClick2(View view){
		switch(view.getId())
		{
			case R.id.Natural_Disasters:
			Intent i2 = new Intent(this, NaturalDisasters.class);
			startActivity(i2);
			break;
		}
	}
*/
}
