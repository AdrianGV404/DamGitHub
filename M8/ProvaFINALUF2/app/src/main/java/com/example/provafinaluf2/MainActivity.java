package com.example.provafinaluf2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    EditText Titol;
    EditText Genere;
    EditText Durada;
    EditText Nota;

    Button btnAlta;
    Button btnBaja;
    Button btnConsulta;
    Button btnModificar;

    ListView listPelis;

    TextView Consult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PelisDbHelper dbHelper = new PelisDbHelper(MainActivity.this);

        Titol = findViewById(R.id.titol);
        Genere = findViewById(R.id.genere);
        Durada = findViewById(R.id.durada);
        Nota = findViewById(R.id.nota);

        Consult = findViewById(R.id.Consult);

        listPelis = findViewById(R.id.listPelis);


        btnAlta = findViewById(R.id.btnAlta);
        btnBaja = findViewById(R.id.btnBaja);
        btnConsulta = findViewById(R.id.btnConsulta);
        btnModificar = findViewById(R.id.btnModificar);

        final List<Pelis> pelisList = dbHelper.getAllPelis();

        HashMap<String, String> hashMap;
        final ArrayList<HashMap<String, String>> peliArray = new ArrayList<HashMap<String, String>>();

        for (Pelis p : pelisList) {
            hashMap = new HashMap<String, String>();
            hashMap.put("titol", p.getTitol());
            hashMap.put("genere", p.getGenere());
            hashMap.put("durada", p.getDurada());
            hashMap.put("nota", p.getNota());
            peliArray.add(hashMap);

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this,
                    peliArray,
                    R.layout.llista,
                    new String[]{"titol", "genere", "durada", "nota"},
                    new int[]{R.id.titol, R.id.genere, R.id.durada, R.id.nota}
            );
            listPelis.setAdapter(adapter);
        }

        btnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Titol.getText().toString().equals("") || !Genere.getText().toString().equals("")|| !Durada.getText().toString().equals("")|| !Nota.getText().toString().equals("")){
                    if (dbHelper.insertarPeli(new Pelis(Titol.getText().toString(), Genere.getText().toString(), Durada.getText().toString(), Nota.getText().toString()))) {
                    final List<Pelis> pelisList = dbHelper.getAllPelis();

                    HashMap<String, String> hashMap;
                    final ArrayList<HashMap<String, String>> peliArray = new ArrayList<HashMap<String, String>>();

                    for (Pelis p : pelisList) {
                        hashMap = new HashMap<String, String>();
                        hashMap.put("titol", p.getTitol());
                        hashMap.put("genere", p.getGenere());
                        hashMap.put("durada", p.getDurada());
                        hashMap.put("nota", p.getNota());
                        peliArray.add(hashMap);

                        createNotificationChannel();
                        createNotification(p);

                        ListAdapter adapter = new SimpleAdapter(
                                MainActivity.this,
                                peliArray,
                                R.layout.llista,
                                new String[]{"titol", "genere", "durada", "nota"},
                                new int[]{R.id.titol, R.id.genere, R.id.durada, R.id.nota}
                        );
                        listPelis.setAdapter(adapter);
                    }

                    Titol.setText("");
                    Genere.setText("");
                    Durada.setText("");
                    Nota.setText("");
                } else {
                    Titol.setText("");
                    Genere.setText("");
                    Durada.setText("");
                    Nota.setText("");
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Peli ya existente", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            } else {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Campos vacíos", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });

        btnBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deletePeli(String.valueOf(Titol.getText()));
                final List<Pelis> pelisList = dbHelper.getAllPelis();

                HashMap<String, String> hashMap;
                final ArrayList<HashMap<String, String>> peliArray = new ArrayList<HashMap<String, String>>();

                for (Pelis p : pelisList) {
                    hashMap = new HashMap<String, String>();
                    hashMap.put("titol", p.getTitol());
                    hashMap.put("genere", p.getGenere());
                    hashMap.put("durada", p.getDurada());
                    hashMap.put("nota", p.getNota());
                    peliArray.add(hashMap);

                    ListAdapter adapter = new SimpleAdapter(
                            MainActivity.this,
                            peliArray,
                            R.layout.llista,
                            new String[]{"titol", "genere", "durada", "nota"},
                            new int[]{R.id.titol, R.id.genere, R.id.durada, R.id.nota}
                    );
                    listPelis.setAdapter(adapter);
                }
            }
        });

        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Peli = dbHelper.consultarPeli(String.valueOf(Genere.getText()));
                Consult.setText(Peli);
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Titol.getText().toString().equals("") && !Genere.getText().toString().equals("") && !Durada.getText().toString().equals("") && !Nota.getText().toString().equals("")){

                 System.out.println("CAMPOS LLENOOOSOOS");

                dbHelper.modificarPeli(String.valueOf(Titol.getText()), String.valueOf(Genere.getText()), String.valueOf(Durada.getText()), String.valueOf(Nota.getText()));

                final List<Pelis> pelisList = dbHelper.getAllPelis();

                HashMap<String, String> hashMap;
                final ArrayList<HashMap<String, String>> peliArray = new ArrayList<HashMap<String, String>>();


                for (Pelis p : pelisList) {
                    hashMap = new HashMap<String, String>();
                    hashMap.put("titol", p.getTitol());
                    hashMap.put("genere", p.getGenere());
                    hashMap.put("durada", p.getDurada());
                    hashMap.put("nota", p.getNota());
                    peliArray.add(hashMap);

                    ListAdapter adapter = new SimpleAdapter(
                            MainActivity.this,
                            peliArray,
                            R.layout.llista,
                            new String[]{"titol", "genere", "durada", "nota"},
                            new int[]{R.id.titol, R.id.genere, R.id.durada, R.id.nota}
                    );
                    listPelis.setAdapter(adapter);
                }
                } else{
                    System.out.println("CAMPOS VACIOOOOS");
                    Toast toast2 = Toast.makeText(getApplicationContext(), "Campos Vacios", Toast.LENGTH_SHORT);
                }
            }
        });
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CANAL";
            String description = "NOTIFICACIÓN";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void createNotification(Pelis pelicula) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(android.R.drawable.ic_menu_today)
                .setContentTitle("TITOL: "+pelicula.getTitol())
                .setContentText("Genere: "+pelicula.getGenere())
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
    }

}
