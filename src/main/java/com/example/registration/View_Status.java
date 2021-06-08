package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


import android.os.Bundle;


import android.os.Bundle;

public class View_Status extends AppCompatActivity {
    int pid;
    String tname,boatname,owner,bdate,status;
    TextView tname1,boatname1,owner1,bdate1,status1;
    Tourist tourist = SharedPrefManager.getInstance(this).getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__status);

        pid = tourist.getTid();
        tname1 = findViewById(R.id.tv_name);
        boatname1 = findViewById(R.id.tv_boat);
        owner1 = findViewById(R.id.tv_owner);
        bdate1 = findViewById(R.id.tv_bdate);
        status1 = findViewById(R.id.tv_status);
        
        loadbookingstatus();
        

    }

    private void loadbookingstatus() {
        class Loadpatientdetails extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {

                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("to_name", tourist.getName());

                return requestHandler.sendPostRequest(URLs.URL_VIEWSTATUS, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONArray array = new JSONArray(s);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject users = array.getJSONObject(i);


                        tname=users.getString("tname");
                        boatname=users.getString("boatname");
                        owner=users.getString("owner");
                        bdate=users.getString("bdate");
                        status=users.getString("status");

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setdetails();
            }
        }
        Loadpatientdetails lp = new Loadpatientdetails();
        lp.execute();
    }
    private void setdetails()
    {
        tname1.setText(tname);
        boatname1.setText(boatname);
        owner1.setText(owner);
        bdate1.setText(bdate);
        status1.setText(status);

    }
}