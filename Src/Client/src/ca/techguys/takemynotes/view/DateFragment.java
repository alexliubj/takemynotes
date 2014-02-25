package ca.techguys.takemynotes.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.model.Note;

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
	
	/** An array of items to display */
    /*private String android_versions[] = new String[]{
            "Name"+"\n"+"Desc"+"\n"+"Price",
            "IceCream Sandwich",
            "HoneyComb",
            "GingerBread",
            "Froyo",
            "HoneyComb",
            "GingerBread",
            "Froyo"
    };*/
    
    /** An array of items to display */
    private int noteImgs[];
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	     	
    	// Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        myNotes=getNotes();
        numOfNote=myNotes.size();
        
        info=new String[numOfNote];
        noteImgs=new int[numOfNote];
        
        for(int c=0; c<numOfNote; c++){
        	/*info[c]=myNotes.get(c).name+"\n"+myNotes.get(c).desc
        			+"\n"+myNotes.get(c).price;*/
        	info[c]="hello "+String.valueOf(c);
        	
        }
        
        for(int c=0; c<numOfNote; c++){
        	noteImgs[c]=R.drawable.note2;
        }
        
        
        for(int i=0;i<numOfNote;i++){
                HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", info[i]);
            hm.put("img", Integer.toString(noteImgs[i]) );
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
     
     public List<Note> getNotes(){
    	 
    	 List<Note> noteList=new ArrayList<Note>();
    	 
    	 Note n1=new Note();
    	 n1.name="note1";
    	 n1.desc="no idea";
    	 n1.price=43.00;
    	 n1.contactName="Atom";
    	 n1.phone="6478648288";
    	 n1.email="sh@gmail.com";
    	 
    	 Note n2=new Note();
    	 n2.name="note2";
    	 n2.desc="good idea";
    	 n2.price=15.10;
    	 n2.contactName="Atom";
    	 n2.phone="6478648288";
    	 n2.email="sh@gmail.com";
    	 
    	 noteList.add(n1);
    	 noteList.add(n2);
    	 
    	 return noteList;
     }
     
     
}
