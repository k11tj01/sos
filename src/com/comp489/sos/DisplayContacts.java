package com.comp489.sos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class DisplayContacts extends Activity implements AdapterView.OnItemClickListener{

	ArrayList<String> phoneNumbers;
	ArrayList<String> emergencyNos;
	ArrayList<String> names;
	String[] content;
	Button done;
	String storeFile;
	String[] contentNo;
	ListView l;
	FileOutputStream out;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_contacts);
	
		l = (ListView) findViewById(R.id.listView);
		//adapter sends row data to list view, in android specified format (TextView)
		
		done = (Button) findViewById(R.id.done);
		
		storeFile = getResources().getString(R.string.sosContacts);
		content = null;
		
		done.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//write selcetions (added to arraylist) to file (overwrites)
				content = new String[emergencyNos.size()];
				int count = 0;
				for(String no : emergencyNos)
				{
					content[count] = no;
					count++;
				}
				
				//read in fields and write to file
				String csv = "";
				
				for(int j = 0; j < content.length;j++)
				{
					csv += (content[j] + ",");
				}
				
				try{
					out = openFileOutput(storeFile, Context.MODE_PRIVATE);
					out.write(csv.getBytes());
					//out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					Toast.makeText(getBaseContext(), "Emergency Contacts Stored", Toast.LENGTH_LONG).show();
				}
			
				finish();	
			}
		});
		
		phoneNumbers = new ArrayList<String>();
		names = new ArrayList<String>();
		emergencyNos = new ArrayList<String>();
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		AlertDialog alertD = alert.create();
		alertD.setTitle("Please select your emergency contacts");
		alertD.show();
		
		Uri uri = Contacts.CONTENT_URI;
		String[] projection = null; //columns of contacts database to grab
		String selection = null; //rows of contacts to grab
		String[] selectionArgs = null; //fills in question marks in above
		String sortOrder = null;
		
		ContentResolver cr = getContentResolver();
		//all but database/uri name null
		Cursor cur = cr.query(uri, projection, selection, selectionArgs, sortOrder);
		
		//cur.moveToFirst();
		
		//if contacts exist
		if(cur.getCount()>0)
		{
			//moves through rows of contacts db (Content Provider)
			while(cur.moveToNext())
			{
				//id of contact
				String id = cur.getString(cur.getColumnIndex(BaseColumns._ID));
				String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				
				//Toast.makeText(this, name, Toast.LENGTH_LONG).show();
				
				//check if name has a phone number
				if(Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) >0)
						{
							//new cursor - points to only the number associated with the id recorded
							Cursor cur2 = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
							
							//retrieves actual phone numbers
							while(cur2.moveToNext())
							{
								//only want name if it has mobile and only want mobile numbers for text
								if(Integer.parseInt(cur2.getString(cur2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE))) == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
								{
									phoneNumbers.add(cur2.getString(cur2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
									names.add(name);
								}
							}
							cur2.close();
						}
				
				
			}
		}
		String[] contentNames = new String[names.size()];
		contentNo = new String[phoneNumbers.size()];
		int count = 0;
		for(String number : phoneNumbers)
		{
			
			contentNo[count] = number;
			count++;
		}
		
		count = 0;
		for(String name : names)
		{
			
			contentNames[count] = name + " (" + contentNo[count] + ")";
			count++;
		}
		
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contentNames);
		l.setAdapter(adapter);
		
		
		//this class implements onItemClickListener
		l.setOnItemClickListener(this);

			
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_contacts, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int i, long l) {
		// TODO Auto-generated method stub
		
		emergencyNos.add(contentNo[i]);
		
	}

}
