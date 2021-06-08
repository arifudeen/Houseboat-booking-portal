package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class complient extends AppCompatActivity {
    EditText et_compltbox;
    TextView txt_des;
    Button btn_add;
    String compltbox1, desccription;
    String p_txt_des;
    int tourid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complient);
        Tourist tourist = SharedPrefManager.getInstance(this).getUser();
        tourid = tourist.getTid();
        et_compltbox = findViewById(R.id.txt_compl);
        btn_add = findViewById(R.id.bt_send);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        txt_des = (TextView) findViewById(R.id.txt_response);

        get_response();

    }

    private void get_response() {

        class GetResponse extends AsyncTask<Void, Void, String>
        {
            @Override
            protected String doInBackground(Void... voids)
            {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("t_id", String.valueOf(tourid));

                return requestHandler.sendPostRequest(URLs.URL_COMPLINTVIEW, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    txt_des.setText(jsonObject.getString("c_reply"));

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
        GetResponse getResponse = new GetResponse();
        getResponse.execute();
    }

    private void validate()
    {
//        p_txt_des = txt_des.getText().toString();
        if(et_compltbox.getText().toString().isEmpty())
        {
            et_compltbox.setError("Please enter Complient.");
            et_compltbox.requestFocus();
        }

        else
        {
           complients();
        }
    }

    private void complients() {
        compltbox1=et_compltbox.getText().toString().trim();

        class Complients extends AsyncTask<Void, Void, String>
        {

            @Override
            protected String doInBackground(Void... voids)
            {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("complint", compltbox1);
                params.put("tid", String.valueOf(tourid));

                return requestHandler.sendPostRequest(URLs.URL_COMPLINTAPI, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    if (!jsonObject.getBoolean("error"))
                    {
                        Toast.makeText(complient.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(complient.this, HomeActivity.class);
                        startActivity(homeIntent);
                        finish();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }

        Complients as = new Complients();
        as.execute();

    }

    }









