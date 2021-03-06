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
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import ca.techguys.takemynotes.beans.StoreUserInfo;
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
	
	private ResultModel tempModel;
	private StoreUserInfo storeInfoModel;
	
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
			//register new user
			case 0:
				Thread thread = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						try {
							UserInfo aUser =new UserInfo();
							
							aUser.setName(userName);
							aUser.setEmail(email);
							aUser.setPassword(pwd);
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
								//dialog.cancel();
								if(common.getResult().equals("succ")) // successful
								{
									//dialog.cancel();
									
									Intent intent = new Intent(RegisterActivity.this,
											LoginActivity.class);
									intent.putExtra("regMsg", "pass");
									startActivity(intent);
									finish();
									
								}
								
							} else {
								dialog.cancel();
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
			//get user'd id by user name in register
			case 4:
				//ShowMyDialog(1, null);
				Thread thread2 = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						try {
					        
							result = request.getLogin("",userName,pwd);
							
						} catch (IOException e) {
							e.printStackTrace();
						} catch (TimeoutException e) {
							e.printStackTrace();
						}
						if (result == null || result.equals("")) {
							dialog.cancel();
						} else {
							tempModel = new ResultModel();
							try {
								tempModel = new Parse().ResultParse(result);
							} catch (JsonSyntaxException e) {
								e.printStackTrace();
							}
							if (!tempModel.getResult().equals("fail")) { // success
								//dialog.cancel();
								System.out.println(tempModel.getResult().toString());
								
							} else { //failed
								
							}
							
						}
					}
				};
				thread2.start();
				break;
			default:
				break;
			}
		}

	};
	
	@SuppressWarnings("deprecation")
	public void showAlert(){

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Message");
		alertDialog.setMessage("You register successfully");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			
		}
		});
		alertDialog.setIcon(R.drawable.icon);
		alertDialog.show();
		
 	   	
	}
	
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
							
							ShowMyDialog(1, null);
							handler.sendEmptyMessage(0);
							
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
		//System.out.println(pwd+" "+conPwd);
		if(pwd.matches(conPwd)){
			valid=true;
		}
		//System.out.println(valid);
		return valid;
	}
	
	

}
