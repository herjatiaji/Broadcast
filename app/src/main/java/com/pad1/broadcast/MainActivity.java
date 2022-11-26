package com.pad1.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button send_btn;
    private customReceiver bReceiver = new customReceiver();
    //membuat identifier supaya berbeda dengan broadcast yang lain
    private final static String ACTION_CUSTOM_BROADCAST = "action-custom-broadcast";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_btn = findViewById(R.id.send_btn);
        //Check ada event apa saja
        //attach filter
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        //Jangan lupa semua harus diregister
        this.registerReceiver(bReceiver, filter);
        this.registerCustomBroadcast();

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast();
            }
        });





    }
    private void sendBroadcast(){
        //Pemasangan identifier
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        //Pengiriman Broadcast
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
    private void registerCustomBroadcast(){
        //Pembuatan filter dan receiver custom
        IntentFilter filter = new IntentFilter(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(bReceiver, filter);
    }
}