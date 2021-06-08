package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Locale;
import android.app.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText phnno;
    EditText c_password;
    EditText email;
    String s_name,s_phn, s_cpass, s_pass, s_email;

    Button btnreg,sign,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        username = (EditText) findViewById(R.id.username);
        phnno = (EditText) findViewById(R.id.phnno);
        password = (EditText) findViewById(R.id.password);
        c_password = (EditText) findViewById(R.id.cpassword);
        btnreg = (Button) findViewById(R.id.bt_register);
        sign = (Button) findViewById(R.id.bt_signin);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent in = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(in);
            }
        });



        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s_name = username.getText().toString();
                s_phn = phnno.getText().toString();
                s_pass = password.getText().toString();
                s_cpass = c_password.getText().toString();

                if (username.length() == 0) {
                    username.requestFocus();
                    username.setError("please enter your name");
                }
                else if (phnno.length() == 0) {
                    phnno.requestFocus();
                    phnno.setError("please enter your phone");
                }
                else if (password.length() == 0) {
                    password.requestFocus();
                    password.setError("please enter your password");
                }
                else if (phnno.length() != 10) {
                    phnno.requestFocus();
                    phnno.setError("please enter valid mobile number");
                }
                else if (password.length() == 0) {
                    password.requestFocus();
                    password.setError("please enter your password");
                }
                else if (!s_pass.equals(s_cpass)) {
                    password.requestFocus();
                    password.setError(" password miss match");
                }

                else {

                   registerUser();
                }

            }
        });





    }
    private void registerUser()
    {
        s_name = username.getText().toString().trim();
        s_phn = phnno.getText().toString().trim();
        s_cpass = password.getText().toString().trim();

        class RegisterUser extends AsyncTask<Void, Void, String>
        {



            @Override
            protected String doInBackground(Void... voids)
            {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("sname", s_name);
                params.put("sphone", s_phn);
                params.put("password", s_pass);


                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
            }


            @Override
            protected void onPreExecute() {
                super.onPreExecute();


            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);



                try
                {
                    JSONObject obj = new JSONObject(s);

                    if (!obj.getBoolean("error"))
                    {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONObject userJson = obj.getJSONObject("user");

                        Tourist tourist = new Tourist(
                                userJson.getInt("tid"),
                                userJson.getString("name"),
                                userJson.getString("mobile")


                        );

                        SharedPrefManager.getInstance(getApplicationContext()).touristLogin(tourist);

                        Intent verifyIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(verifyIntent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "mobile already registered.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute();
    }
}