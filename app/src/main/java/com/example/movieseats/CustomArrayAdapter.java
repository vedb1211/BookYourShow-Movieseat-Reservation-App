package com.example.movieseats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

class CustomArrayAdapter extends ArrayAdapter<CustomList> {


    public CustomArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CustomList> customLists ){
        super(context, resource, customLists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.single_items,parent,false);
        }

        CustomList currentItem = getItem(position);
        ImageView imageView = listItemView.findViewById(R.id.imageView);
        imageView.setImageResource(currentItem.getmImgResId());
        TextView textView1 = listItemView.findViewById(R.id.movies_name);
        textView1.setText(currentItem.getmMovieName());
        TextView textView2 = listItemView.findViewById(R.id.movie_rating);
        textView2.setText(currentItem.getmMovieRating());

        return listItemView;



    }
}
