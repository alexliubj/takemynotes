package ca.techguys.takemynotes.view;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import com.google.gson.JsonSyntaxException;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ca.techguys.takemynotes.beans.ResultModel;
import ca.techguys.takemynotes.beans.CategoryItem;
import ca.techguys.takemynotes.beans.UniversalModel;
import ca.techguys.takemynotes.beans.UserInfo;
import ca.techguys.takemynotes.net.Parse;
import ca.techguys.takemynotes.beans.CommonModel;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;

public class RegisterActivity extends Activity implements OnClickListener {

	
	private Button buttonBuy;
	private Button buttonSell;
	private CategoryItem item;
	private DialogActivity dialog;
	private ResultModel common;
	
	private EditText userNameEdt;
	private EditText emailEdt ;
	private EditText pwdEdt ;
	private EditText conPwdEdt ;
    
	private TextView warningTv;
	
	private String userName;
	private String email;
	private String pwd;
	private String conPwd;
	
	private UserInfo aUser;
	
	private void ShowMyDialog(int type, String str) {
		if (type == 1) {
			dialog = new DialogActivity(this, 1);
			dialog.getBtnCancel().setOnClickListener(this);
		} else {
			dialog = new DialogActivity(this, 2);
			dialog.setShowMessage(str);
			dialog.getBtnSure().setOnClickListener(this);
		}
		dialog.show();
	}
	
	private Button signupBtn;
	
	private void init() {
		
		userNameEdt = (EditText) findViewById(R.id.regUserNameEdt);
		emailEdt = (EditText) findViewById(R.id.regEmailEdt);
	    pwdEdt = (EditText) findViewById(R.id.regPasswordEdt);
	    conPwdEdt = (EditText) findViewById(R.id.regConPasswordEdt);
		
	    warningTv=(TextView) findViewById(R.id.regWarningTv);
	    
		signupBtn = (Button) findViewById(R.id.regRegisterBtn);
		signupBtn.setOnClickListener((OnClickListener) this);
		
	}
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				Thread thread = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						try {
							
							result = request.getRegister(aUser);
							
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
								common = new Parse().ResultParse(result);
							} catch (JsonSyntaxException e) {
							
								e.printStackTrace();
							}
							
							if (common != null) {
								
								dialog.cancel();
								if(common.getResult().equals("succ")) // successful
								{
									handler.sendEmptyMessage(1);
								}
								Intent intent = new Intent(RegisterActivity.this,
										SelectRoleActivity.class);
								intent.putExtra("email", aUser.getEmail().toString());
								startActivity(intent);
								finish();
							} else {
								handler.sendEmptyMessage(1);
							}
						}
					}
				};
				thread.start();
				break;
			case 1:
				dialog.cancel();
				
				break;
			case 3:
				dialog.cancel();
				
				break;
			default:
				break;
			}
		}

	};
	
	
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.regRegisterBtn:
			{
				userName="";
				email="";
				pwd="";
				conPwd="";
				
				warningTv.setText("");
				signupBtn.setBackgroundResource(R.drawable.registerbgpress);
				
				
				userName=userNameEdt.getText().toString();
				email=emailEdt.getText().toString();
				pwd=pwdEdt.getText().toString();
				conPwd=conPwdEdt.getText().toString();

				//check all the field has been entered
				if(validNotEmpty()==true){
					//check if email is valid
					if(validEmail(email)==true){
						//check if pwd is match
						if(pwdMatch(pwd, conPwd)==true){
							warningTv.setTextColor(Color.GREEN);
							warningTv.setText("All parameters are good to go");
							//start to assign value to user
							aUser.setName(userName);
//					        aUser.setEmail(email);
//					        aUser.setPassword(pwd);
					        
							//ShowMyDialog(1, "cool man");
							//handler.sendEmptyMessage(0);
							
						}else{//pwd doesn't match
							warningTv.setText("Your password doesn't match.");
						}
						
					}else{//email wasn't valid
						warningTv.setText("Please check your email address.");
					}
					
				}else{//has empty parameters
					warningTv.setText("Please enter all the parameters.");
				}
				
				break;
			}
			
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		setTitle("Register");
		init();
		warningTv.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	
	public boolean validEmail(String email){
		boolean valid=true;
		
		valid=android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
		
		return valid;
	}
	
	public boolean validNotEmpty(){
		boolean valid=true;
		
		if(userName.length()==0){
			valid=false;
		}
		if(email.length()==0){
			valid=false;
		}
		if(pwd.length()==0){
			valid=false;
		}
		if(conPwd.length()==0){
			valid=false;
		}
		
		return valid;
	}
	
	public boolean pwdMatch(String pwd, String conPwd){
		boolean valid=false;
		System.out.println(pwd+" "+conPwd);
		if(pwd.matches(conPwd)){
			valid=true;
		}
		System.out.println(valid);
		return valid;
	}
	
	

}
