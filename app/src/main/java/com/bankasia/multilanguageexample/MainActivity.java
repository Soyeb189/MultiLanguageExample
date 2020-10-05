package com.bankasia.multilanguageexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Locale localeToSwitchTo;
    private Button buttonBn,buttonEn;
    private String local="en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBn = findViewById(R.id.button2);
        buttonEn = findViewById(R.id.button);



        buttonEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                local = "en";
                setLocal("en");
                ContextUtils.updateLocale(MainActivity.this,localeToSwitchTo);
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        buttonBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                local = "bn";
                localeToSwitchTo = new Locale(local);
                setLocal("bn");
                ContextUtils.updateLocale(MainActivity.this,localeToSwitchTo);
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


    }

    private void setLocal(String lang){
            localeToSwitchTo = new Locale(lang);
            Locale.setDefault(localeToSwitchTo);
        Configuration config = new Configuration();
        config.locale = localeToSwitchTo;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
    }
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        localeToSwitchTo = new Locale(local);
//        ContextWrapper localeUpdatedContext = ContextUtils.updateLocale(newBase, localeToSwitchTo);
//        super.attachBaseContext(localeUpdatedContext);
//    }


}