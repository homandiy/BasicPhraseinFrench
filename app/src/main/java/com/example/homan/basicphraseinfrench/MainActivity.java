package com.example.homan.basicphraseinfrench;

import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public void buttonTapped(View view) {
        //button id
        int id = view.getId();
        //get the button
        Button x = (Button) findViewById(id);
        //my tag stores the filename so get tag
        String ourId = String.valueOf(x.getTag());
        Log.i("button: ", ourId);

        int resourceId = getResources().getIdentifier(ourId, "raw", getPackageName());
        MediaPlayer mPlayer = MediaPlayer.create(this, resourceId);
        mPlayer.start();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mPlayer) {
                mPlayer.stop();
                if (mPlayer != null) {
                    mPlayer.reset();
                    mPlayer.release();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //read files form assets folder
        String[] musicList = new String[0];
        AssetManager assetManager = getAssets();

        try {
            musicList = assetManager.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String filename : musicList){
            if (filename.endsWith("m4a"))
            Log.i("music file with *.m4a:", filename);
        }

    }


}
