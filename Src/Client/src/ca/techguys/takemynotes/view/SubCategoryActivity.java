package ca.techguys.takemynotes.view;



import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.gson.JsonSyntaxException;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import ca.techguys.takemynotes.beans.AppConstants;
import ca.techguys.takemynotes.beans.CategoryItem;
import ca.techguys.takemynotes.beans.Comment;
import ca.techguys.takemynotes.beans.Note;
import ca.techguys.takemynotes.net.Parse;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;
import ca.techguys.takemynotes.view.CategoryActivity.MyBaseAdapter;
import ca.techguys.takemynotes.view.NoteDetailsActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class SubCategoryActivity extends Activity implements OnClickListener {
	
	private String userId;
	
	private DialogActivity dialog;
	private Intent intent;
	private String mobelNo;
	private ArrayList<Note> noteList;
	private MyBaseAdapter myBaseAdapter;
	static String path=AppConstants.path;
	private Bitmap bitmap;
	private ArrayList<Note> tempModel;
	private ArrayList<Comment> commentModel;
	private Note nextPageNote;
	private int noteId;
	
	private Button dateBtn;
	private Button priceBtn;
	
	private ArrayList<Note> dateNoteList;
	private ArrayList<Note> priceNoteList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_category);
		
		setTitle("Note List");
		
		if(getIntent().hasExtra("userId")){
			userId=getIntent().getStringExtra("userId").toString();
		}
		
		dateBtn=(Button) findViewById(R.id.scDateBtn);
		priceBtn=(Button) findViewById(R.id.scPriceBtn);
		

		getNoteList();
		
		dateBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dateNoteList=noteList;
			}
			
		});
		
		priceBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		ListView listView=(ListView)findViewById(R.id.news_listview);
		myBaseAdapter=new MyBaseAdapter();
		listView.setAdapter(myBaseAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(noteList==null){
					return;
				}
				Note noetItem = noteList.get(position);
				noteId = Integer.parseInt(noetItem.getIdNotes());
				Object obj=(Object)noteList.get(position);
				nextPageNote = new Note();
				nextPageNote = (Note)obj;
				ShowMyDialog(1, null);
				handler.sendEmptyMessage(0);
				if(obj instanceof String){
					return;
				}
			}
		});
		
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

	
	@SuppressWarnings("unchecked")
	private void getNoteList()
	{
		noteList = (ArrayList<Note>)getIntent().getSerializableExtra("noteLists") ;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}
	class MyBaseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return noteList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(noteList==null){
				return convertView;
			}
			final View view=convertView.inflate(SubCategoryActivity.this, R.layout.subcate_listview,null);
			Object obj=noteList.get(position);
			ImageView news_item_image=(ImageView)view.findViewById(R.id.img);
			TextView news_item_title=(TextView)view.findViewById(R.id.info);
			ImageView right_flag=(ImageView)view.findViewById(R.id.favImg);
			if(obj instanceof Note){
				
				final Note aNote=(Note)obj;
				String cateName=aNote.getNoteName();

//				if(bitmap!=null){
//					news_item_image.setImageBitmap(bitmap);
//				}else{
//					if(cateName.contains("ICT")){
//						news_item_image.setImageResource(R.drawable.computer);
//					}else if(cateName.contains("Business")){
//						news_item_image.setImageResource(R.drawable.business);
//					}else if(cateName.contains("Math")){
//						news_item_image.setImageResource(R.drawable.math);
//					}
//				}
				news_item_title.setText("Name: "+aNote.getNoteName()+"\n"+
						"Description: "+aNote.getDescription()+"\n"+
						"Price: $"+aNote.getPrice());
				
				//news_item_content.setText(cate.getTitle());
			}else{
			}
			return view;
		}
	}
	
 	public static Bitmap getPicByPath(String picName){
 			picName=picName.substring(picName.lastIndexOf("/")+1);
 			String filePath=path+picName;
 			Bitmap bitmap=BitmapFactory.decodeFile(filePath);
 			return bitmap;
 	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
//		switch (arg0.getId()) {
//		case R.id.srBuyBtn:
//		{
//			ShowMyDialog(1, null);
//			handler.sendEmptyMessage(0);
//		
//		}
//		break;
//		
//		case R.id.srSellBtn:
//		{
//			startActivity(new Intent(SelectRoleActivity.this,  SellNotesActivity.class));
//			SelectRoleActivity.this.finish();
//		}
//		break;
//	}
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			//get comment by note id
			case 0:
				Thread thread = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						try {
							result = request.getComments(String.format("%d", noteId));
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

							commentModel = new ArrayList<Comment>();
							try {
								commentModel = new Parse().GetListComments(result);
							} catch (JsonSyntaxException e) {
								
								e.printStackTrace();
							}
							if (commentModel != null) {
								
								dialog.cancel();
//								
							} else {
								handler.sendEmptyMessage(1);
							}
							
							Intent intent = new Intent(SubCategoryActivity.this,NoteDetailsActivity.class);
							if (commentModel != null) {
								intent.putExtra("commentsList", (Serializable)commentModel);
							}
							intent.putExtra("notedetails", (Serializable)nextPageNote);
							intent.putExtra("userId", userId);
							startActivity(intent);
							finish();
						}
					}
				};
				thread.start();
				break;
			case 1:
				dialog.cancel();
				break;
			case 2:
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
	

}
