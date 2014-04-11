package ca.techguys.takemynotes.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.beans.Note;

import com.actionbarsherlock.app.SherlockListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DateFragment extends SherlockListFragment{

	private List<Note> myNotes;
	private int numOfNote;
	private String info[];
	
    private int noteImgs[];
    private int favImgs[];
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	     	
    	// Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        myNotes=getNotes();
        numOfNote=myNotes.size();
        
        info=new String[numOfNote];
        noteImgs=new int[numOfNote];
        favImgs=new int[numOfNote];
        
        //generate note images
        for(int c=0; c<numOfNote; c++){
        	noteImgs[c]=R.drawable.note2;
        }
        
        //generate note's info
//        for(int c=0; c<numOfNote; c++){
//        	info[c]=myNotes.get(c).name+"\n"+myNotes.get(c).desc+"\n"
//        			+"$"+myNotes.get(c).price+"\n";
//        	
//        }
        
        //generate favorite images
        for(int c=0; c<numOfNote; c++){
        	
        	if(c%2==0){
        		//which user add this note as favorite
        		favImgs[c]=R.drawable.heart;
        	}else{
        		//which user didn't add this note as favorite
        		favImgs[c]=android.R.color.transparent;
        	}
        	
        }
        
        
        
        for(int i=0;i<numOfNote;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", info[i]);
            hm.put("img", Integer.toString(noteImgs[i]) );
            hm.put("favImg", Integer.toString(favImgs[i]) );
            aList.add(hm);
        }

        
        // Keys used in Hashmap
        String[] from = { "img","txt", "favImg" };

        // Ids of views in listview_layout
        int[] to = { R.id.img,R.id.info, R.id.favImg};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.subcate_listview, from, to);        
      
        // Setting the adapter to the listView
        setListAdapter(adapter);        
        
        super.onCreateView(inflater, container, savedInstanceState).setBackgroundColor(R.drawable.itembg);
        
        return super.onCreateView(inflater, container, savedInstanceState);

    }
    
    
     @Override
     public void onListItemClick(ListView l, View v, int position, long id) {  
    	 
       	Intent intent=new Intent(getActivity(), NoteDetailsActivity.class);
   		
//   		intent.putExtra("value1", String.valueOf(position));
//   		intent.putExtra("name", myNotes.get(position).name.toString());
//   		
//   		
   		
   		startActivity(intent);
           
     }  
     
     public List<Note> getNotes(){
    	 
    	 List<Note> noteList=new ArrayList<Note>();
    	 
    	 
    	 return noteList;
     }
     
     
}
