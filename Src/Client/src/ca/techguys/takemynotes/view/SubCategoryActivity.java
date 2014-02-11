package ca.techguys.takemynotes.view;

import java.util.ArrayList;
import java.util.List;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.R.layout;
import ca.techguys.takemynotes.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

public class SubCategoryActivity extends Activity {

	private static final String LIST1_TAB_TAG = "List1";
	private static final String LIST2_TAB_TAG = "List2";

	// The two views in our tabbed example
	private ListView listView1;
	private ListView listView2;

	private TabHost tabHost;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_category);
		
		//add by atom
		tabHost = getTabHost();
		tabHost.setOnTabChangedListener(this);

		// setup list view 1
		listView1 = (ListView) findViewById(R.id.list1);

		// create some dummy strings to add to the list
		List<String> list1Strings = new ArrayList<String>();
		list1Strings.add("Item 1");
		list1Strings.add("Item 2");
		list1Strings.add("Item 3");
		list1Strings.add("Item 4");
		listView1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list1Strings));

		// setup list view 2
		listView2 = (ListView) findViewById(R.id.list2);

		// create some dummy strings to add to the list (make it empty initially)
		List<String> list2Strings = new ArrayList<String>();
		listView2.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list2Strings));

		// add an onclicklistener to add an item from the first list to the second list
		listView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View view, int position, long id) {
				String item = (String) listView1.getAdapter().getItem(position);
				if(item != null) {
					((ArrayAdapter) listView2.getAdapter()).add(item);
					Toast.makeText(TabbedListListActivity.this, item + " added to list 2", Toast.LENGTH_SHORT).show();
				}
			}
		});

		// add views to tab host
		tabHost.addTab(tabHost.newTabSpec(LIST1_TAB_TAG).setIndicator(LIST1_TAB_TAG).setContent(new TabContentFactory() {
			public View createTabContent(String arg0) {
				return listView1;
			}
		}));
		tabHost.addTab(tabHost.newTabSpec(LIST2_TAB_TAG).setIndicator(LIST2_TAB_TAG).setContent(new TabContentFactory() {
			public View createTabContent(String arg0) {
				return listView2;
			}
		}));
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sub_category, menu);
		return true;
	}
	
	
	/**
	 * Implement logic here when a tab is selected
	 */
	public void onTabChanged(String tabName) {
		if(tabName.equals(LIST2_TAB_TAG)) {
			//do something
		}
		else if(tabName.equals(LIST1_TAB_TAG)) {
			//do something
		}
	}
	
	
	
	
}
