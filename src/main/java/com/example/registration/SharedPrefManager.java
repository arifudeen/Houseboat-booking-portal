package com.example.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {

        private static final String SHARED_PREF_NAME = "touristsharedpref";
        private static final String KEY_NAME = "keyname";
        private static final String KEY_MOBILE = "keymobile";
        private static final String KEY_TOID = "keytoid";

        private static SharedPrefManager mInstance;
        private static Context mCtx;

        private SharedPrefManager(Context context)
        {
            mCtx = context;
        }

        public static synchronized SharedPrefManager getInstance(Context context)
        {
            if (mInstance == null)
            {
                mInstance = new SharedPrefManager(context);
            }

            return mInstance;
        }

        public void touristLogin(Tourist tourist)
        {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(KEY_TOID, tourist.getTid());
            editor.putString(KEY_NAME, tourist.getName());
            editor.putString(KEY_MOBILE, tourist.getMobile());

            editor.apply();
        }

        public boolean isLoggedIn()
        {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_NAME,null) != null;
        }

        public Tourist getUser()
        {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
            return new Tourist(
                    sharedPreferences.getInt(KEY_TOID,-1),
                    sharedPreferences.getString(KEY_NAME,null),
                    sharedPreferences.getString(KEY_MOBILE,null)



                    );
        }

        public void logout()
        {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
        }


    }
