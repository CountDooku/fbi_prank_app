package io.github.countdooku.fbi_prank_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.os.Vibrator;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                MediaPlayer splashSound = MediaPlayer.create(MainActivity.this, R.raw.alert);

                splashSound.start();

                splashSound.setOnCompletionListener(new
                                                            MediaPlayer.OnCompletionListener() {
                                                                @Override
                                                                public void onCompletion(MediaPlayer splashSound) {

                        splashSound.stop();
                        splashSound.release();

                        //start next Activity...
                        Intent mainIntent = new Intent(MainActivity.this, MenuActivity.class);
                        MainActivity.this.startActivity(mainIntent);
                        MainActivity.this.finish();

                    }
                });

                //vibrate perpetually : until loaded (6 seconds)
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                long[] pattern = {0, 6000};
                v.vibrate(pattern, -1);


            }
        }, 6000);

    }

}