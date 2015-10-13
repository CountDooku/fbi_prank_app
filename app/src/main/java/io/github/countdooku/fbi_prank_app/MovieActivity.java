package io.github.countdooku.fbi_prank_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;


public class MovieActivity extends AppCompatActivity {

    private final static int SELECT_VIDEO = 12346;

    //Shared Preferences specific variables to set the selected : videoView
    public static final String MyPREFERENCES = "MyPre";
    public static final String  key = "vidKey";
    SharedPreferences sharedpreferences;

    private boolean bVideoIsBeingTouched = false;
    private Handler mHandler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        //set layout to PORTRAIT mode
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //set app icon atop the ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        final VideoView videoView = (VideoView)this.findViewById(R.id.videoView);

        //onTouch : pause video, and then pick selected video
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                
                if (!bVideoIsBeingTouched) {

                    bVideoIsBeingTouched = true;

                    if (videoView.isPlaying() || videoView.isEnabled()) {

                        videoView.pause();

                        //Pick Selected Video : processed by onActivityResult()
                        Intent videoPickerIntent = new Intent(Intent.ACTION_PICK);
                        videoPickerIntent.setType("video/*");
                        startActivityForResult(videoPickerIntent, SELECT_VIDEO);

                    } else {
                        videoView.resume();
                    }

                    mHandler.postDelayed(new Runnable() {
                        public void run() {
                            bVideoIsBeingTouched = false;
                        }
                    }, 100);

                }
                return true;
            }
        });


        //Shared Preferences, initialize for the selected : videoView
        sharedpreferences =  getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(key)) {

            String u = sharedpreferences.getString(key, "");
            VideoView iv = (VideoView) findViewById(R.id.videoView);

            //Shared Preferences : parse key-value to URI && convert to String
            Uri uri = Uri.parse(u);
            String uriString = uri.toString();
            //setVideoPath to uriString
            iv.setVideoPath(uriString);
            //start it
            iv.start();

        } else {

            //set helper video by default,
            //BUT: if new video selected by end-user, return user-selected video instead
            String uri = "android.resource://" + getPackageName() + "/" + R.raw.default_vid;
            videoView.setVideoURI(Uri.parse(uri));
            videoView.start();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //resize selected video for videoView and set it into place on the UI
        if (requestCode == SELECT_VIDEO && resultCode == RESULT_OK && data != null) {
            //read selected video's Uri
            Uri pickedVideo = data.getData();
            //read and set path of selected video
            String[] filePath = { MediaStore.Video.Media.DATA};
            Cursor cursor = getContentResolver().query(pickedVideo, filePath, null, null, null);
            cursor.moveToFirst();
            String videoPath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            VideoView videoView = (VideoView)this.findViewById(R.id.videoView);
            videoView.setVideoPath(videoPath);
            videoView.start();

            //Shared Preferences specific code-bit to commit data for the selected : videoView
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key, videoPath);
            editor.commit();

            //mandatory
            cursor.close();

        }
    }

}
