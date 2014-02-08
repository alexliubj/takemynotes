package ca.techguys.takemynotes.view;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.google.gson.JsonSyntaxException;
import ca.techguys.takemynotes.beans.ToastUtil;
import ca.techguys.takemynotes.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;

/**
 * @author kenny.li
 * 
 */
public abstract class BaseAsyncTask extends AsyncTask<String, String, String> {

	public enum DialogShow {
		normal, hide
	}

	private DialogActivity dialog;

	private boolean isRunning;
	private boolean isException;
	private Context mContext;
	private DialogShow dialogShow;

	public void setException(boolean isException) {
		this.isException = isException;
	}

	public Context getmContext() {
		return mContext;
	}

	public BaseAsyncTask(Context ctx, DialogShow dialogShow) {
		this.mContext = ctx;
		isRunning = true;
		isException = false;
		this.dialogShow = dialogShow;
	}

	@Override
	protected void onPreExecute() {
		dialogShow();
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		String result = "";
		try {
			result = getNetJsonData(params);
			if (null == result) {
				result = "";
			}
		} catch (IOException e) {
			setException(true);
			result = String.valueOf(R.string.network_failure);
			e.printStackTrace();
		} catch (TimeoutException e) {
			setException(true);
			result = String.valueOf(R.string.network_failure);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		dialogDismiss();
		if (isRunning) {
			if (isException) {
				hasException(result);
				if (onHasException != null) {
					onHasException.hasExceptionExe(result);
				}
			} else {
				try {
					parseJson(result);
					if (onParseJsonDone != null) {
						onParseJsonDone.parseJsonDoneExe();
					}
				} catch (JsonSyntaxException e) {
					hasException(String.valueOf(R.string.network_failure));
					e.printStackTrace();
				}
			}
		}
	}

	private void dialogShow() {

		switch (dialogShow) {
		case normal:
			dialog = new DialogActivity(mContext, 0);
			dialog.setCancelable(true);
			dialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					isRunning = false;
				}
			});
			dialog.show();
			break;
		default:
			break;
		}

	}

	private void dialogDismiss() {
		if (null != dialog && dialog.isShowing()) {
			switch (dialogShow) {
			case normal:
				dialog.dismiss();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @return get json
	 */
	public abstract String getNetJsonData(String[] params) throws IOException, TimeoutException;

	/**
	 * parse json
	 */
	public abstract void parseJson(String json) throws JsonSyntaxException;

	/**
	 */
	public void hasException(String resultId) {
		switch (dialogShow) {
		case normal:
			ToastUtil.showText((Activity)mContext, Integer.parseInt(resultId));
			break;
		default:
			break;
		}
	}

	@Override
	protected void onCancelled() {
		isRunning = false;
		super.onCancelled();
	}

	private OnHasException onHasException;
	private OnParseJsonDone onParseJsonDone;

	public void setOnHasException(OnHasException onHasException) {
		this.onHasException = onHasException;
	}

	public void setOnParseJsonDone(OnParseJsonDone onParseJsonDone) {
		this.onParseJsonDone = onParseJsonDone;
	}

	public interface OnParseJsonDone {
		public void parseJsonDoneExe();
	}

	public interface OnHasException {
		public void hasExceptionExe(String result);
	}
}