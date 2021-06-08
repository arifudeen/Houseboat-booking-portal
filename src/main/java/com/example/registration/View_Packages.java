package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class View_Packages extends AppCompatActivity {
    Button send, bt_test1;

    TextView txt;
    TextView txt_des;
    TextView txt_rate;
    ImageView imges;
    String p_txt, p_txt_des, p_txt_rate;
    String id, image, boat_name, packagee, desccription, rate;

    String owner_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__packages);
        send = (Button) findViewById(R.id.bt_book);
        bt_test1 = (Button) findViewById(R.id.bt_test);
        bt_test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent book_int = new Intent(View_Packages.this, Boat_Booking.class);
                book_int.putExtra("pacid", getIntent().getExtras().getInt("pacid"));
                book_int.putExtra("id", id);
                book_int.putExtra("owner_name", owner_name);
                book_int.putExtra("package", packagee);
                book_int.putExtra("boat_name", boat_name);
                startActivity(book_int);
            }
        });
        txt = (TextView) findViewById(R.id.txt_packname);
        txt_des = (TextView) findViewById(R.id.txt_description);
        txt_rate = (TextView) findViewById(R.id.txt_rate);
        imges = findViewById(R.id.iv_home);

        id = getIntent().getExtras().getString("id");
        image = getIntent().getStringExtra("image");
        boat_name = getIntent().getStringExtra("boat_name");
        packagee = getIntent().getStringExtra("package");
        desccription = getIntent().getStringExtra("desccription");
        rate = getIntent().getStringExtra("rate");

        owner_name = getIntent().getStringExtra("owner_name");

        txt.setText(packagee);
        txt_des.setText(desccription);
        txt_rate.setText(rate);


        Glide.with(this)
                .load(image)
                .into(imges);


        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                send = send.getText().toString();
                p_txt = txt.getText().toString();
                p_txt_des = txt_des.getText().toString();
                p_txt_rate = txt_rate.getText().toString();

                if (txt.getText().toString().isEmpty()) {
                    txt.setError("Please enter ");
                    txt.requestFocus();
                } else {


//                    Toast.makeText(view_pack.this, "booking successfully! ", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//
//                    startActivity(intent);

                    bookPackage();

                }
            }
        });


    }


    private void bookPackage() {

//        send = findViewById(R.id.bt_book);
//        txt = findViewById(R.id.txt_pack);
//        txt_des = findViewById(R.id.txt_desc);
//        txt_rate = findViewById(R.id.txt_rate);

//        send = send.getText().toString();
        p_txt = txt.getText().toString();
        p_txt_des = txt_des.getText().toString();
        p_txt_rate = txt_rate.getText().toString();

        class BookPackage extends AsyncTask<Void, Void, String> {


            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("package", p_txt);
                params.put("description", p_txt_des);
                params.put("rate", p_txt_rate);


                return requestHandler.sendPostRequest(URLs.URL_PACKBOOK, params);
            }


            @Override
            protected void onPreExecute() {
                super.onPreExecute();


            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);


                try {
                    JSONObject obj = new JSONObject(s);

                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONObject userJson = obj.getJSONObject("user");

                        Tourist tourist = new Tourist(
                                userJson.getInt("id"),
                                userJson.getString("package"),
                                userJson.getString("description")


                        );

                        SharedPrefManager.getInstance(getApplicationContext()).touristLogin(tourist);

                        Intent verifyIntent = new Intent(View_Packages.this, Boat_Booking.class);
                        startActivity(verifyIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "waiting for admin request.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        BookPackage ru = new BookPackage();
        ru.execute();
    }

}