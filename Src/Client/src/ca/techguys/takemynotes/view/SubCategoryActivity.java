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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

public class SubCategoryActivity extends SherlockFragmentActivity  implements OnClickListener {

	/*private static final String LIST1_TAB_TAG = "List1";
	private static final String LIST2_TAB_TAG = "List2";

	// The two views in our tabbed example
	private ListView listView1;
	private ListView listView2;
	private TabHost tabHost;*/
	
	private DialogActivity dialog;
	private Intent intent;
	private String mobelNo;
	private ArrayList<CategoryItem> categoryList;
	private MyBaseAdapter myBaseAdapter;
	static String path=AppConstants.path;
	private Bitmap bitmap;
	private ArrayList<Note> tempModel;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setTitle("Notes");
        // Getting an instance of action bar
        ActionBar actionBar = getSupportActionBar();
        
        // Enabling Tab Navigation mode for this action bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        // Enabling Title
        actionBar.setDisplayShowTitleEnabled(true);
        
        // Creating Android Tab
        Tab tab1 = actionBar.newTab()
        					.setText("By Date")
        					.setTabListener(new CustomTabListener<DateFragment>(this, "By Date", DateFragment.class) );
        
        // Adding Android Tab to acton bar
        actionBar.addTab(tab1);
        
        // Creating Apple Tab
        Tab tab2 = actionBar.newTab()
				.setText("By Price")
				.setTabListener(new CustomTabListener<PriceFragment>(this, "By Price", PriceFragment.class));
        
        // Adding Apple Tab to action bar
        actionBar.addTab(tab2);        
        
        // Orientation Change Occurred
        if(savedInstanceState!=null){
        	int currentTabIndex = savedInstanceState.getInt("tab_index");
        	actionBar.setSelectedNavigationItem(currentTabIndex);
        }
        
        
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	int currentTabIndex = getSupportActionBar().getSelectedNavigationIndex();
    	outState.putInt("tab_index", currentTabIndex);
    	super.onSaveInstanceState(outState);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
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

							tempModel = new ArrayList<Note>();
							try {
								tempModel = new Parse().GetNotesByCategory(result);
							} catch (JsonSyntaxException e) {
								
								e.printStackTrace();
							}
							
							if (tempModel != null) {
								
								dialog.cancel();
//								Intent intent = new Intent(NoteDetailsActivity.this,
//										SubCategoryActivity.class);
//								intent.putExtra("tempModel", (Serializable)tempModel);
//								startActivity(intent);
//								finish();
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
			case 3:
				dialog.cancel();
				
				break;
			default:
				break;
			}
		}

	};
	
	/**
	 * Implement logic here when a tab is selected
	 */
	/*public void onTabChanged(String tabName) {
		if(tabName.equals(LIST2_TAB_TAG)) {
			//do something
		}
		else if(tabName.equals(LIST1_TAB_TAG)) {
			//do something
		}
	}*/
}
