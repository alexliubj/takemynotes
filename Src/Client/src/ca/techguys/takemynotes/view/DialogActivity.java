package ca.techguys.takemynotes.view;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.beans.ApplicationData;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class DialogActivity extends Dialog {
	public Context mContext;
	public Dialog dialog;
	private TextView tvMessage;
	private Button btnSure;
	private Button btnCancel;
	public int mType;
	

	public DialogActivity(Context context, int type) {
		super(context, R.style.dialog_meijika);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.mType = type;
		if (mType == 1) {
			setContentView(R.layout.dialog_load);
			btnCancel = (Button) findViewById(R.id.btn_cancel);
			disDialog();
		} else {
			setContentView(R.layout.dialog_prompt);
			tvMessage = (TextView) findViewById(R.id.tv_message);
			btnSure = (Button) findViewById(R.id.btn_sure);
		}
		setProperty();
	}

	private void setProperty() {
		Window window = getWindow();
		WindowManager.LayoutParams p = window.getAttributes();
		Display d = getWindow().getWindowManager().getDefaultDisplay();

		p.height = (int) (d.getHeight() * 1);
		p.width = (int) (d.getWidth() * 1);
		window.setAttributes(p);
	}

	public void setShowMessage(String showMessage) {
		tvMessage.setText(showMessage);
	}

	public Button getBtnSure() {
		return btnSure;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void disDialog() {
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ApplicationData.isCancel = false;
				dis();
			}
		});
	}

	private void dis() {
		// TODO Auto-generated method stub
		this.dismiss();
	}
}
