package com.example.Adama;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {
    SongCollection songCollection = new SongCollection();
    static ArrayList<Integer> playList = new ArrayList<Integer>();
    private String title = "";
    private String artiste = "";
    private String filelink = "";
    private int drawable;
    private int currentIndex = -1;
    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    private SeekBar seekBarController;
    Handler handler = new Handler();
    Button repeatBtn;
    Runnable runnable;
    private TextView tvCurrentTime, tvTotalTime;
    Button shuffleBtn;
    Boolean repeatFlag = false;
    Boolean shuffleFlag = false;
    SongCollection originalsongCollection = new SongCollection();
    List<Song> shuffleList = Arrays.asList(songCollection.songs);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        Bundle songData = this.getIntent().getExtras();
        int currentIndex = songData.getInt("index");
        Log.d("temasek", "we are" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(filelink);

        seekBarController = findViewById(R.id.seek1);
        seekBarController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                /*if (fromUser) {
                    player.seekTo(progress);
                    tvCurrentTime.setText(getTimeFormatted(progress));
                }*/
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (player != null && player.isPlaying()) {
                    player.seekTo(seekBar.getProgress());
                }
            }
        });
        seekBarController.setMax(player.getDuration());
        handler.removeCallbacks(bar);
        handler.postDelayed(bar,1000);
        repeatBtn = findViewById(R.id.repeatBtn);
        shuffleBtn = findViewById(R.id.shuffleBtn);

    }
    Runnable bar = new Runnable() {
        @Override
        public void run() {
            if (player != null && player.isPlaying()) {
                seekBarController.setProgress(player.getCurrentPosition());
                handler.postDelayed(this, 1000);
            }
        }
    };
    public void displaySongBasedOnIndex(int selectedIndex){
        Song song = songCollection.getCurrentSong(selectedIndex);
        title = song.getTitle();
        artiste = song.getArtiste();
        filelink = song.getFilelink();
        drawable = song.getDrawable();
        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);
        TextView txtArtiste = findViewById(R.id.txtArtist);
        txtArtiste.setText(artiste);
        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        iCoverArt.setImageResource(drawable);
    }
    public void playSong(String songUrl){
        try{
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            btnPlayPause.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24);
            //playCycle();
            //seekBarController.setMax(player.getDuration());
            setTitle(title);
            /*tvTotalTime.setText(getTimeFormatted(player.getDuration()));
            if (player.isPlaying()) {
                btnPlayPause.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24);
                tvTotalTime.setText(getTimeFormatted(player.getDuration()));
            }

            seekBarController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        player.seekTo(progress);
                        tvCurrentTime.setText(getTimeFormatted(progress));
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });*/
            gracefullyStopsWhenMusicEnds();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void playOrPauseMusic(View view) {
        if(player.isPlaying()){
            seekBarController.setMax(player.getDuration());
            handler.removeCallbacks(bar);
            handler.postDelayed(bar,1000);
            player.pause();
            btnPlayPause.setBackgroundResource(R.drawable.ic_baseline_play_circle_24);
        }else{
            player.start();
            seekBarController.setMax(player.getDuration());
            handler.removeCallbacks(bar);
            handler.postDelayed(bar,1000);
            btnPlayPause.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24);
        }
    }
    private void gracefullyStopsWhenMusicEnds() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (repeatFlag){
                    playOrPauseMusic(null);
                }else{
                    btnPlayPause.setBackgroundResource(R.drawable.ic_baseline_play_circle_24);
                }
            }

        });

    }

    public void playNext (View view){
        if (repeatFlag) {
            Toast.makeText(getApplicationContext(),"Repeat is on",Toast.LENGTH_SHORT).show();
        }
        else{
                currentIndex = songCollection.getNextSong(currentIndex);
                Toast.makeText(this, "after clicking playnext, \n the current index of this song\n" +
                        "in the SongCollecton Array is now: " + currentIndex, Toast.LENGTH_LONG).show();
                Log.d("temasek", "After playNext, the index is now : " + currentIndex);
                displaySongBasedOnIndex(currentIndex);
                playSong(filelink);
                btnPlayPause.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24);
                seekBarController.setMax(player.getDuration());
                handler.removeCallbacks(bar);
                handler.postDelayed(bar, 1000);
        }

    }

    public void playPrevious (View view) {
        if (repeatFlag) {
            Toast.makeText(getApplicationContext(),"Repeat is on",Toast.LENGTH_SHORT).show();
        }
        else{
            currentIndex = songCollection.getPrevSong(currentIndex);
            Toast.makeText(this, "after clicking playPrevious, \n the current index of this song\n" +
                    "in the SongCollecton array is now: " + currentIndex, Toast.LENGTH_LONG).show();
            Log.d("temasek", "After playPrevious, the index is now : " + currentIndex);
            displaySongBasedOnIndex(currentIndex);
            playSong(filelink);
            btnPlayPause.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24);
            seekBarController.setMax(player.getDuration());
            handler.removeCallbacks(bar);
            handler.postDelayed(bar,1000);
        }

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(bar);
        player.release();
    }
    public void addToPlaylist(View view) {
        /*String songID = view.getContentDescription().toString();
        int song1 = songCollection.searchSongById(songID);
        String currentId = songCollection.getCurrentSong()
        Toast.makeText(this,"love is war",Toast.LENGTH_SHORT).show();
        playList.add(song);
        for (int i=0 ; i < playList.size();i++) {
            Log.d("temasek", playList.get(i).toString());*/
        Bundle songData = this.getIntent().getExtras();
        int currentIndex = songData.getInt("index");
        Song song = songCollection.getCurrentSong(currentIndex);
        title = song.getTitle();


        Toast.makeText(this, currentIndex + "", Toast.LENGTH_SHORT).show();
        /*currentIndex = songCollection.getPrevSong(currentIndex);
        Toast.makeText(this, currentIndex + "", Toast.LENGTH_SHORT).show();*/
        }



    public void repeatSong(View view) {
        if (repeatFlag) {
            repeatBtn.setBackgroundResource(R.drawable.ic_baseline_repeat_24);
            Toast.makeText(getApplicationContext(),"REPEAT OFF",Toast.LENGTH_SHORT).show();
        }
        else {
            repeatBtn.setBackgroundResource(R.drawable.ic_baseline_repeat_on_24);
            Toast.makeText(getApplicationContext(),"REPEAT ON",Toast.LENGTH_SHORT).show();
        }
        repeatFlag =! repeatFlag;
    }
    public void shuffleSong(View view) {
        if (shuffleFlag) {
            shuffleBtn.setBackgroundResource(R.drawable.ic_baseline_shuffle_24);
            songCollection = new SongCollection();
        }
        else {
            shuffleBtn.setBackgroundResource(R.drawable.ic_baseline_shuffle_on_24);
            Collections.shuffle(shuffleList);
            shuffleList.toArray(songCollection.songs);
        }
        shuffleFlag =! shuffleFlag;
    }
    /*private String getTimeFormatted(long milliSeconds) {
        String finalTimerString = "";
        String secondsString;

        //Converting total duration into time
        int hours = (int) (milliSeconds / 3600000);
        int minutes = (int) (milliSeconds % 3600000) / 60000;
        int seconds = (int) ((milliSeconds % 3600000) % 60000 / 1000);

        // Adding hours if any
        if (hours > 0)
            finalTimerString = hours + ":";

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10)
            secondsString = "0" + seconds;
        else
            secondsString = "" + seconds;

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // Return timer String;
        return finalTimerString;
    }
    private void playCycle() {
        try {
            seekBarController.setProgress(player.getCurrentPosition());
            tvCurrentTime.setText(getTimeFormatted(player.getCurrentPosition()));
            if (player.isPlaying()) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        playCycle();

                    }
                };
                handler.postDelayed(runnable, 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
