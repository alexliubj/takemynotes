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
import ca.techguys.takemynotes.beans.ApplicationData;
import ca.techguys.takemynotes.beans.CategoryItem;
import ca.techguys.takemynotes.beans.Note;
import ca.techguys.takemynotes.beans.ResultModel;
import ca.techguys.takemynotes.beans.StoreUserInfo;
import ca.techguys.takemynotes.beans.UserInfo;
import ca.techguys.takemynotes.net.Parse;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;



public class PostCommentActivity extends Activity{

	private DialogActivity dialog;
	
	private String userId;
	private String comment;
	private String noteId;
	private EditText commentEdt;
	private TextView testTv;
	private TextView warningTv;
	private Button postBtn;
	
	private ResultModel resultModel;
	
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			//post comment for note
			case 0:
				Thread thread = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						
						try {
							comment=commentEdt.getText().toString();
							result = request.postComment(comment, noteId, userId);
							
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
							System.out.println(result.toString());
//							showAlert();
							
						}
					}
				};
				thread.start();
				break;

				
			//post sell note ad
			case 1:
				Thread thread1 = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						
						postBtn.setBackgroundResource(R.drawable.registerbgpress);
						
						//comment=commentEdt.getText().toString();
						
						try {
							result = request.postComment(comment, noteId, userId);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (result == null || result.equals("")) {
							handler.sendEmptyMessage(3);
						}
					}
				};
				thread1.start();
				break;
			//dialog for error message
			case 3:
				dialog.cancel();
				
				break;
			default:
				break;
			}
		}
	};
	
	
	public void showAlert(){

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Message");
		alertDialog.setMessage("Your comment has been posted");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
//	        Intent intent=new Intent(PostCommentActivity.this, NoteDetailsActivity.class);
//	 	   	startActivity(intent);
		}
		});
		alertDialog.setIcon(R.drawable.icon);
		alertDialog.show();
		
 	   	
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_postcomments);
		
		StoreUserInfo suin = ApplicationData.GetUserInforamtion();
		userId = suin.getIdUsers();
	 
		commentEdt=(EditText) findViewById(R.id.pcCommentEdt);
		testTv=(TextView) findViewById(R.id.pcTestTv);
		warningTv=(TextView) findViewById(R.id.pcWarningTv);
		
		noteId=getIntent().getStringExtra("noteId").toString();
		
		testTv.setText("userId: "+userId+" noteId: "+noteId);
		postBtn=(Button) findViewById(R.id.pcPostBtn);
		postBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				warningTv.setText("");
				comment=commentEdt.getText().toString();
				if(comment.length()!=0){
					//post comment 
					handler.sendEmptyMessage(0);
					showAlert();
					
				}else{
					warningTv.setTextColor(Color.RED);
					warningTv.setText("Please enter your comment.");
				}
			}
		});
		
	}
}
