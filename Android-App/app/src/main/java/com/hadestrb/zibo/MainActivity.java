package com.hadestrb.zibo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.hadestrb.zibo.Model.Schedule.Schedule;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    View decorView;
    VideoView videoView;
    MediaController mediaController;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInterface();
        setContentView(R.layout.activity_main);


        videoView = findViewById(R.id.playerView);
        mediaController = new MediaController(this);

        Gson gson = new Gson();

        Intent intent = getIntent();
        if(intent.getStringExtra("todt") != null) {
            int timer = Integer.parseInt(intent.getStringExtra("todt"));
            new CountDownTimer(timer, 1000) {
                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {
                    finish(); // finish Activity Main
                }
            }.start();
        }


        Schedule schedule = gson.fromJson(intent.getStringExtra("scheduleJson"), Schedule.class);
        String path = getFilePath(intent.getStringExtra("fileName"));
        Log.d("MAIN_ACT", "path:"+path);

        checkSchedule(schedule);

        videoView.setVideoURI(Uri.parse(path));
        videoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
        videoView.start();
    }

    private void initInterface() {
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(i -> {
            if (i == 0) {
                decorView.setSystemUiVisibility(hideSystemBar());
            }
        });
    }

    private String getFilePath(String fileName) {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        File file = new File(directory, fileName);
        return file.getPath();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkSchedule(Schedule schedule) {
        String todt = schedule.getFromdt();
        Thread thread = new Thread(() -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now;
            while (true) {
                now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                //check here then do activity ...
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) decorView.setSystemUiVisibility(hideSystemBar());
    }

    private int hideSystemBar() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }
}