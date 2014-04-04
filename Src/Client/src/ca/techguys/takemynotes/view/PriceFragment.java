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
import ca.techguys.takemynotes.model.Note;

import com.actionbarsherlock.app.SherlockListFragment;

public class PriceFragment extends SherlockListFragment{

	
	private List<Note> myNotes;
	private int numOfNote;
	private String info[];
	
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
        	info[c]=myNotes.get(c).name+"\n"+myNotes.get(c).desc+"\n"
        			+"$"+myNotes.get(c).price+"\n";
        	//info[c]="hello "+String.valueOf(c);
        	
        }
        
        for(int c=0; c<numOfNote; c++){
        	noteImgs[c]=R.drawable.note2;
        }
        
        for(int i=0;i<numOfNote;i++){
                HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", info[i]);
            hm.put("img", Integer.toString(noteImgs[i]  ) );
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
   		intent.putExtra("name", myNotes.get(position).name.toString());
   		
   		
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
		 
		 Note n3=new Note();
		 n3.name="note3";
		 n3.desc="good idea";
		 n3.price=15.10;
		 n3.contactName="Atom";
		 n3.phone="6478648288";
		 n3.email="sh@gmail.com";
		 
		 
		 Note n4=new Note();
		 n4.name="note4";
		 n4.desc="good idea";
		 n4.price=15.10;
		 n4.contactName="Atom";
		 n4.phone="6478648288";
		 n4.email="sh@gmail.com";
		 
		 Note n5=new Note();
		 n5.name="note5";
		 n5.desc="good idea";
		 n5.price=15.10;
		 n5.contactName="Atom";
		 n5.phone="6478648288";
		 n5.email="sh@gmail.com";
		 
		 Note n6=new Note();
		 n6.name="note6";
		 n6.desc="good idea";
		 n6.price=15.10;
		 n6.contactName="Atom";
		 n6.phone="6478648288";
		 n6.email="sh@gmail.com";
		 
		 Note n7=new Note();
		 n7.name="note7";
		 n7.desc="good idea";
		 n7.price=15.10;
		 n7.contactName="Atom";
		 n7.phone="6478648288";
		 n7.email="sh@gmail.com";
		 
		 Note n8=new Note();
		 n8.name="note8";
		 n8.desc="good idea";
		 n8.price=15.10;
		 n8.contactName="Atom";
		 n8.phone="6478648288";
		 n8.email="sh@gmail.com";
		 
		 Note n9=new Note();
		 n9.name="note9";
		 n9.desc="good idea";
		 n9.price=15.10;
		 n9.contactName="Atom";
		 n9.phone="6478648288";
		 n9.email="sh@gmail.com";
		 
		 
		 noteList.add(n1);
		 noteList.add(n2);
		 noteList.add(n3);
		 noteList.add(n4);
		 noteList.add(n5);
		 noteList.add(n6);
		 noteList.add(n7);
		 noteList.add(n8);
		 noteList.add(n9);
		 
		 return noteList;
		 
	}
	
	
     
}
