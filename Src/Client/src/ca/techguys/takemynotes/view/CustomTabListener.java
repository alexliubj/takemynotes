package ca.techguys.takemynotes.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;


public class CustomTabListener<T extends Fragment> implements TabListener {

	private Fragment mFragment;
	private final SherlockFragmentActivity mActivity;
	private final String mTag;
	private final Class<T> mClass;
	
	public CustomTabListener(SherlockFragmentActivity activity, String tag, Class<T> clz){
		mActivity = activity;
		mTag = tag;
		mClass = clz;
	}	
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// Nothing special to do here for this application
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {		
		mFragment = mActivity.getSupportFragmentManager().findFragmentByTag(mTag);
		if(mFragment==null){
			mFragment = Fragment.instantiate(mActivity, mClass.getName());
			ft.add(android.R.id.content,mFragment, mTag);		
		}else{
			ft.attach(mFragment);
		}		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if(mFragment!=null) {
			ft.detach(mFragment);
		}		
	}
	
}
