package com.comp489.sos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeScreen extends Activity implements OnClickListener{
	Button medical;
	Button naturalDisasters;
	Button emergencyContact;
	Button fire;
	Button sendAlert;
	Button healthInfoE;
	Button healthInfoV;
	String writeName;
	String sosContacts;
	Resources res;
	FileInputStream in;
	File file;
	String message;
	String phoneNumber,collect, loadFile;
	BroadcastReceiver sentReceiver, deliveredReceiver, receiver;
	String SENT = "SMS_SENT";
	String DELIVERED = "SMS_DELIVERED";
	String text = "Automated response has been sent";
	ArrayList<String> numbers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		numbers=new ArrayList<String>();
		naturalDisasters = (Button) findViewById(R.id.Natural_Disasters);
		naturalDisasters.setOnClickListener(this);
		
		loadFile = getResources().getString(R.string.sosContacts);

		healthInfoE = (Button) findViewById(R.id.healthInfoEnter);
		healthInfoE.setOnClickListener(this);

		healthInfoV = (Button) findViewById(R.id.healthInfoView);
		healthInfoV.setOnClickListener(this);
		
		emergencyContact = (Button) findViewById(R.id.emergencyContact);
		emergencyContact.setOnClickListener(this);
		
		res = this.getResources();
		
		
		//medical = (Button) findViewById(R.id.)
		

		medical = (Button) findViewById(R.id.medical);
		medical.setOnClickListener(this);

		emergencyContact = (Button) findViewById(R.id.emergencyContact);
		emergencyContact.setOnClickListener(this);

		fire = (Button) findViewById(R.id.Fire);
		fire.setOnClickListener(this);

		sendAlert = (Button) findViewById(R.id.sendAlert);
		sendAlert.setOnClickListener(this);

		res = this.getResources();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}
	
	public void onClick(View view){
		switch(view.getId())
		{
			case R.id.Natural_Disasters:
			Intent i2 = new Intent(this, NaturalDisasters.class);
			startActivity(i2);
			break;
			
			case R.id.medical:
				startActivity(new Intent(this,MedicalActivity.class));
				break;	
			case R.id.healthInfoEnter:	
				startActivity(new Intent(this, EnterHealthInsurance.class));
				break;		

				case R.id.healthInfoView:
					startActivity(new Intent(this, ViewHealthInsurance.class));
					break;
					
				case R.id.emergencyContact:
					startActivity(new Intent(this, DisplayContacts.class));
					break;
			
			case R.id.sendAlert:
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
			StringTokenizer tokenizer = new StringTokenizer(collect, ",");
			while(tokenizer.hasMoreTokens())
			{
				numbers.add(tokenizer.nextToken());
			}

			final PendingIntent sentPendIntent = PendingIntent.getBroadcast(this,0, new Intent(SENT), 0);
			final PendingIntent delivered_pendIntent = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
			sentReceiver = new BroadcastReceiver(){

				@Override
				public void onReceive(Context arg0, Intent arg1) {
					switch(getResultCode()){
					case Activity.RESULT_OK:
						Toast.makeText(getBaseContext(), "SMS sent", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
						Toast.makeText(getBaseContext(), "Generic failure", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_NO_SERVICE:
						Toast.makeText(getBaseContext(), "No service", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_NULL_PDU:
						Toast.makeText(getBaseContext(), "Null PDU", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_RADIO_OFF:
						Toast.makeText(getBaseContext(), "Radio off",Toast.LENGTH_SHORT).show();
						break;
					}
				}
			};
			deliveredReceiver = new BroadcastReceiver() {

				@Override
				public void onReceive(Context context, Intent intent) {
					switch(getResultCode()){
					case Activity.RESULT_OK:
						Toast.makeText(getBaseContext(), "SMS succesfully delivered", Toast.LENGTH_SHORT).show();
						break;
					case Activity.RESULT_CANCELED:
						Toast.makeText(getBaseContext(), "Failure-SMS not delivered", Toast.LENGTH_SHORT).show();
						break;
					}
				}
			};
			registerReceiver(sentReceiver, new IntentFilter(SENT));
			registerReceiver(deliveredReceiver, new IntentFilter(DELIVERED));
			message = res.getString(R.string.sosMessage);
			for(int i=0;i<numbers.size();i++){
				if(numbers.get(i).toString().trim().length()>0 && message.toString().trim().length()>0){
					SmsManager sms = SmsManager.getDefault();
					for(String n : numbers)
					{
						sms.sendTextMessage(n.toString(),null, message.toString(),sentPendIntent, delivered_pendIntent);
					}
					Toast.makeText(this, "Message Sent",Toast.LENGTH_SHORT).show();
				}
				else{
					Toast.makeText(this, "Either phone number or text is missing",Toast.LENGTH_SHORT).show();
				}	
			}	
			break;

		}
	}
}
