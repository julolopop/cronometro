package com.example.usuario.cronometro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ReverseCronometro cronometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cronometro = findViewById(R.id.crono);

    }

    @Override
    protected void onResume() {
        super.onResume();
        cronometro.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cronometro.stop();
    }
}
