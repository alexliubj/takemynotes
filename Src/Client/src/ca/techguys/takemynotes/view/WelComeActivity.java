package ca.techguys.takemynotes.view;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
/**
 * Welcome
 * @author Alex.Liu
 *
 */
public class WelComeActivity extends Activity {
 
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				startActivity(new Intent(WelComeActivity.this, MainActivity.class));
				WelComeActivity.this.finish();
				break;

			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		Message msg=new Message();
		msg.what=1;
		handler.sendMessageDelayed(msg, 500);//test
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK ) { 
			handler.removeMessages(1);
       	 	openConfirmDialog();
           return false; 
       } 
		
		
		return super.onKeyDown(keyCode, event);
	}

	private void openConfirmDialog() {
		final Builder builder=new AlertDialog.Builder(this);
		builder.setTitle(R.string.quit);
		builder.setIcon(R.drawable.icon);
		builder.setMessage(R.string.exit);
	
		builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				builder.create().dismiss();
				startActivity(new Intent(WelComeActivity.this, MainActivity.class));
				WelComeActivity.this.finish();
			}
		});

		builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				builder.create().dismiss();
				WelComeActivity.this.finish();
			}
		});
		builder.show();
	}
}
