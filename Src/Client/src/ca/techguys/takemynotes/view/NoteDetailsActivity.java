package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import com.google.gson.JsonSyntaxException;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import ca.techguys.takemynotes.beans.AppConstants;
import ca.techguys.takemynotes.beans.ApplicationData;
import ca.techguys.takemynotes.beans.Comment;
import ca.techguys.takemynotes.beans.Note;
import ca.techguys.takemynotes.beans.StoreUserInfo;
import ca.techguys.takemynotes.net.Parse;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;
import ca.techguys.takemynotes.view.SubCategoryActivity.MyBaseAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NoteDetailsActivity extends Activity implements OnClickListener {

	private String userId;
	
	private Note aNote;
	private TextView nameTv;
	private TextView descTv;
	private TextView contactTv;
	private TextView emailTv;
	private TextView phoneTv;
	private TextView priceTv;
	private Button commentBtn;
	private Button favBtn;
	private DialogActivity dialog;
	private MyBaseAdapter myBaseAdapter;
	private ArrayList<Comment> commentList;
	static String path=AppConstants.path;
	private int noteId;
	private Bitmap bitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_details);
		
		setTitle("Note detail");
		
		if(getIntent().hasExtra("userId")){
			userId=getIntent().getStringExtra("userId").toString();
		}
		
		nameTv=(TextView) findViewById(R.id.ndNoteNameTv);
		descTv=(TextView) findViewById(R.id.ndDescTv);
		contactTv=(TextView) findViewById(R.id.ndContactTv);
		emailTv=(TextView) findViewById(R.id.ndEmailTv);
		phoneTv=(TextView) findViewById(R.id.ndPhoneTv);
		priceTv=(TextView) findViewById(R.id.ndPriceTv);
		commentBtn=(Button) findViewById(R.id.ndCommentBtn);
		favBtn=(Button) findViewById(R.id.ndFavBtn);
		
		aNote = (Note)getIntent().getSerializableExtra("notedetails") ;
		getNoteList();

		ListView listView=(ListView)findViewById(R.id.note_comments);
		if(commentList!=null){
		myBaseAdapter=new MyBaseAdapter();
		listView.setAdapter(myBaseAdapter);
		}
		init();
		commentBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(NoteDetailsActivity.this, PostCommentActivity.class);
		   		
				intent.putExtra("userId", userId);
				intent.putExtra("noteId", aNote.getIdNotes().toString());
				
				startActivity(intent);
			}
		});

		favBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				ShowMyDialog(1,null);
				handler.sendEmptyMessage(0);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void getNoteList()
	{
		commentList = (ArrayList<Comment>)getIntent().getSerializableExtra("commentsList") ;
	}

	class MyBaseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return commentList.size();
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
			if(commentList==null){
				return convertView;
			}
			final View view=convertView.inflate(NoteDetailsActivity.this, R.layout.comment_listview,null);
			Object obj=commentList.get(position);
			ImageView news_item_image=(ImageView)view.findViewById(R.id.img);
			TextView news_item_title=(TextView)view.findViewById(R.id.info);
			if(obj instanceof Note){
				final Comment aComment=(Comment)obj;
				String cateName=aComment.getCommt();
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
				news_item_title.setText(aComment.getCommt().toString());
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
 	
	//get string value from intent of notes 
	private void init(){
		nameTv.setText(String.valueOf(aNote.getNoteName()));
		descTv.setText(String.valueOf(aNote.getDescription()));
		contactTv.setText(String.valueOf(aNote.getPubUserId()));
		emailTv.setText(String.valueOf(aNote.getPrice()));
		phoneTv.setText(String.valueOf(Float.valueOf(aNote.getPrice())));
		priceTv.setText(String.valueOf(Float.valueOf(aNote.getPrice())));
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note_details, menu);
		//Log.i("TEST", "====================== Menu creation called");
		MenuItem item = menu.findItem(R.id.favorite);
		item.setIcon(R.drawable.create);
	    item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

	        @Override
	        public boolean onMenuItemClick(MenuItem item) {
	        	
	            return true;
	        }
	    });
	    return true;
	}
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			//get all category 
			case 0:
				Thread thread = new Thread() {
					@Override
					public void run() {
						super.run();
						TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
						String result = null;
						try {
							result = request.addFav(aNote.getIdNotes(), ApplicationData.GetUserInforamtion().getIdUsers());
						} catch (IOException e) {
							e.printStackTrace();
						} catch (TimeoutException e) {
							e.printStackTrace();
						}
						if (result == null || result.equals("")) {
							handler.sendEmptyMessage(3);
						} else {
							try {
								//cateList = new Parse().
							} catch (JsonSyntaxException e) {
								e.printStackTrace();
							}
						}
					}
				};
				thread.start();
				
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
	public boolean onOptionsItemSelected(MenuItem item) {
	   // handle item selection
	   switch (item.getItemId()) {
	      case R.id.favorite:
	         // do s.th.
	         return true;
	      default:
	         return super.onOptionsItemSelected(item);
	   }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
