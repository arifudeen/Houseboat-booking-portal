package com.example.registration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Boat_Booking extends AppCompatActivity {
    private int mdate, mmonth, myear;
    final Calendar myCalendar = Calendar.getInstance();
    EditText name_b, b_date, c_date, pay;
    String name_b1, b_date1, c_date1, pay1, payment_mode;
    RadioGroup payment;
    Button pros;
    int tid;
    ImageView imageView;
    String image;

    String owner_name;
    String boat_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat__booking);

        owner_name = getIntent().getStringExtra("owner_name");
        boat_name = getIntent().getStringExtra("boat_name");


        Tourist tourist = SharedPrefManager.getInstance(this).getUser();
        tid = tourist.getTid();
        name_b = findViewById(R.id.b_name);
        b_date = findViewById(R.id.b_date);
        c_date = findViewById(R.id.c_date);
        pay = findViewById(R.id.pay);
        payment = findViewById(R.id.radiogroup_pay);
        imageView = findViewById(R.id.image);

        pros = findViewById(R.id.bt_register);
        pros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayofmonth);
                String Myformat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(Myformat, Locale.US);
                c_date.setText(sdf.format(myCalendar.getTime()));


            }
        };

        findViewById(R.id.select_image).setOnClickListener(v -> {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        });

        c_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Boat_Booking.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                // String Myformat = "dd MMMM YYYY";
                // SimpleDateFormat sdf = new SimpleDateFormat(Myformat, Locale.US);
                // s_start.setText(sdf.format(myCalendar.getTime()));


            }
        });


        //date picker dialogue
        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayofmonth);
                String Myformat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(Myformat, Locale.US);
                b_date.setText(sdf.format(myCalendar.getTime()));


            }
        };
        b_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Boat_Booking.this, date2, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                // String Myformat = "dd MMMM YYYY";
                // SimpleDateFormat sdf = new SimpleDateFormat(Myformat, Locale.US);
                // s_start.setText(sdf.format(myCalendar.getTime()));


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageView.setImageURI(resultUri);
                try {
                    getBitmap(resultUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void getBitmap(Uri resultUri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), resultUri);
        image = getStringImage(bitmap);
        Log.d("TESTING", image);

    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }


    private void validate() {
       /* if (image.isEmpty()) {
            Toast.makeText(this, "image is empty", Toast.LENGTH_SHORT).show();
            return;
        }*/
        if (name_b.getText().toString().isEmpty()) {
            name_b.setError("Please enter fullname.");
            name_b.requestFocus();
        } else if (b_date.getText().toString().isEmpty()) {
            b_date.setError("Please enter Date.");
            b_date.requestFocus();
        } else {
            process();
        }
    }

    private void process() {
        name_b1 = name_b.getText().toString().trim();
        b_date1 = b_date.getText().toString().trim();
        c_date1 = c_date.getText().toString().trim();
        pay1 = pay.getText().toString().trim();
        if (payment.getCheckedRadioButtonId() == R.id.rad_upi) {
            payment_mode = "UPI";
        }
        if (payment.getCheckedRadioButtonId() == R.id.rad_bank) {
            payment_mode = "Bank Transfer";
        }

        class process extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
//                params.put("boat_name",boatName);
                params.put("t_name", name_b1);
                params.put("bdate", b_date1);
                params.put("cdate", c_date1);
                params.put("pay", pay1);
                params.put("pay_mode", payment_mode);
                params.put("image", image);
                params.put("owner_name", owner_name);
                params.put("boatname", boat_name);
                params.put("pacid", String.valueOf(getIntent().getExtras().getInt("pacid")));

                return requestHandler.sendPostRequest(URLs.URL_BOOKINGTOUR, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    Toast.makeText(Boat_Booking.this, s, Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(s);
                    if (!jsonObject.getBoolean("error")) {
                        Toast.makeText(Boat_Booking.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent corderIntent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(corderIntent);
                    }
                    else
                    {
                        Toast.makeText(Boat_Booking.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        process as = new process();
        as.execute();
    }
}