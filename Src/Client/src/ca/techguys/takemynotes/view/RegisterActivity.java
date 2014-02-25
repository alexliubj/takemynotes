package ca.techguys.takemynotes.view;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegisterActivity extends Activity {

	private Button signupBtn;
	
	private void init() {
		signupBtn = (Button) findViewById(R.id.signupBtn);
		signupBtn.setOnClickListener((OnClickListener) this);
		
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_get_captcha:
			{
				signupBtn.setBackgroundResource(R.drawable.registerbgpress);
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	

}
