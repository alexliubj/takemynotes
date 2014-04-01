package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.beans.CategoryItem;
import ca.techguys.takemynotes.beans.Note;
import ca.techguys.takemynotes.net.Parse;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;

import com.google.gson.JsonSyntaxException;


public class SellNotesActivity extends Activity {

	private DialogActivity dialog;
	
	
	
	EditText nameEdt;
	EditText priceEdt;
	EditText descEdt;
	
	Spinner cateSpinner;

	Button postBtn;

	String name;
	String cateId;
	Float price;
	String desc;
	String userId;
	String status;
	
	Note myNote;
	
	String result; 
	ArrayList<CategoryItem> cateList;
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			//get all category 
			case 0:
				Thread thread = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						try {
							result = request.getCategory();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (result == null || result.equals("")) {
							handler.sendEmptyMessage(3);
						} else {

							try {
								cateList = new Parse().GetCategory(result);
							} catch (JsonSyntaxException e) {
								
								e.printStackTrace();
							}
							
						}
					}
				};
				thread.start();
				break;
			//post sell note ad
			case 1:
				Thread thread1 = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						
						postBtn.setBackgroundResource(R.drawable.registerbgpress);
						
						name=nameEdt.getText().toString();
						cateId=String.valueOf(cateSpinner.getSelectedItemId());
						price=Float.valueOf(priceEdt.getText().toString());
						desc=descEdt.getText().toString();
						userId="0";
						status="0";
						
						
						myNote.setNoteName(name);
						myNote.setCateId(cateId);
						myNote.setDescription(desc);
						myNote.setPrice(price.toString());
						myNote.setPubUserId(userId);
						myNote.setStatus(status);
						
						try {
							result = request.postNote(myNote);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (result == null || result.equals("")) {
							handler.sendEmptyMessage(3);
						}
					}
				};
				thread1.start();
				break;
			//dialog for error message
			case 3:
				dialog.cancel();
				
				break;
			default:
				break;
			}
		}

	};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sell_notes);
		
		setTitle("Sell note");
		
		
		postBtn=(Button) findViewById(R.id.lgCreateBtn);
		postBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				
			}
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sell_notes, menu);
		return true;
	}
	
	
	

}
