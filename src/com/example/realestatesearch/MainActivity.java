package com.example.realestatesearch;

import org.json.JSONException;
import org.json.JSONObject;
import android.R;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
public final static String INPUT = "Input";
private class getJSON extends AsyncTask<String, Void, String> {
   @Override
   protected String doInBackground(String... urls) {
	   
       String output = "";
       for (String url : urls) {
       	ServiceHandler sh = new ServiceHandler();
       	output= sh.makeServiceCall(url, ServiceHandler.GET);
       }
       return output;
   }

   @Override
   protected void onPostExecute(String output)
   {
	   try
	   	{
	   	JSONObject val=new JSONObject(output);
	   	JSONObject res=val.getJSONObject("result");
	   	String checkCode=res.getString("code").toString();
	   	Toast toast1 = Toast.makeText(getApplicationContext(), checkCode, Toast.LENGTH_SHORT);
	   	toast1.show();
	   	if(checkCode.equals("0"))
	   	{
	   	Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
	   	intent.putExtra(INPUT, output);
	   	startActivity(intent);
	   	}
	   	else
	   	{
	   	TextView textViewToChange = (TextView) findViewById(R.id.matchnotfound);
	   	textViewToChange.setText("No exact match found--Verify that the given address is correct.");
	   	Toast toast = Toast.makeText(getApplicationContext(), "No exact match found", Toast.LENGTH_SHORT);
	   	toast.show();
	   	}
	   	}
	   	catch(JSONException e)
	   	{
	   	Log.d("JSON Creation",e.toString());
	   	}
	   }
}

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
Spinner spinner = (Spinner) findViewById(R.id.spinner1);
ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.states, android.R.layout.simple_spinner_item);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinner.setAdapter(adapter);
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.main, menu);
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
public void validate(View view) throws Exception
{
	
EditText editText = (EditText) findViewById(R.id.editText1);
String address=editText.getText().toString();
editText = (EditText) findViewById(R.id.editText2);
String city=editText.getText().toString();
Spinner spinner=(Spinner) findViewById(R.id.spinner1);
String state=spinner.getSelectedItem().toString();
String values="http://myfirstapplication17-env.elasticbeanstalk.com/?street="+address+"&city="+city+"&state="+state;
values=values.replaceAll("\\s","+");
try
{
getJSON jtask=new getJSON();
jtask.execute(values);

}
catch(Exception e)
{
Log.d("getJSON",e.toString());
}
}
public void zillowpage(View view)
{
Uri uriUrl = Uri.parse("http://www.zillow.com");
Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
}
}