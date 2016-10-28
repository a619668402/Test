package com.luxshare.testkeeplive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luxshare.testkeeplive.service.LocalService;
import com.luxshare.testkeeplive.service.RemoteService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, LocalService.class));
        startService(new Intent(MainActivity.this, RemoteService.class));
    }


}
