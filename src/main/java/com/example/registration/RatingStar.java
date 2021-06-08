package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RatingStar extends AppCompatActivity {

    Tourist tourist;
    int rating;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.simpleRatingBar);
        editText = (EditText) findViewById(R.id.feedbackEditText);
        Button submitButton = (Button) findViewById(R.id.submitButton);
        rating = simpleRatingBar.getNumStars();

        tourist = SharedPrefManager.getInstance(this).getUser();

        // perform click event on button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast

                if (editText.getText().toString().isEmpty())
                {
                    editText.setError("Enter feedback");
                    editText.requestFocus();
                }
                else
                {
                    addRating();
                }
            }
        });
    }

    private void addRating() {

        class AddRating extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {

                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("tid", String.valueOf(tourist.getTid()));
                params.put("rating", String.valueOf(rating));
                params.put("feedback", editText.getText().toString().trim());

                return requestHandler.sendPostRequest(URLs.URL_ADDRATING, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject object = new JSONObject(s);

                    if (!object.getBoolean("error"))
                    {
                        Toast.makeText(RatingStar.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RatingStar.this, HomeActivity.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(RatingStar.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        AddRating addRating = new AddRating();
        addRating.execute();
    }
}