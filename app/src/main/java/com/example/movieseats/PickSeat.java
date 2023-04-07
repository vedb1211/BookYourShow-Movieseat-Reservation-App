package com.example.movieseats;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

// This activity is called when user selects a movie and proceed to select seats
public class PickSeat extends AppCompatActivity {

    DatabaseReference databasemovie;
    Boolean[] btnSelected = new Boolean[40]; // array to store selected or not selected status of each seat
    int noOfSelectedSeats;

    private List<String> bookedSeatsList = new ArrayList<>();

    private List<String> bookedSeats = new ArrayList<>();


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        final String movieTitle = bundle.getString("MovieTitle");
        final String Image = bundle.getString("Image");
        System.out.println("Movie Name = "+movieTitle);

        noOfSelectedSeats = 0;

        // get ids of booked seat buttons from database

        readData(movieTitle,new FirebaseCallback() {
            @Override
            public void OnCallback(List<String> list) {
                System.out.println("Veddd"+bookedSeatsList);

                Button[] seatButtons = new Button[40];
                final ArrayList<Integer> selectedButtons = new ArrayList<Integer>();


                System.out.println("hellllooo"+bookedSeats);
//
                // if there are some seats already booked
                if (bookedSeatsList != null) {
                    Log.d("BookedSeatNo",  " ----  " + bookedSeatsList.size());
                    for (int i = 0; i < bookedSeatsList.size(); i++) {
                        String buttonID = "btn" + bookedSeatsList.get(i);
                        Log.d("BookedSeatNo", buttonID + " ----  " + bookedSeatsList.get(i));
                        int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                        Button btn = (Button) (findViewById(resID));
                        btn.setBackground(getResources().getDrawable(R.drawable.seatbooked));
                        btn.setEnabled(false);
                    }

                }
                // set btnselected flag as false for all buttons
                for (int i = 0; i < 40; i++) {
                    btnSelected[i] = false;
                }
                //
                for (int i = 1; i < seatButtons.length; i++) {

                    String buttonID = "btn" + i;

                    int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                    Log.d("buttonid", "idd---" + buttonID);
                    Log.d("resId", "idd---" + resID);

                    seatButtons[i] = ((Button) findViewById(resID));
                    final int finalId = resID;
                    final int buttonNum = i;
//
                    // set listeners to all buttons
                    if (seatButtons[i].isEnabled()) {

                        seatButtons[i].setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                Button btn = (Button) findViewById(finalId);
                                if (!btnSelected[buttonNum]) {
                                    btn.setBackground(getResources().getDrawable(R.drawable.seatselected));
                                    btnSelected[buttonNum] = true;
                                    noOfSelectedSeats = noOfSelectedSeats + 1;
                                    selectedButtons.add(buttonNum);
                                    bookedSeatsList.add(String.valueOf(buttonNum));
                                    System.out.println("Booked"+bookedSeats);


                                } else {
                                    btn.setBackground(getResources().getDrawable(R.drawable.available));
                                    btnSelected[buttonNum] = false;
                                    noOfSelectedSeats = noOfSelectedSeats - 1;

                                    try {
                                        selectedButtons.remove(buttonNum);
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                }
                            }
                        });
                    }
                }

                // go to Receipt page after selecting seats and confirming seats

                Button btnProceed = (Button) findViewById(R.id.btnConfirmSeats);

                btnProceed.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        for (int i = 0; i < selectedButtons.size(); i++) {
                            System.out.println("Seat no "+selectedButtons.get(i));
                            bookedSeatsList.add(String.valueOf(selectedButtons.get(i)));
                            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("MovieDetails").child(movieTitle);
                            String key = dbref.push().getKey();
                            String value = selectedButtons.get(i).toString();
                            dbref.child(key).setValue(value);
                        }

                        Intent myintent = new Intent(PickSeat.this, Receipt.class);
                        myintent.putExtra("selectedSeatsList",selectedButtons);
                        myintent.putExtra("movieTitle",movieTitle);
                        myintent.putExtra("Image",Image);

                        startActivity(myintent);
                    }
                });
            }
            });
     }
    private void readData(String movieTitle, FirebaseCallback firebaseCallback){

        DatabaseReference databaseMovie = FirebaseDatabase.getInstance().getReference().child("MovieDetails").child(movieTitle);

        databaseMovie.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot seatSnapshot : dataSnapshot.getChildren()) {
                    System.out.println(seatSnapshot.getKey());
                    String bookedSeat = seatSnapshot.getValue(String.class);
                    bookedSeatsList.add(bookedSeat);
                }
                System.out.println(bookedSeatsList);
                // Remove duplicates from the list of booked seats
                Set<String> uniqueBookedSeats = new LinkedHashSet<>(bookedSeatsList);
                System.out.println(uniqueBookedSeats);
                bookedSeatsList.clear();
                System.out.println(bookedSeatsList);
                bookedSeatsList.addAll(uniqueBookedSeats);
                System.out.println(bookedSeatsList);

                firebaseCallback.OnCallback(bookedSeatsList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Error getting booked seats for movie: " , databaseError.toException());
            }
        });
    }
    private interface FirebaseCallback{
        void OnCallback(List<String> list);
    }

}