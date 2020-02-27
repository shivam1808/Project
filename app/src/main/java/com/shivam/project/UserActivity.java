package com.shivam.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity{

    private SmartMaterialSpinner spProvince;
    private SmartMaterialSpinner spEmptyItem;
    private List<String> provinceList;

    private static final String THINGSPEAK_FIELD1 = "field1";
    private static final String THINGSPEAK_FIELD2 = "field2";
    private static final String THINGSPEAK_FIELD3 = "field3";

    TextView t1,t2,t3,t4, location;
    ImageView i1, i2, i3;
    Button b2, sensor;
    EditText et1, et2;
    CheckBox alert;

    int sensor1 = 40;
    int sensor2 = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);t1=(TextView)findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView3);
        t3=(TextView)findViewById(R.id.textView5);
        t4=(TextView)findViewById(R.id.textView6);

        location=(TextView)findViewById(R.id.location);

        i1=findViewById(R.id.imageView);
        i2=findViewById(R.id.imageView2);
        i3=findViewById(R.id.imageView3);

        sensor=findViewById(R.id.button);
        b2=findViewById(R.id.button2);

        alert = findViewById(R.id.checkBox);

        if(alert.isChecked())
        {
            Toast.makeText(UserActivity.this, "Alert will be send through Mail!!!", Toast.LENGTH_LONG).show();
            Log.d("Response", "Checked Alert");
        }

        initSpinner();

        t2.setText(" ");

        sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et1 = findViewById(R.id.threshold);
                et2 = findViewById(R.id.threshold2);

                sensor1 = Integer.parseInt(et1.getText().toString());
                sensor2 = Integer.parseInt(et2.getText().toString());

                Toast.makeText(UserActivity.this, "Threshold Value Saved", Toast.LENGTH_LONG).show();
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
                Toast.makeText(UserActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
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
                else if (value>sensor1)
                    i1.setImageResource(R.drawable.warning);
                else
                    i1.setImageResource(R.drawable.normal);

                if(value2>30)
                    i2.setImageResource(R.drawable.alert);
                else if (value2>sensor2)
                    i2.setImageResource(R.drawable.warning);
                else
                    i2.setImageResource(R.drawable.normal);

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
                Toast.makeText(UserActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
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
                else if (value>sensor1)
                    i1.setImageResource(R.drawable.warning);
                else
                    i1.setImageResource(R.drawable.normal);

                if(value2>30)
                    i2.setImageResource(R.drawable.alert);
                else if (value2>sensor2)
                    i2.setImageResource(R.drawable.warning);
                else
                    i2.setImageResource(R.drawable.normal);

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
                Toast.makeText(UserActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
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
                else if (value>sensor1)
                    i1.setImageResource(R.drawable.warning);
                else
                    i1.setImageResource(R.drawable.normal);

                if(value2>30)
                    i2.setImageResource(R.drawable.alert);
                else if (value2>sensor2)
                    i2.setImageResource(R.drawable.warning);
                else
                    i2.setImageResource(R.drawable.normal);

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
                Toast.makeText(UserActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
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
                else if (value>sensor1)
                    i1.setImageResource(R.drawable.warning);
                else
                    i1.setImageResource(R.drawable.normal);

                if(value2>30)
                    i2.setImageResource(R.drawable.alert);
                else if (value2>sensor2)
                    i2.setImageResource(R.drawable.warning);
                else
                    i2.setImageResource(R.drawable.normal);

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
                Toast.makeText(UserActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
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
                else if (value>sensor1)
                    i1.setImageResource(R.drawable.warning);
                else
                    i1.setImageResource(R.drawable.normal);

                if(value2>30)
                    i2.setImageResource(R.drawable.alert);
                else if (value2>sensor2)
                    i2.setImageResource(R.drawable.warning);
                else
                    i2.setImageResource(R.drawable.normal);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void initSpinner() {
        spProvince = findViewById(R.id.spinner1);
        provinceList = new ArrayList<>();

        provinceList.add("Dehradun");
        provinceList.add("Haridwar");
        provinceList.add("Rishikesh");
        provinceList.add("Roorkee");
        provinceList.add("Lucknow");

        spProvince.setItem(provinceList);

        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(UserActivity.this, provinceList.get(position), Toast.LENGTH_SHORT).show();
                if (position==0)
                {
                    location.setText("Dehradun");
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
                            }
                            catch(Exception e){
                                Log.e("ERROR", e.getMessage(), e);
                            }
                        }
                    });
                }
                else if (position==1)
                {
                    location.setText("Haridwar");
                    try {
                        new FetchThingspeakTask2().execute();
                    }
                    catch(Exception e){
                        Log.e("ERROR", e.getMessage(), e);
                    }

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                new FetchThingspeakTask2().execute();
                            }
                            catch(Exception e){
                                Log.e("ERROR", e.getMessage(), e);
                            }
                        }
                    });
                }
                else if (position==2)
                {
                    location.setText("Rishikesh");
                    try {
                        new FetchThingspeakTask3().execute();
                    }
                    catch(Exception e){
                        Log.e("ERROR", e.getMessage(), e);
                    }

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                new FetchThingspeakTask3().execute();
                            }
                            catch(Exception e){
                                Log.e("ERROR", e.getMessage(), e);
                            }
                        }
                    });
                }
                else if (position==3)
                {
                    location.setText("Roorkee");
                    try {
                        new FetchThingspeakTask4().execute();
                    }
                    catch(Exception e){
                        Log.e("ERROR", e.getMessage(), e);
                    }

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                new FetchThingspeakTask4().execute();
                            }
                            catch(Exception e){
                                Log.e("ERROR", e.getMessage(), e);
                            }
                        }
                    });
                }
                else if (position==4)
                {
                    location.setText("Lucknow");
                    try {
                        new FetchThingspeakTask5().execute();
                    }
                    catch(Exception e){
                        Log.e("ERROR", e.getMessage(), e);
                    }

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                new FetchThingspeakTask5().execute();
                            }
                            catch(Exception e){
                                Log.e("ERROR", e.getMessage(), e);
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


}
