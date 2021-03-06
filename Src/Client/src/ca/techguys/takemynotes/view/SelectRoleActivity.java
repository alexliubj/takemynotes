package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.io.Serializable;
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
import ca.techguys.takemynotes.beans.ApplicationData;
import ca.techguys.takemynotes.beans.CategoryItem;
import ca.techguys.takemynotes.beans.Note;
import ca.techguys.takemynotes.beans.UniversalModel;
import ca.techguys.takemynotes.net.Parse;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;

public class SelectRoleActivity extends Activity implements OnClickListener {

	private String email;
	
	private Button buttonBuy;
	private Button buttonSell;
	private Button userInformaionButton;
	private CategoryItem item;
	private DialogActivity dialog;
	private ArrayList<CategoryItem> tempModel;
	private ArrayList<Note> tempNote;
	private void init() {
		buttonBuy = (Button) findViewById(R.id.srBuyBtn);
		buttonBuy.setOnClickListener(this);
		
		buttonSell = (Button) findViewById(R.id.srSellBtn);
		buttonSell.setOnClickListener(this);
		
		userInformaionButton = (Button) findViewById(R.id.srUserInfo);
		userInformaionButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.srBuyBtn:
			{
				buttonBuy.setBackgroundResource(R.drawable.buy);
				ShowMyDialog(1, null);
				handler.sendEmptyMessage(0);
			}
			break;
			
			case R.id.srSellBtn:
			{
				buttonSell.setBackgroundResource(R.drawable.sell);
				startActivity(new Intent(SelectRoleActivity.this,  SellNotesActivity.class));
				SelectRoleActivity.this.finish();
			}
			
			break;
			
			case R.id.srUserInfo:
			{
				ShowMyDialog(1, null);
				handler.sendEmptyMessage(2);
				
				
			}
		}
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			//get category
			case 0:
				Thread thread = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						try {
							result = request.getCategory();
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

							tempModel = new ArrayList<CategoryItem>();
							try {
								tempModel = new Parse().GetCategory(result);
								ApplicationData.SetCategoryList(tempModel);
							} catch (JsonSyntaxException e) {
								
								e.printStackTrace();
							}
							
							if (tempModel != null) {
								
								dialog.cancel();
								Intent intent = new Intent(SelectRoleActivity.this,
										CategoryActivity.class);
								intent.putExtra("tempModel", (Serializable)tempModel);
							
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
			//get user fav list
			case 2:
				Thread thread2 = new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						try {
							result = request.getFavList(ApplicationData.GetUserInforamtion().getIdUsers());
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

							tempNote = new ArrayList<Note>();
							try {
								tempNote = new Parse().GetNotesByCategory(result);
							} catch (JsonSyntaxException e) {
								e.printStackTrace();
							}
							if (tempNote != null) {
								dialog.cancel();
								
								Intent intent = new Intent(SelectRoleActivity.this,
										UserPanelActivity.class);
								intent.putExtra("notelists", (Serializable)tempNote);
							
								startActivity(intent);
								finish();
							} else {
								handler.sendEmptyMessage(1);
							}
						}
					}
				};
				thread2.start();
				
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_role);
		
		
		setTitle("Select Your Action");
		init();
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_role, menu);
		return true;
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

}
