package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import com.google.gson.JsonSyntaxException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import ca.techguys.takemynotes.beans.*;
import ca.techguys.takemynotes.net.Parse;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.content.Context;
import android.content.DialogInterface;
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
private Button buttonBuy;
private Button buttonSell;
private CategoryItem item;
private DialogActivity dialog;
private int userid;

private ResultModel tempModel;
private StoreUserInfo storeInfoModel;

private AlertDialog alertDialog;

private void init() {
btnGetCaptcha = (Button) findViewById(R.id.lgLoginBtn);
btnGetCaptcha.setOnClickListener(this);
createBtn = (Button) findViewById(R.id.lgCreateBtn);
createBtn.setOnClickListener(this);

alertDialog = new AlertDialog.Builder(this).create();
}

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
case R.id.lgLoginBtn:
{
btnGetCaptcha.setBackgroundResource(R.drawable.loginbtnpressed);

handler.sendEmptyMessage(0);

// startActivity(new Intent(LoginActivity.this, SelectRoleActivity.class));
// LoginActivity.this.finish();

}
break;
case R.id.lgCreateBtn:
{
startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
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

if(getIntent().hasExtra("regMsg")){
	showAlert();
}

init();



}


@SuppressWarnings("deprecation")
public void showAlert(){

	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	alertDialog.setTitle("Message");
	alertDialog.setMessage("Please check you user name and password");
	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int which) {
		
	}
	});
	alertDialog.setIcon(R.drawable.icon);
	alertDialog.show();
	
	   	
}



private Handler handler = new Handler() {

@Override
public void handleMessage(Message msg) {
// TODO Auto-generated method stub
super.handleMessage(msg);
switch (msg.what) {
case 0:
ShowMyDialog(1, null);
Thread thread = new Thread() {

@Override
public void run() {
// TODO Auto-generated method stub
super.run();
TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
String result = null;
try {
final EditText lgUserName = (EditText) findViewById(R.id.lgUserName);
String username = lgUserName.getText().toString();
final EditText lgPasswordEdt = (EditText) findViewById(R.id.lgPasswordEdt);
String password = lgPasswordEdt.getText().toString();
result = request.getLogin("",username,password);
} catch (IOException e) {
e.printStackTrace();
} catch (TimeoutException e) {
e.printStackTrace();
}
if (result == null || result.equals("")) {
handler.sendEmptyMessage(3);
} else {
tempModel = new ResultModel();
try {
tempModel = new Parse().ResultParse(result);
} catch (JsonSyntaxException e) {
e.printStackTrace();
}
if (!tempModel.getResult().equals("fail")) { // success
int userid2 = Integer.parseInt(tempModel.getResult());
userid = userid2;
handler.sendEmptyMessage(4);
// startActivity(new Intent(LoginActivity.this, SelectRoleActivity.class));
// LoginActivity.this.finish();
// dialog.cancel();
} else { //failed
	
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

case 4:
Thread thread2 = new Thread() {

@Override
public void run() {
super.run();
TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
String result = null;
try {
result = request.GetUserInfo(String.format("%d", userid));
} catch (IOException e) {
e.printStackTrace();
} catch (TimeoutException e) {
e.printStackTrace();
}
if (result == null || result.equals("")) {
handler.sendEmptyMessage(3);
} else {
storeInfoModel = new StoreUserInfo();
try {
storeInfoModel = new Parse().storeUserInfoParse(result);
} catch (JsonSyntaxException e) {
e.printStackTrace();
}

ApplicationData.SetUserInfor(storeInfoModel);

startActivity(new Intent(LoginActivity.this, SelectRoleActivity.class));
LoginActivity.this.finish();
dialog.cancel();
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
startActivity(new Intent(LoginActivity.this, UserPanelActivity.class));
LoginActivity.this.finish();
return true;
default:
return super.onOptionsItemSelected(item);
}
}
}