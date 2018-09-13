package com.example.android.qplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumAdapter extends ArrayAdapter<Song> {
    public AlbumAdapter(Activity context, ArrayList<Song> songs) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list_item.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_1, parent, false);
        }

        // Get the {@link Song} object located at this position in the list_item
        Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID song_title_text_view
        TextView songTitleTextView = (TextView) listItemView.findViewById(R.id.heading_text_view);
        // Get the version name from the current Song object and
        // set this text on the name TextView
        songTitleTextView.setText(currentSong.getSource());

        // Find the TextView in the list_item.xml layout with the ID artist_name_text_view
        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.sub_heading_text_view);
        // Get the version number from the current Song object and
        // set this text on the number TextView
        artistNameTextView.setText(currentSong.getArtistName());

        // Return the whole list_item item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}


