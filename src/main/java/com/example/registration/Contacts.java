package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contacts extends AppCompatActivity {
    ListView l;
    SharedPreferences sh;
    String url;
    ArrayList<String> name,emno,lati,longi,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l=(ListView)findViewById(R.id.list2);



//        url ="http://"+sh.getString("ip", "") + ":5000/viewcontacts";
//        RequestQueue queue = Volley.newRequestQueue(Contacts.this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                // Display the response string.
//                Log.d("+++++++++++++++++",response);
//                try {
//                    JSONArray ar=new JSONArray(response);
//                    name= new ArrayList<>();
//                    emno= new ArrayList<>();
//                    lati= new ArrayList<>();
//                    longi= new ArrayList<>();
//
//                    type= new ArrayList<>();
//
//
//                    for(int i=0;i<ar.length();i++)
//                    {
//                        JSONObject jo=ar.getJSONObject(i);
//                        name.add(jo.getString("name")+"("+jo.getString("type")+")");
//                        type.add(jo.getString("type"));
//
//                        emno.add(jo.getString("emergencyno"));
//                        lati.add(jo.getString("latitude"));
//                        longi.add(jo.getString("longitude"));
//
//
//                    }

//
//                    l.setAdapter(new contactcustom(Contacts.this,name,emno,lati,longi));
//
//
//
//
//                } catch (Exception e) {
//                    Toast.makeText(Contacts.this, "exp"+e, Toast.LENGTH_SHORT).show();
//
//                    Log.d("=========", e.toString());
//                }
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(Contacts.this, "err"+error, Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("lati",LocationService.lati);
//                params.put("longi",LocationService.logi);
//
//
//                return params;
//            }
//        };
//        queue.add(stringRequest);

 }
}