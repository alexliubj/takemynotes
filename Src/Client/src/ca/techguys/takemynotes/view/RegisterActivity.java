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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
							UserInfo aUser = new UserInfo();
					        final EditText email = (EditText) findViewById(R.id.regEmailEdt);
					        aUser.setEmail(email.getText().toString());
					        final EditText regUserNameEdt = (EditText) findViewById(R.id.regUserNameEdt);
					        final EditText regConPasswordEdt = (EditText) findViewById(R.id.regConPasswordEdt);
					        aUser.setName(regUserNameEdt.getText().toString());
					        aUser.setPassword(regConPasswordEdt.getText().toString());
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
//								Intent intent = new Intent(SelectRoleActivity.this,
//										CategoryActivity.class);
//								intent.putExtra("tempModel", (Serializable)tempModel);
//								startActivity(intent);
//								finish();
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
				signupBtn.setBackgroundResource(R.drawable.registerbgpress);
				
				ShowMyDialog(1, null);
				handler.sendEmptyMessage(0);
				
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
