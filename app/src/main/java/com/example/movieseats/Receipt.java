package com.example.movieseats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Receipt extends AppCompatActivity implements View.OnClickListener {

    private TextView name,seatno,screen;

    private FirebaseAuth mAuth;
    private Button button,btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        TextView textView2 = findViewById(R.id.seatno);

        Bundle bundle = getIntent().getExtras();
        final String movieTitle = bundle.getString("MovieTitle1");
        final String SelectedButtons = bundle.getString("SelectedSeatsList");
        final ArrayList<Integer> myList = (ArrayList<Integer>) getIntent().getSerializableExtra("selectedSeatsList");


        String seats = "";

        for (int i = 0; i < myList.size(); i++){
            seats = new StringBuilder().append(seats).append("S").append(myList.get(i)).append(" ").toString();
        }

        textView2.setText("Seats : "+seats);
        button = findViewById(R.id.btnbookAnother);
        btnLogout = findViewById(R.id.btnLogout);

        button.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

    }

    public void onClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.btnbookAnother:
                startActivity(new Intent(Receipt.this, MainActivity.class));
                break;
            case R.id.btnLogout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Receipt.this, Login.class));
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}















