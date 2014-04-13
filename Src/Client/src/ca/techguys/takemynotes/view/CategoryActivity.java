package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.google.gson.JsonSyntaxException;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import ca.techguys.takemynotes.beans.ApplicationData;
import ca.techguys.takemynotes.beans.CategoryItem;
import ca.techguys.takemynotes.beans.Note;
import ca.techguys.takemynotes.net.Parse;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ca.techguys.takemynotes.beans.AppConstants;
import ca.techguys.takemynotes.view.SubCategoryActivity;;
public class CategoryActivity extends Activity {

	private DialogActivity dialog;
	private String mobelNo;
	private ArrayList<CategoryItem> categoryList;
	private ArrayList<Note> noteList;
	private MyBaseAdapter myBaseAdapter;
	static String path=AppConstants.path;
	private Bitmap bitmap;
	private int categoryId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		setTitle("Note Category");
	
		getCategoryList();
		
		ListView listView=(ListView)findViewById(R.id.news_listview);
		myBaseAdapter=new MyBaseAdapter();
		listView.setAdapter(myBaseAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(categoryList==null){
					return;
				}
				CategoryItem cateItem = categoryList.get(position);
				categoryId = Integer.parseInt(cateItem.getIdCategory());
				Object obj=(Object)categoryList.get(position);
				new GetData(CategoryActivity.this,0).execute("");
				if(obj instanceof String){
					return;
				}
			}
		});
		
	}
	
	@SuppressWarnings("unchecked")
	private void getCategoryList()
	{
		categoryList = (ArrayList<CategoryItem>)getIntent().getSerializableExtra("tempModel") ;
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
			return categoryList.size();
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
			if(categoryList==null){
				return convertView;
			}
			final View view=convertView.inflate(CategoryActivity.this, R.layout.cate_item,null);
			
			Object obj=categoryList.get(position);
			ImageView news_item_image=(ImageView)view.findViewById(R.id.news_item_image);
			TextView news_item_title=(TextView)view.findViewById(R.id.news_item_title);
			TextView news_item_content=(TextView)view.findViewById(R.id.news_item_content);
			ImageView right_flag=(ImageView)view.findViewById(R.id.right_flag);
			if(obj instanceof CategoryItem){
				
				final CategoryItem cate=(CategoryItem)obj;
				
				String cateName=cate.getCategoryName().toString();

				if(bitmap!=null){
					news_item_image.setImageBitmap(bitmap);
				}else{
					if(cateName.contains("ICT")){
						news_item_image.setImageResource(R.drawable.computer);
					}else if(cateName.contains("Business")){
						news_item_image.setImageResource(R.drawable.business);
					}else if(cateName.contains("Math")){
						news_item_image.setImageResource(R.drawable.math);
					}
				}
				news_item_title.setText(cate.getCategoryName());
				
				//news_item_content.setText(cate.getTitle());
			}else{
				//view.setBackgroundResource(R.drawable.grey_box);
				view.setEnabled(false);
				news_item_title.setText(obj.toString());
				LayoutParams params = news_item_title.getLayoutParams();
				params.width=LayoutParams.FILL_PARENT;
				params.height=25;
				news_item_title.setLayoutParams(params);
				news_item_title.setGravity(Gravity.CENTER_VERTICAL);
				news_item_title.setTextSize(15);
			//	news_item_title.setTextColor(R.color.fav_text_item);
				news_item_image.setVisibility(View.GONE);
				news_item_content.setVisibility(View.GONE);
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Intent myIntent = new Intent();  
            myIntent = new Intent(CategoryActivity.this, SelectRoleActivity.class);  
            startActivity(myIntent);  
            this.finish();  

			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	

	private class GetData extends AsyncTask<String, String, String> {
		private Context mContext;
		private int mType;

		private GetData(Context context, int type) {
			this.mContext = context;
			this.mType = type;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			if (mType == 0) {
				if (null != dialog && !dialog.isShowing()) {
					dialog.show();
				}
			}
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String result = null;
			TakeMyNotesRequest request = new TakeMyNotesRequest(CategoryActivity.this);
			
			try {
				result = request.getSubcategory(categoryId, 1, 30, "no");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if (null != dialog && dialog.isShowing()) {
				dialog.dismiss();
			}
			
			if (result == null || result.equals("")) {
				handler.sendEmptyMessage(3);
			} else {

				noteList = new ArrayList<Note>();
				try {
					noteList = new Parse().GetNotesByCategory(result);
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
				}
				if (noteList != null) {
					Intent intent = new Intent(CategoryActivity.this,SubCategoryActivity.class);
					intent.putExtra("noteLists", (Serializable)noteList);
					
					startActivity(intent);
					finish();
				} else {
					handler.sendEmptyMessage(1);
				}
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				new GetData(CategoryActivity.this, 1).execute("");
				break;
			default:
				break;
			}
		}
	};

	private class CategoryAdapter extends BaseAdapter {
		private Context mContext;

		public CategoryAdapter(Context convert) {
			this.mContext = convert;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 1;// modellist.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return convertView;
		}

		private class ViewHolder {
			TextView tvName;
			TextView tvMessage;
		}
	};
	}
}
