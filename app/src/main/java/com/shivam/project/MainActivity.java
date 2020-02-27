package com.shivam.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "UsingThingspeakAPI";
    private static final String THINGSPEAK_CHANNEL_ID = "908725";
    private static final String THINGSPEAK_API_KEY = "6ZBMMBTWS37BH8VD";
    private static final String THINGSPEAK_API_KEY_STRING = "api_key";

    /* Be sure to use the correct fields for your own app*/
    private static final String THINGSPEAK_FIELD1 = "field1";
    private static final String THINGSPEAK_FIELD2 = "field2";
    private static final String THINGSPEAK_FIELD3 = "field3";

    private static final String THINGSPEAK_UPDATE_URL = "https://api.thingspeak.com/update?";
    private static final String THINGSPEAK_CHANNEL_URL = "https://api.thingspeak.com/channels/";
    private static final String THINGSPEAK_FEEDS_LAST = "/feeds/last?";
    TextView t1,t2,t3,t4;
    ImageView i1, i2, i3;
    Button b1, b2;

    TextView t1_2,t3_2,t4_2;
    ImageView i1_2, i2_2, i3_2;

    TextView t1_3,t3_3,t4_3;
    ImageView i1_3, i2_3, i3_3;

    TextView t1_4,t3_4,t4_4;
    ImageView i1_4, i2_4, i3_4;

    TextView t1_5,t3_5,t4_5;
    ImageView i1_5, i2_5, i3_5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView3);
        t3=(TextView)findViewById(R.id.textView5);
        t4=(TextView)findViewById(R.id.textView6);

        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);

        i1=findViewById(R.id.imageView);
        i2=findViewById(R.id.imageView2);
        i3=findViewById(R.id.imageView3);

        i1_2=findViewById(R.id.imageView_2);
        i2_2=findViewById(R.id.imageView2_2);
        i3_2=findViewById(R.id.imageView3_2);

        t1_2=(TextView)findViewById(R.id.textView2_2);
        t3_2=(TextView)findViewById(R.id.textView5_2);
        t4_2=(TextView)findViewById(R.id.textView6_2);

        i1_3=findViewById(R.id.imageView_3);
        i2_3=findViewById(R.id.imageView2_3);
        i3_3=findViewById(R.id.imageView3_3);

        t1_3=(TextView)findViewById(R.id.textView2_3);
        t3_3=(TextView)findViewById(R.id.textView5_3);
        t4_3=(TextView)findViewById(R.id.textView6_3);

        i1_4=findViewById(R.id.imageView_4);
        i2_4=findViewById(R.id.imageView2_4);
        i3_4=findViewById(R.id.imageView3_4);

        t1_4=(TextView)findViewById(R.id.textView2_4);
        t3_4=(TextView)findViewById(R.id.textView5_4);
        t4_4=(TextView)findViewById(R.id.textView6_4);

        i1_5=findViewById(R.id.imageView_5);
        i2_5=findViewById(R.id.imageView2_5);
        i3_5=findViewById(R.id.imageView3_5);

        t1_5=(TextView)findViewById(R.id.textView2_5);
        t3_5=(TextView)findViewById(R.id.textView5_5);
        t4_5=(TextView)findViewById(R.id.textView6_5);

        t2.setText(" ");
        try {
            new FetchThingspeakTask().execute();
        }
        catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new FetchThingspeakTask().execute();
                    new FetchThingspeakTask2().execute();
                    new FetchThingspeakTask3().execute();
                    new FetchThingspeakTask4().execute();
                    new FetchThingspeakTask5().execute();
                }
                catch(Exception e){
                    Log.e("ERROR", e.getMessage(), e);
                }
            }
        });
    }


    class FetchThingspeakTask extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            t2.setText("Fetching Data from Server. Please Wait...");
        }

        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL("https://api.thingspeak.com/channels/984170/feeds/last.json?api_key=H56CO1NEIC751AXF" + "");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        protected void onPostExecute(String response) {

            if(response == null) {
                Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {

                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                double v1 = channel.getDouble(THINGSPEAK_FIELD1);
                double v2 = channel.getDouble(THINGSPEAK_FIELD2);
                double v3 = channel.getDouble(THINGSPEAK_FIELD3);
                int value = (int) v1;
                t1.setText(value+" cm");
                float value2 = (float) v2;
                t3.setText(value2+" millitres/min");
                int value3 = (int) v3;
                if(value3==1)
                {
                    t4.setText("ON");
                    i3.setImageResource(R.drawable.on);
                }
                else
                {
                    t4.setText("OFF");
                    i3.setImageResource(R.drawable.off);
                }


                if(value>70)
                    i1.setImageResource(R.drawable.alert);
                else if (value>40)
                    i1.setImageResource(R.drawable.warning);
                else
                    i1.setImageResource(R.drawable.normal);

                if(value2>30)
                    i2.setImageResource(R.drawable.alert);
                else if (value2>20)
                    i2.setImageResource(R.drawable.warning);
                else
                    i2.setImageResource(R.drawable.normal);

                new FetchThingspeakTask2().execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class FetchThingspeakTask2 extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            t2.setText("Fetching Data from Server. Please Wait...");
        }

        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL("https://api.thingspeak.com/channels/984173/feeds/last.json?api_key=NSAPSP3PBDDSL5XH" + "");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        protected void onPostExecute(String response) {

            if(response == null) {
                Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {

                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                double v1 = channel.getDouble(THINGSPEAK_FIELD1);
                double v2 = channel.getDouble(THINGSPEAK_FIELD2);
                double v3 = channel.getDouble(THINGSPEAK_FIELD3);
                int value = (int) v1;
                t1_2.setText(value+" cm");
                float value2 = (float) v2;
                t3_2.setText(value2+" millitres/min");
                int value3 = (int) v3;
                if(value3==1)
                {
                    t4_2.setText("ON");
                    i3_2.setImageResource(R.drawable.on);
                }
                else
                {
                    t4_2.setText("OFF");
                    i3_2.setImageResource(R.drawable.off);
                }


                if(value>70)
                    i1_2.setImageResource(R.drawable.alert);
                else if (value>40)
                    i1_2.setImageResource(R.drawable.warning);
                else
                    i1_2.setImageResource(R.drawable.normal);

                if(value2>30)
                    i2_2.setImageResource(R.drawable.alert);
                else if (value2>20)
                    i2_2.setImageResource(R.drawable.warning);
                else
                    i2_2.setImageResource(R.drawable.normal);

                new FetchThingspeakTask3().execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class FetchThingspeakTask3 extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            t2.setText("Fetching Data from Server. Please Wait...");
        }

        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL("https://api.thingspeak.com/channels/984174/feeds/last.json?api_key=8BRM3PKLICAZ6OGY" + "");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        protected void onPostExecute(String response) {

            if(response == null) {
                Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {

                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                double v1 = channel.getDouble(THINGSPEAK_FIELD1);
                double v2 = channel.getDouble(THINGSPEAK_FIELD2);
                double v3 = channel.getDouble(THINGSPEAK_FIELD3);
                int value = (int) v1 ;
                t1_3.setText(value+" cm");
                float value2 = (float) v2;
                t3_3.setText(value2+" millitres/min");
                int value3 = (int) v3;
                if(value3==1)
                {
                    t4_3.setText("ON");
                    i3_3.setImageResource(R.drawable.on);
                }
                else
                {
                    t4_3.setText("OFF");
                    i3_3.setImageResource(R.drawable.off);
                }


                if(value>70)
                    i1_3.setImageResource(R.drawable.alert);
                else if (value>40)
                    i1_3.setImageResource(R.drawable.warning);
                else
                    i1_3.setImageResource(R.drawable.normal);

                if(value2>30)
                    i2_3.setImageResource(R.drawable.alert);
                else if (value2>20)
                    i2_3.setImageResource(R.drawable.warning);
                else
                    i2_3.setImageResource(R.drawable.normal);

                new FetchThingspeakTask4().execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class FetchThingspeakTask4 extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            t2.setText("Fetching Data from Server. Please Wait...");
        }

        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL("https://api.thingspeak.com/channels/984177/feeds/last.json?api_key=OUYD7GR6R9AT8UXD" + "");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        protected void onPostExecute(String response) {

            if(response == null) {
                Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {

                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                double v1 = channel.getDouble(THINGSPEAK_FIELD1);
                double v2 = channel.getDouble(THINGSPEAK_FIELD2);
                double v3 = channel.getDouble(THINGSPEAK_FIELD3);
                int value = (int) v1;
                t1_4.setText(value+" cm");
                float value2 = (float) v2;
                t3_4.setText(value2+" millitres/min");
                int value3 = (int) v3;
                if(value3==1)
                {
                    t4_4.setText("ON");
                    i3_4.setImageResource(R.drawable.on);
                }
                else
                {
                    t4_4.setText("OFF");
                    i3_4.setImageResource(R.drawable.off);
                }


                if(value>70)
                    i1_4.setImageResource(R.drawable.alert);
                else if (value>40)
                    i1_4.setImageResource(R.drawable.warning);
                else
                    i1_4.setImageResource(R.drawable.normal);

                if(value2>30)
                    i2_4.setImageResource(R.drawable.alert);
                else if (value2>20)
                    i2_4.setImageResource(R.drawable.warning);
                else
                    i2_4.setImageResource(R.drawable.normal);

                new FetchThingspeakTask5().execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class FetchThingspeakTask5 extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            t2.setText("Fetching Data from Server. Please Wait...");
        }

        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL("https://api.thingspeak.com/channels/984179/feeds/last.json?api_key=X2FETKYCW5FAW6OP" + "");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        protected void onPostExecute(String response) {

            if(response == null) {
                Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {

                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                double v1 = channel.getDouble(THINGSPEAK_FIELD1);
                double v2 = channel.getDouble(THINGSPEAK_FIELD2);
                double v3 = channel.getDouble(THINGSPEAK_FIELD3);
                int value = (int) v1;
                t1_5.setText(value+" cm");
                float value2 = (float) v2;
                t3_5.setText(value2+" millitres/min");
                int value3 = (int) v3;
                if(value3==1)
                {
                    t4_5.setText("ON");
                    i3_5.setImageResource(R.drawable.on);
                }
                else
                {
                    t4_5.setText("OFF");
                    i3_5.setImageResource(R.drawable.off);
                }


                if(value>70)
                    i1_5.setImageResource(R.drawable.alert);
                else if (value>40)
                    i1_5.setImageResource(R.drawable.warning);
                else
                    i1_5.setImageResource(R.drawable.normal);

                if(value2>30)
                    i2_5.setImageResource(R.drawable.alert);
                else if (value2>20)
                    i2_5.setImageResource(R.drawable.warning);
                else
                    i2_5.setImageResource(R.drawable.normal);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}