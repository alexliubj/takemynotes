package ca.techguys.takemynotes.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import ca.techguys.takemynotes.R;

import com.actionbarsherlock.app.SherlockListFragment;

public class PriceFragment extends SherlockListFragment{

	
	/** An array of items to display */
    String android_versions[] = new String[]{
            "Jelly Bean",
            "IceCream Sandwich",
            "HoneyComb",
            "GingerBread",
            "Froyo"
    };
    
    /** An array of items to display */
    int android_images[] = new int[]{
            R.drawable.jb,
            R.drawable.ics,
            R.drawable.honeycomb,
            R.drawable.gingerbread,
            R.drawable.froyo
    };
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	     	
    	// Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<5;i++){
                HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", android_versions[i]);
            hm.put("img", Integer.toString(android_images[i]  ) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "img","txt" };

        // Ids of views in listview_layout
        int[] to = { R.id.img,R.id.info};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.subcate_listview, from, to);        
        
        // Setting the adapter to the listView
        setListAdapter(adapter);        
        
        return super.onCreateView(inflater, container, savedInstanceState);

    }
    
    
     @Override
     public void onListItemClick(ListView l, View v, int position, long id) {     
           
       	Intent intent=new Intent(getActivity(), NoteDetailsActivity.class);
   		
   		intent.putExtra("value1", String.valueOf(position));
   		
   		startActivity(intent);
           
     }  

     
     
}
