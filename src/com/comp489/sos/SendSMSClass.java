package com.comp489.sos;



import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SendSMSClass  {
	
	BroadcastReceiver sentReceiver, deliveredReceiver;
	public static final String SENT = "SMS_SENT";
	public static final String DELIVERED = "SMS_DELIVERED";
	Activity context;
	int id;
	String message;
	final PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, new Intent(SENT), 0);
	
	//(not fired by emulator)
	final PendingIntent delivPI = PendingIntent.getBroadcast(context, 0, new Intent(DELIVERED), 0);
	public SendSMSClass(Context c, String m, int buttonID)
	{
		context = (Activity) c;
		message = m;
		id = buttonID;
	}
	sentReceiver = new BroadcastReceiver()
	{

		@Override
		public void onReceive(Context arg0, Intent arg1) 
		{
			// TODO Auto-generated method stub
			
			switch (getResultCode())
			{
				case Activity.RESULT_OK:
					Toast.makeText(context, "SMS sent", Toast.LENGTH_LONG).show();
					break;
				
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(context, "Generic failure", Toast.LENGTH_LONG).show();
					break;
				
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(context, "NO SERVICE", Toast.LENGTH_LONG).show();
					break;
					
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(context, "Null PDU", Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(context, "Radio off", Toast.LENGTH_LONG).show();
					break;
				
			}
		}
			
	};
	
	deliveredReceiver = new BroadcastReceiver()
	{

		@Override
		public void onReceive(Context arg0, Intent arg1) 
		{
			// TODO Auto-generated method stub
			
			switch (getResultCode()) //public method of BroadcastReceiver returning int
			{
				case Activity.RESULT_OK:
					Toast.makeText(context, "SMS delivered sucessfully", Toast.LENGTH_LONG).show();
					break;
					
				case Activity.RESULT_CANCELED:
					Toast.makeText(context, "Failure - SMS not delivered", Toast.LENGTH_LONG).show();
					break;
				
			}
		}
			
	};
	
	//registers broadcast receivers that filter for appropriate intents
	//to be able to receive broadcasted intents
	context.registerReceiver(sentReceiver, new IntentFilter(SENT));
	context.registerReceiver(deliveredReceiver, new IntentFilter(DELIVERED));
	
	Button sendBtn = (Button) context.findViewById(id);
	sendBtn.setOnClickListener(new Button.OnClickListener()
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//static method of SmsManager to get instance
			SmsManager sms = SmsManager.getDefault();
		
			//null -> short message service center (SMSC) default
			//broadcasts/invokes pendingintents when sent and delivered
			//sms.sendTextMessage(destinationAddress, scAddress, text, sentIntent, deliveryIntent);
			//throws IllegalArgumentException for empty number or text
			
			//sms.sendTextMessage(number, null, message, sentPI, delivPI);
		}
	});

}
}
