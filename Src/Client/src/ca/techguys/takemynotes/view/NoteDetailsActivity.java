package ca.techguys.takemynotes.view;

import java.util.ArrayList;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import ca.techguys.takemynotes.beans.Note;
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

	private Note aNote;
	private TextView nameTv;
	private TextView descTv;
	private TextView contactTv;
	private TextView emailTv;
	private TextView phoneTv;
	private TextView priceTv;
	private Button commentBtn;
	private Button favBtn;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_details);
		
		setTitle("Note detail");
		nameTv=(TextView) findViewById(R.id.ndNoteNameTv);
		descTv=(TextView) findViewById(R.id.ndDescTv);
		contactTv=(TextView) findViewById(R.id.ndContactTv);
		emailTv=(TextView) findViewById(R.id.ndEmailTv);
		phoneTv=(TextView) findViewById(R.id.ndPhoneTv);
		priceTv=(TextView) findViewById(R.id.ndPriceTv);
		commentBtn=(Button) findViewById(R.id.ndCommentBtn);
		favBtn=(Button) findViewById(R.id.ndFavBtn);
		
		aNote = (Note)getIntent().getSerializableExtra("notedetails") ;
		init();
		


		commentBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(NoteDetailsActivity.this, PostCommentActivity.class);
				
		   		startActivity(intent);

			}
			
		});

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
