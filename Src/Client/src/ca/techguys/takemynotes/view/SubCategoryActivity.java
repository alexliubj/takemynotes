package ca.techguys.takemynotes.view;



import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import android.os.Bundle;
import android.app.Activity;
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
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
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
