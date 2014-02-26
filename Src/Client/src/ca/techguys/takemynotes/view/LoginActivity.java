package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.app.Dialog;
import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import ca.techguys.takemynotes.beans.ApplicationData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private Button btnGetCaptcha;
	private Button createBtn;
	private void init() {
		btnGetCaptcha = (Button) findViewById(R.id.btn_get_captcha);
		btnGetCaptcha.setOnClickListener(this);

		createBtn = (Button) findViewById(R.id.lgCreateBtn);
		createBtn.setOnClickListener(this);
		
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			ApplicationData.exit(LoginActivity.this);
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_get_captcha:
			{
				btnGetCaptcha.setBackgroundResource(R.drawable.loginbtnpressed);
				startActivity(new Intent(LoginActivity.this,  SelectRoleActivity.class));
				LoginActivity.this.finish();
				
			}
			break;
			case R.id.lgCreateBtn:
			{
				startActivity(new Intent(LoginActivity.this,  RegisterActivity.class));
				LoginActivity.this.finish();
				
			}
			break;
			
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		setTitle("Login");
		
		//ActivityManager.getActivityManage().addActivity(SignInActivity.this);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
		switch (item.getItemId()) {
	    case R.id.Account_Management:
	    	startActivity(new Intent(LoginActivity.this,  UserPanelActivity.class));
			LoginActivity.this.finish();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
		
	}
	

		
}
