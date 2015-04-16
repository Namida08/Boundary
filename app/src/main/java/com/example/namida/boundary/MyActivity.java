package com.example.namida.boundary;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class MyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		Window window = getWindow();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		WindowManager manager = window.getWindowManager();
		Display disp = manager.getDefaultDisplay();
		int disp_w = disp.getWidth();
		int disp_h = disp.getHeight();

		//setContentView(R.layout.activity_my);
		//setContentView(new MainLoop(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
