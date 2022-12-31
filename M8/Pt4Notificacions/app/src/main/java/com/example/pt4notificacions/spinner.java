package com.example.pt4notificacions;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class spinner extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = "spinner";
    Button añadir;
    String posicion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        añadir = findViewById(R.id.Añadir);
        añadir.setOnClickListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Opciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        posicion = (String) parent.getItemAtPosition(pos);
        String mensaje = (String) parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {

        String channelId = getString(R.string.default_notification_channel_id);
        String channelName = getString(R.string.default_notification_channel_name);
        switch (posicion) {
            case "Administració i Gestió":
                channelId = getString(R.string.default_notification_channel_Admin);
                break;
            case "Comerç i Màrqueting":
                channelId = getString(R.string.default_notification_channel_Marqueting);
                break;
            case "Informàtica i Comunicacions":
                channelId = getString(R.string.default_notification_channel_Info);
                break;
            case "Serveis a la Comunitat":
                channelId = getString(R.string.default_notification_channel_Service);
                break;
        }
        NotificationManager notificationManager =
                getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_LOW));
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.default_notification_channel_name))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscrit);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscrit_error);
                        }
                        Toast.makeText(spinner.this, msg+": "+posicion, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

