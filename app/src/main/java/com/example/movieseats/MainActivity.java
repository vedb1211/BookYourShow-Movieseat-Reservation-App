package com.example.movieseats;


//import static com.example.booking.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

//    private FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView main_listview = findViewById(R.id.main_ListView);

        ArrayList<CustomList> moviesList = new ArrayList<>();
        CustomArrayAdapter arrayAdapter = new CustomArrayAdapter(this, 0, moviesList);
        main_listview.setAdapter((arrayAdapter));

        moviesList.add(new CustomList(R.drawable.avatar, "Avatar", "English - (U/A)"));
        moviesList.add(new CustomList(R.drawable.pathaan, "Pathaan", "Hindi - (U)"));
        moviesList.add(new CustomList(R.drawable.johnwick4, "John Wick", "English - (U/A)"));
        moviesList.add(new CustomList(R.drawable.fastx, "Fast X", "English - (U/A)"));



        main_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CustomList list = moviesList.get(position);
                Intent intent = new Intent(MainActivity.this, PickSeat.class);
                intent.putExtra("MovieTitle",list.getmMovieName());
                intent.putExtra("Image",list.getmImgResId());

                startActivity(intent);
            }
        }) ;
    };
}



