package com.comp489.sos;

/*
 * Developers: Tibin John, Mesfin Mekonnen, Simon Haile
 * SOS is an Android application that aims to minimize response time
 * during emergency situations. During emergencies of any nature, 
 * timely response can be hindered due to panic, dis-orientation,
 * unawareness, or mis-information. SOS aims to minimize this response
 * time by giving its user an organized, comprehensive and step-by-step 
 * instruction on how to deal with emergencies.
 *   
 *  Allows for the user to store and easily retrieve health insurance 
 *  information. In situations where the user is unable to access 
 *  and/or remember his/her health insurance information,
 *  SOS will allow for quick access from the home screen.
 *  In the case of distress, the user also has the option of sending
 *  out a text message to a user-identified emergency contact list
 *  or directly calling 911 from the home screen of the application.

 */


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
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends Activity implements OnClickListener{
	Button medical;
	Button naturalDisasters;
	Button emergencyContact;
	Button fire;
	Button sendAlert;
	Button healthInfoE;
	Button healthInfoV;
	Button call911;
	String writeName;
	String sosContacts;
	String[] content;
	Resources res;
	FileInputStream in;
	File file;
	String message;
	String phoneNumber,collect, loadFile;
	BroadcastReceiver sentReceiver, deliveredReceiver, receiver;
	final String SENT = "SMS_SENT";
	final String DELIVERED = "SMS_DELIVERED";
	final String text = "Automated response has been sent";
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
		
		call911 = (Button) findViewById(R.id.call911);
		call911.setOnClickListener(this);
		
		res = this.getResources();
		content = null;
		
		
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
		
		//need phone listener to tell when call is done and return here
		PhoneCallListener phone = new PhoneCallListener();
		
		//similar to smsManager mechanics - instantiating manually with default from Context
		//TelephonyManager telMan = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		//telMan.listen(phone, PhoneStateListener.LISTEN_CALL_STATE);
	}

	//adapted from http://www.mkyong.com/android/how-to-make-a-phone-call-in-android/
	//only implementing checks for calling from this app, not receiving calls
	private class PhoneCallListener extends PhoneStateListener{
		private boolean calling =  false;
		public void onCallStateChanged(int state, String number)
		{
			if(state ==TelephonyManager.CALL_STATE_OFFHOOK)
			{
				calling = true;
			}
			
			//if idle, restart SOS app and end call
			if(state == TelephonyManager.CALL_STATE_IDLE)
			{
				if(calling)
				{
					//get intent representing sos application
					Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//clears top of stack 
					//-->new instance isn't created; already running instance of app is continued
					startActivity(i);
					calling = false;
				}
			}
		}
			
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	
	@Override
	public void onClick(View view){
		switch(view.getId())
		{
		
		//adapted from http://stackoverflow.com/questions/15842328/android-intent-action-call-uri
		case R.id.call911:
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:911")); //uniform resource identifier static method
			startActivity(callIntent); //starts built-in activity that calls number in set data
			break;
		
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
				case R.id.Fire:
					startActivity(new Intent(this, Fire.class));
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
					if(in != null)
						in.close();
					else
						Toast.makeText(getBaseContext(), "Please Select Emergency Contacts First", Toast.LENGTH_SHORT ).show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(collect != null)
			{
				StringTokenizer tokenizer = new StringTokenizer(collect, ",");
				while(tokenizer.hasMoreTokens())
				{
					String str = tokenizer.nextToken();
					if(!numbers.contains(str))
						numbers.add(str);
				}
		
			final PendingIntent sentPendIntent = PendingIntent.getBroadcast(this,0, new Intent(SENT), 0);
			final PendingIntent delivered_pendIntent = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
			sentReceiver = new BroadcastReceiver(){

				@Override
				public void onReceive(Context arg0, Intent arg1) {
					switch(getResultCode()){
					case Activity.RESULT_OK:
						//Toast.makeText(getBaseContext(), "SMS sent", Toast.LENGTH_SHORT).show();
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
			
				//Toast.makeText(this, "" + numbers.size(),Toast.LENGTH_LONG).show();
				
					SmsManager sms = SmsManager.getDefault();
					for(String n : numbers)
					{
						if(n.toString().trim().length()>0 && message.toString().trim().length()>0)
						{
						sms.sendTextMessage(n.toString(),null, message.toString(),sentPendIntent, delivered_pendIntent);
						Toast.makeText(this, "Message Sent to " + n,Toast.LENGTH_SHORT).show();
						}
						else{
							Toast.makeText(this, "Either phone number or text is missing",Toast.LENGTH_SHORT).show();
						}	
					}
			}	
			
			break;

		}
	}
}
