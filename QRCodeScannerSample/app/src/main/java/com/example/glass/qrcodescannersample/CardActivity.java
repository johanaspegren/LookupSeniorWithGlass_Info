package com.example.glass.qrcodescannersample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CardActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvRemember;
    TextView tvTimefor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        tvName = findViewById(R.id.textView2);
        tvRemember = findViewById(R.id.tvRemember);
        tvTimefor = findViewById(R.id.tvTimefor);

        tvName.setText("Greta");
        tvRemember.setText("Glöm inte att Greta har haft ont i benen");
        tvTimefor.setText("Meka en macka");
    }
}