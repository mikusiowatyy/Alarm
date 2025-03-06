package com.example.alarm;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCzas;
    private TimePicker timePicker;
    private Button buttonUstawAlarm;
    private Calendar czasAlarmu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewCzas = findViewById(R.id.textViewCzas);
        timePicker = findViewById(R.id.timePicker);
        buttonUstawAlarm = findViewById(R.id.buttonUstawAlarm);


        buttonUstawAlarm.setOnClickListener(v -> ustawAlarm());


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sprawdzAlarm();
                handler.postDelayed(this, 1000); // Sprawdzanie co sekundę
            }
        }, 1000);


        czasAlarmu = Calendar.getInstance();
    }


    private void ustawAlarm() {
        int godzina = timePicker.getHour();
        int minuta = timePicker.getMinute();


        czasAlarmu.set(Calendar.HOUR_OF_DAY, godzina);
        czasAlarmu.set(Calendar.MINUTE, minuta);
        czasAlarmu.set(Calendar.SECOND, 0);


        textViewCzas.setText("Alarm ustawiony na: " + String.format("%02d:%02d", godzina, minuta));
    }


    private void sprawdzAlarm() {
        Calendar teraz = Calendar.getInstance();

        if (teraz.after(czasAlarmu) && teraz.get(Calendar.SECOND) == 0) {
            textViewCzas.setText("ALARM! Czas na wstanie!");
        }

        String godzina = String.format("%02d:%02d:%02d", teraz.get(Calendar.HOUR_OF_DAY), teraz.get(Calendar.MINUTE), teraz.get(Calendar.SECOND));
        textViewCzas.setText(godzina);
    }
}