package com.comp489.sos;



import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMSClass extends Activity {
	EditText phoneNumber, message;
	String message2 = "I'm driving right now, I will call you soon";
	BroadcastReceiver sentReceiver, deliveredReceiver, receiver;
	String SENT = "SMS_SENT";
	String DELIVERED = "SMS_DELIVERED";
	String text = "Automated response has been sent";
	String[] numbers;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		Button sendBtn = (Button) this.findViewById(R.id.send_button);
		sendBtn.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View view){
				phoneNumber = (EditText) findViewById(R.id.recvr_no);
				message = (EditText)findViewById(R.id.txt_msg);
				if(phoneNumber.getText().toString().trim().length()>0 && message.getText().toString().trim().length()>0){
					SmsManager sms = SmsManager.getDefault();
					for(String n : numbers){
					sms.sendTextMessage(phoneNumber.getText().toString(),null, message.getText().toString(),sentPendIntent, delivered_pendIntent);
					}
				}
				else{
					Toast.makeText(SendSMSClass.this, "Either phone number or text is missing",Toast.LENGTH_SHORT).show();
				}
			//	Intent intent = new Intent(SendSMSAppActivity.this, ReceiveSMSApp.class);
				//startActivity(intent);
			}
		});
//		Button cancelBtn = (Button) this.findViewById(R.id.cancel_button);
//		cancelBtn.setOnClickListener(new Button.OnClickListener(){
//			@Override
//			public void onClick(View view){
//				phoneNumber.setText("");
//				message.setText("");
//			}
//		});
	
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
