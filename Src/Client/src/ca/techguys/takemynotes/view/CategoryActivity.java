package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import ca.techguys.takemynotes.beans.ApplicationData;
import ca.techguys.takemynotes.beans.CategoryItem;
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
public class CategoryActivity extends Activity {

	private DialogActivity dialog;
	private Intent intent;
	private String mobelNo;
	private ArrayList<CategoryItem> categoryList;
	private MyBaseAdapter myBaseAdapter;
	static String path=AppConstants.path;
	private Bitmap bitmap;
	
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
				Object obj=(Object)categoryList.get(position);
				if(obj instanceof String){
					return;
				}
				
				Intent intent=new Intent();
				intent.setClass(CategoryActivity.this, SubCategoryActivity.class);
				startActivity(intent);
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
			ApplicationData.exit(CategoryActivity.this);
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	

}
