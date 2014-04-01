package ca.techguys.takemynotes.view;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;

public class SellNotesActivity extends Activity {

	EditText nameEdt;
	EditText priceEdt;
	EditText descEdt;
	
	Spinner cateSpinner;

	Button postBtn;

	String name;
	String category;
	Float price;
	String desc;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sell_notes);
		
		setTitle("Sell note");
		
		
		
		
		
		postBtn=(Button) findViewById(R.id.lgCreateBtn);
		postBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				postBtn.setBackgroundResource(R.drawable.registerbgpress);
				
				name=nameEdt.getText().toString();
				category=cateSpinner.getSelectedItem().toString();
				price=Float.valueOf(priceEdt.getText().toString());
				desc=descEdt.getText().toString();
				
				TakeMyNotesRequest request=new TakeMyNotesRequest(getApplicationContext());
				
				
				
				
				
			}
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sell_notes, menu);
		return true;
	}

}
