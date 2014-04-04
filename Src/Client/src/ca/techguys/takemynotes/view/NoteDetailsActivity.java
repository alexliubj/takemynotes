package ca.techguys.takemynotes.view;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NoteDetailsActivity extends Activity {

	private String name;
	private String desc;
	private String price;
	private String contact;
	private String email;
	private String phone;
	
	private TextView nameTv=(TextView) findViewById(R.id.ndNoteNameTv);
	private TextView descTv=(TextView) findViewById(R.id.ndDescTv);
	private TextView contactTv=(TextView) findViewById(R.id.ndContactTv);
	private TextView emailTv=(TextView) findViewById(R.id.ndEmailTv);
	private TextView phoneTv=(TextView) findViewById(R.id.ndPhoneTv);
	private TextView priceTv=(TextView) findViewById(R.id.ndPriceTv);
	
	
	
	private Button commentBtn=(Button) findViewById(R.id.ndCommentBtn);
	private Button favBtn=(Button) findViewById(R.id.ndFavBtn);
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_details);
		
		setTitle("Note detail");
		String position=String.valueOf(getIntent().getStringExtra("value1"));
		
		
		init();
		
		//Assign value
		nameTv.setText(name);
		descTv.setText(desc);
		contactTv.setText(contact);
		emailTv.setText(email);
		phoneTv.setText(phone);
		priceTv.setText(price);
		
		
		
		
		
		//comment button handler
		commentBtn=(Button) findViewById(R.id.ndCommentBtn);
		commentBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(NoteDetailsActivity.this, PostCommentActivity.class);
				
		   		startActivity(intent);

			}
			
		});
		
		//comment button handler
		favBtn=(Button) findViewById(R.id.ndFavBtn);
		favBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//add to favourite list

			}
			
		});
		
		
		
	}
	
	//get string value from intent of notes 
	private void init(){
		
		name=String.valueOf(getIntent().getStringExtra("name"));
		desc=String.valueOf(getIntent().getStringExtra("desc"));
		contact=String.valueOf(getIntent().getStringExtra("desc"));
		email=String.valueOf(getIntent().getStringExtra("desc"));
		phone=String.valueOf(getIntent().getStringExtra("desc"));
		price=String.valueOf(Float.valueOf(getIntent().getStringExtra("desc")));
		
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
	        	Log.i("TEST", "====================== Menu creation called");
	            return true;
	        }
	    });
	    return true;
	    
		
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

}
