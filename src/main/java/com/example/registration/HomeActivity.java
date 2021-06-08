package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.os.Handler;

import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textview.MaterialTextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    List<Homelist> homelists;
    RecyclerView recyclerView;
    private NavigationView nv;
    private DrawerLayout dl;
    private Toolbar tb;

    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    MaterialTextView title;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nv = findViewById(R.id.nav);
        dl = findViewById(R.id.drawer);
        tb = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.toolbar_title);
        searchView = findViewById(R.id.search_view);

        Objects.requireNonNull(getSupportActionBar()).hide();

        toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.drawerOpen, R.string.drawerClose);
        toggle.syncState();

        searchView.setOnSearchClickListener(v -> title.setVisibility(View.INVISIBLE));

        searchView.setOnCloseListener(() -> {
            title.setVisibility(View.VISIBLE);
            return false;
        });


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {

                    case R.id.complints:
                        Intent cbook = new Intent(HomeActivity.this, complient.class);
                        startActivity(cbook);
                        return true;

                    case R.id.raten:
                        Intent main = new Intent(HomeActivity.this, RatingStar.class);
                        startActivity(main);
                        finish();

                        return true;

                    case R.id.packges:
                        Intent pac = new Intent(HomeActivity.this, Packages.class);
                        startActivity(pac);
                        finish();
                        return true;

                    case R.id.stat:
                        startActivity(new Intent(HomeActivity.this,View_Status.class));
                        finish();
                        return true;

                    case R.id.about:
                        Intent a = new Intent(HomeActivity.this, About.class);
                        startActivity(a);
                        return true;

                    case R.id.logout:
                        finishAffinity();
                        SharedPrefManager.getInstance(getApplicationContext()).logout();

                }

                return true;
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home_nav);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile_nav:
                        startActivity(new Intent(getApplicationContext(), complient.class));
                        overridePendingTransition(0, 0);
                        return true;



                    case R.id.home_nav:
                        return true;


                    case R.id.raten:
                        startActivity(new Intent(getApplicationContext(), RatingStar.class));
                        return true;


                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), Boat_Booking.class));
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.sign_out:
                        SharedPrefManager.getInstance(getApplicationContext()).logout();
                        return true;
                }
                return false;
            }
        });
        recyclerView = findViewById(R.id.rv_home);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));


        searchView.setOnQueryTextListener(searchQueryListener);
        homelists = new ArrayList<>();
        loadHome();

    }


    androidx.appcompat.widget.SearchView.OnQueryTextListener searchQueryListener = new
            androidx.appcompat.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchView.clearFocus();
                    searchHome(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText.isEmpty())
                        loadHome();
                    return false;
                }
            };


    private void searchHome(String keyword) {
        Log.d("HOMEEEEE", keyword);
//        homelists.clear();
        class LoadHome extends AsyncTask<Void, Void, String> {
            ProgressBar progressBar = findViewById(R.id.prog_bar_home);

            @Override
            protected String doInBackground(Void... voids) {

                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("package", keyword);

                return requestHandler.sendPostRequest(URLs.URL_SEARCH_HOME, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONArray array = new JSONArray(s);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject users = array.getJSONObject(i);

                        homelists.add(new Homelist(
                                users.getInt("pid"),
                                users.getString("boatname"),
                                users.getString("image"),
                                users.getString("package"),
                                users.getString("description"),
                                users.getString("rate"),
                                ""));


                    }

                    HomelistAdapter adapter = new HomelistAdapter(HomeActivity.this, homelists);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        LoadHome lc = new LoadHome();
        lc.execute();

    }

    private void loadHome() {
        homelists.clear();
        class LoadHome extends AsyncTask<Void, Void, String> {
            ProgressBar progressBar = findViewById(R.id.prog_bar_home);

            @Override
            protected String doInBackground(Void... voids) {

                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();


                return requestHandler.sendPostRequest(URLs.URL_VIEWHOME, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONArray array = new JSONArray(s);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject users = array.getJSONObject(i);

                        homelists.add(new Homelist(
                                users.getInt("pid"),
                                users.getString("boatname"),
                                users.getString("image"),
                                users.getString("package"),
                                users.getString("description"),
                                users.getString("rate"),
                                users.getString("owner_name")
                        ));


                    }

                    HomelistAdapter adapter = new HomelistAdapter(HomeActivity.this, homelists);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        LoadHome lc = new LoadHome();
        lc.execute();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}