package ca.techguys.takemynotes.view;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectRoleActivity extends Activity implements OnClickListener {

	private Button buttonBuy;
	private Button buttonSell;
	
	private void init() {
		buttonBuy = (Button) findViewById(R.id.buttonBuy);
		buttonBuy.setOnClickListener(this);
		
		buttonSell = (Button) findViewById(R.id.buttonSell);
		buttonSell.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.buttonBuy:
			{
				startActivity(new Intent(SelectRoleActivity.this,  CategoryActivity.class));
				SelectRoleActivity.this.finish();
			}
			break;
			
			case R.id.buttonSell:
			{
				startActivity(new Intent(SelectRoleActivity.this,  SellNotesActivity.class));
				SelectRoleActivity.this.finish();
			}
			break;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_role);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_role, menu);
		return true;
	}

}
