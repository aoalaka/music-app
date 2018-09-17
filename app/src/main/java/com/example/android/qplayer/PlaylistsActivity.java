package com.example.android.qplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlaylistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);

        Button backToSongsListBtn = (Button) findViewById(R.id.back_to_home);
        backToSongsListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //The code in this method will be executed when the albums View is clicked on.
                Intent backToSongsListIntent = new Intent(PlaylistsActivity.this, MainActivity.class);
                startActivity(backToSongsListIntent);
            }
        });


    }
}
