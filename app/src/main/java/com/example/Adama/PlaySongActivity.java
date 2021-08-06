package com.example.Adama;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;

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
    Button shuffleBtn;
    Boolean repeatFlag = false;
    Boolean shuffleFlag = false;


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
            btnPlayPause.setText("PAUSE");
            setTitle(title);
            gracefullyStopsWhenMusicEnds();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void playOrPauseMusic(View view) {
        if(player.isPlaying()){
            player.pause();
            seekBarController.setMax(player.getDuration());
            handler.removeCallbacks(bar);
            handler.postDelayed(bar,1000);
            btnPlayPause.setText("PLAY");
        }else{
            player.start();
            seekBarController.setMax(player.getDuration());
            handler.removeCallbacks(bar);
            handler.postDelayed(bar,1000);
            btnPlayPause.setText("PAUSE");
        }
    }
    private void gracefullyStopsWhenMusicEnds() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(PlaySongActivity.this, "Song ended", Toast.LENGTH_SHORT).show();
                btnPlayPause.setText("PLAY");

            }
        });
    }
    public void playNext (View view){
        currentIndex = songCollection.getNextSong(currentIndex);
        Toast.makeText(this,"after clicking playnext, \n the current index of this song\n"+
                "in the SongCollecton Array is now: " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playNext, the index is now : "+ currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(filelink);

    }

    public void playPrevious (View view) {
        currentIndex = songCollection.getPrevSong(currentIndex);
        Toast.makeText(this, "after clicking playPrevious, \n the current index of this song\n" +
                "in the SongCollecton array is now: " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playPrevious, the index is now : " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(filelink);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(bar);
        player.release();
    }


    public void addToPlaylist(View view) {
        String songID = view.getContentDescription().toString();
        int song = songCollection.searchSongById(songID);
        Toast.makeText(this,"love is war",Toast.LENGTH_SHORT).show();
        playList.add(song);
    }


    public void repeatSong(View view) {
        if (repeatFlag) {
            repeatBtn.setBackgroundResource(R.drawable.repeat_off);


        } else {

            repeatBtn.setBackgroundResource(R.drawable.repeat_on);

        }
        repeatFlag=!repeatFlag;

    }
}
