package com.example.realestatesearch;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		TabHost th=(TabHost)findViewById(R.id.tabhost);
		th.setup();
		TabSpec ts= th.newTabSpec("tag1");
		ts.setContent(R.id.tab1);
		ts.setIndicator("Basic Information");
		th.addTab(ts);
		ts= th.newTabSpec("tag2");
		ts.setContent(R.id.tab2);
		ts.setIndicator("Historical Zestimates");
		th.addTab(ts);
		
		
		/*Intent intent = getIntent();
		String js = intent.getStringExtra(MainActivity.INPUT);	
		try
		{
		JSONObject res=new JSONObject(js);
		Toast toast = Toast.makeText(getApplicationContext(), res.getString("homedetails"), Toast.LENGTH_SHORT);
		toast.show();
		}
		catch(JSONException e)
		{
		Log.d("JSON Creation",e.toString());
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
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
