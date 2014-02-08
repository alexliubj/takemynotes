package ca.techguys.takemynotes.view;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import ca.techguys.takemynotes.net.Parse;
import ca.techguys.takemynotes.net.TakeMyNotesRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
public class CategoryActivity extends Activity {

	private DialogActivity dialog;
	private Intent intent;
	private String mobelNo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}


	private class GetData extends AsyncTask<String, String, String> {
		private Context mContext;

		public GetData(Context context) {
			this.mContext = context;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			if (null != dialog && !dialog.isShowing()) {
				dialog.show();
			}
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String result = null;
			String key = "test string";
			TakeMyNotesRequest request = new TakeMyNotesRequest(getApplicationContext());
			try {
				result = request.getRegisterCaptcha(mobelNo, key);
				Log.i("zzz", "result = " + result);
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
				Toast.makeText(CategoryActivity.this, R.string.network_failure, Toast.LENGTH_SHORT)
						.show();
			} else {
				if (new Parse().CommonPares(result).getResult() == 1) {
					intent = new Intent(CategoryActivity.this, SubCategoryActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("mobelNo", mobelNo);
					intent.putExtras(bundle);
					startActivity(intent);
				} else if (new Parse().CommonPares(result).getResult() == 0) {
					dialog = new DialogActivity(CategoryActivity.this, 2);
					dialog.getBtnSure().setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					dialog.setShowMessage(new Parse().CommonPares(result).getErrormessage());
					dialog.show();
				}
			}
		}
	}
}
