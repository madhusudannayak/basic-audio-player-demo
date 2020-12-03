package com.example.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager=(AudioManager) getSystemService(AUDIO_SERVICE);

        int volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int currentvolume =audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        mediaPlayer =MediaPlayer.create(this,R.raw.audio);

        seekBar=findViewById(R.id.volume);
        seekBar.setMax(volume);
        seekBar.setProgress(currentvolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar audio =findViewById(R.id.audio);
        audio.setMax(mediaPlayer.getDuration());
        audio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                audio.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,300);

    }

    public void play(View view) {
        mediaPlayer.start();
    }

    public void pause(View view) {
        mediaPlayer.pause();
    }
}