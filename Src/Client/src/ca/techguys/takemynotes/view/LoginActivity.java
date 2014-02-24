package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.app.Dialog;
import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private Button btnGetCaptcha;
	private Button loginBtn;
	private void init() {
		btnGetCaptcha = (Button) findViewById(R.id.btn_get_captcha);
		btnGetCaptcha.setOnClickListener(this);

		
		
		
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_get_captcha:
		{
			startActivity(new Intent(LoginActivity.this,  SelectRoleActivity.class));
			LoginActivity.this.finish();
		}
			break;
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//ActivityManager.getActivityManage().addActivity(SignInActivity.this);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	

		
}
