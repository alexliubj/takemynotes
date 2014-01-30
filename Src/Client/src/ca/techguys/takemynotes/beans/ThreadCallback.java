package ca.techguys.takemynotes.beans;

import java.io.Serializable;

/**
 * Call back
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 */
public interface ThreadCallback extends Serializable{
	public void onCallbackFromThread(int callid);
}
