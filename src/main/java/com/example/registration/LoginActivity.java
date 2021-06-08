package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.view.View;
import android.widget.Toast;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {
    EditText usernamelog,passlog;
    Button login,signup,otplogin;
    ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernamelog = (EditText) findViewById(R.id.usernamelog);
        passlog = (EditText) findViewById(R.id.passwordlog);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.sign_up);

        if (SharedPrefManager.getInstance(this).isLoggedIn())
        {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToDas = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(goToDas);
                finish();

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();

           }
     });


   }
    private void validate()
    {
        if (usernamelog.getText().toString().isEmpty())
        {
            usernamelog.setError("Please enter mobile number");
            usernamelog.requestFocus();
        }
        else if (passlog.getText().toString().isEmpty())
        {
            passlog.setError("Please enter password");
            passlog.requestFocus();
        }
        else
        {
            loginUser();
        }
    }
    private void loginUser()
    {
        final String mob = usernamelog.getText().toString().trim();
        final String pass = passlog.getText().toString().trim();

        class LoginUser extends AsyncTask<Void, Void, String>
        {
            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {

                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("mobile", mob);
                params.put("password", pass);

                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressBar = (ProgressBar) findViewById(R.id.progress_bar);
                progressBar.setVisibility(View.VISIBLE);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                progressBar.setVisibility(View.GONE);

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

                        Intent verifyIntent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(verifyIntent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }

        LoginUser lu = new LoginUser();
        lu.execute();

    }
}