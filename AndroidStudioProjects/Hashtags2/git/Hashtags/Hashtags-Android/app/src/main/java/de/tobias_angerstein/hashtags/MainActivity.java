package de.tobias_angerstein.hashtags;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private static JSONArray jsonArray;
    private EditText editText;
    private String device_uuid;
    //TODO becomes ListView
    private ListView hashTagListView;
    private  ArrayAdapter<String> adapter;
    private ArrayList<String> hashTags = new ArrayList<String>();
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        hashTagListView = (ListView) findViewById(R.id.hashTagListView);
         mp = MediaPlayer.create(getApplicationContext(), R.drawable.sendhashtag);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, hashTags);

        hashTagListView.setAdapter(adapter);
        //generates unique device ID
        device_uuid = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        //allows no asynchronous sending => new Threads required
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        // Add the request to the RequestQueue.
        queue.add(this.getStringRequest());
        queue.start();
        Log.d("TOBIIDD", "TEST");


        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //not called in my PHONE!!!
                Log.d("TOBIIDD", "SDJFKLSJF");

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
                   Log.d("TOBIIDD", "DFJHASDLFKJLASDFK");
                    try {
                        mp.stop();
                        mp.seekTo(0);
                        mp = MediaPlayer.create(getApplicationContext(), R.drawable.sendhashtag);
                        mp.start();
                        // hashtag wird gesendet
                        //nicht nur enter gedrückt
                        if (!editText.getText().toString().equals("\n")) {
                            String hashtag = editText.getText().toString().substring(1, editText.getText().toString().length());
                            sendHashtag(hashtag);
                        } else {
                            //Log.d("Leerer String", "Sehr leerer String");
                        }
                        editText.setText("");
                        hashTagListView.setSelection(adapter.getCount() -1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }
    //sendet Hashtag via JSON und GET an mysql server
    private void sendHashtag(String hashtag) throws IOException, JSONException {

        if (!hashtag.equals("") && hashtag.charAt(0) == '#') {
            hashtag = hashtag.substring(1, hashtag.length());
        }
        //Create Json
        JSONObject json = new JSONObject();
        json.put("hashtag", hashtag).put("gps", "").put("userID", device_uuid);
        URL url = new URL("http://www.tobias-angerstein.de/mysql/send.php?" + "json=" + json.toString() + "&password=krautsalat5000");
        //create the connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        connection.getResponseMessage();
        //Log.d("Request:", url.toString());
        hashTags.add(hashtag);
        adapter.notifyDataSetChanged();
    }

    private void reloadHashtags(){

    }

    private StringRequest getStringRequest(){
        String url = "http://www.tobias-angerstein.de/mysql/receive.php?password=krautsalat5000";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jsonLoaded(response);
                        hashTagListView.setSelection(adapter.getCount()-1);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Read Request", "Didn't work !!");
            }
        });
        return stringRequest;
    }

    private void jsonLoaded(String response) {
        try {
            jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                    String hashtag = jsonArray.getJSONObject(i).getString("hashtag");
                    hashTags.add(hashtag);
                }
        }catch(Exception e){
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
