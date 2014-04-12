package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import ca.techguys.takemynotes.beans.ApplicationData;
import ca.techguys.takemynotes.beans.StoreUserInfo;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;

public class UserPanelActivity extends Activity {

	TextView registerDateTv;
	TextView loginDateTv;
	EditText userNameEdt;
	EditText emailEdt;
	EditText addressEdt;
	Button updateBtn;
	String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_panel);
		
		StoreUserInfo suin = ApplicationData.GetUserInforamtion();
		setTitle("My profile");
		
		registerDateTv=(TextView) findViewById(R.id.upDateRegisterTv);
		loginDateTv=(TextView) findViewById(R.id.upLastLoginTv);
		registerDateTv.setVisibility(View.GONE);
		loginDateTv.setVisibility(View.GONE);
		userNameEdt=(EditText) findViewById(R.id.upNameEdt);
		emailEdt=(EditText) findViewById(R.id.upEmailEdt);
		addressEdt=(EditText) findViewById(R.id.upAddressEdt);
		updateBtn=(Button) findViewById(R.id.upUpdateBtn);
		userId = suin.getIdUsers();

//		registerDateTv.setText(suin.get);
//		loginDateTv.setText(loginDate);
		userNameEdt.setText(suin.getName());
		emailEdt.setText(suin.getEmail());
		addressEdt.setText(suin.getAddress());
		
		updateBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=userNameEdt.getText().toString();
				String email=emailEdt.getText().toString();
				String address=addressEdt.getText().toString();
				
				TakeMyNotesRequest request=new TakeMyNotesRequest(getApplicationContext());
				
				String result = null;
				/*try {
			        
					result = request.get(userId, key, passWord, userName, userSex, userAddress);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
			
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Intent myIntent = new Intent();  
            myIntent = new Intent(UserPanelActivity.this, SelectRoleActivity.class);  
            startActivity(myIntent);  
            this.finish();  

			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_panel, menu);
		return true;
	}

}
