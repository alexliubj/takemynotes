package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;

public class UserPanelActivity extends Activity {

	TextView registerDateTv;
	TextView loginDateTv;
	
	EditText userNameEdt;
	EditText emailEdt;
	EditText addressEdt;
	
	Button updateBtn;
	
	String regDate;
	String loginDate;
	String name;
	String email;
	String address;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_panel);
		
		setTitle("My profile");
		
		registerDateTv=(TextView) findViewById(R.id.upDateRegisterTv);
		loginDateTv=(TextView) findViewById(R.id.upLastLoginTv);
		
		userNameEdt=(EditText) findViewById(R.id.upNameEdt);
		emailEdt=(EditText) findViewById(R.id.upEmailEdt);
		addressEdt=(EditText) findViewById(R.id.upAddressEdt);
		
		registerDateTv.setText(regDate);
		loginDateTv.setText(loginDate);
		
		userNameEdt.setText(name);
		emailEdt.setText(email);
		addressEdt.setText(address);
		
		updateBtn=(Button) findViewById(R.id.upUpdateBtn);
		updateBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name=userNameEdt.getText().toString();
				email=emailEdt.getText().toString();
				address=addressEdt.getText().toString();
				
				TakeMyNotesRequest request=new TakeMyNotesRequest(getApplicationContext());
				
				String result = null;
				/*try {
			        
					result = request.getUserSet(userId, key, passWord, userName, userSex, userAddress);
					
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_panel, menu);
		return true;
	}

}
