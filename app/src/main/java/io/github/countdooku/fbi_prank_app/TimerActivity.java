package io.github.countdooku.fbi_prank_app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class TimerActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //set layout to PORTRAIT mode
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //set app icon atop the ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //button : "30 seconds..."
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(thirtySeconds);
        //button : "1 minute..."
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(oneMinute);
        //button : "2 minutes..."
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(twoMinutes);
        //button : "5 minutes..."
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(fiveMinutes);
        //button : "10 minutes..."
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(tenMinutes);
        //button : "15 minutes..."
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(fifteenMinutes);
        //button : "20 minutes..."
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(twentyMinutes);
        //button : "25 minutes..."
        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(twentyFiveMinutes);
        //button : "30 minutes..."
        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(thirtyMinutes);
        //button : "45 minutes..."
        Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(fortyFiveMinutes);
        //button : "1 hour..."
        Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(oneHour);

    }


    //restart application in 30 seconds...
    View.OnClickListener thirtySeconds = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 10 seconds
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 30000); //30,000 milliseconds == 30 seconds

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };

    //restart application in 1 minute...
    View.OnClickListener oneMinute = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 1 minute
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 60000); //60,000 milliseconds == 1 minute

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };

    //restart application in 2 minutes...
    View.OnClickListener twoMinutes = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 2 minutes
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 120000); //120,000 milliseconds == 2 minutes

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };

    //restart application in 5 minutes...
    View.OnClickListener fiveMinutes = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 5 minutes
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 300000); //300,000 milliseconds == 5 minutes

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };

    //restart application in 10 minutes...
    View.OnClickListener tenMinutes = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 10 minutes
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 600000); //600,000 milliseconds == 10 minutes

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };

    //restart application in 15 minutes...
    View.OnClickListener fifteenMinutes = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 15 minutes
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 900000); //900,000 milliseconds == 15 minutes

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };

    //restart application in 20 minutes...
    View.OnClickListener twentyMinutes = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 20 minutes
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 1200000); //1,200,000 milliseconds == 20 minutes

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };

    //restart application in 25 minutes...
    View.OnClickListener twentyFiveMinutes = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 25 minutes
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 1500000); //1,500,000 milliseconds == 25 minutes

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };

    //restart application in 30 minutes...
    View.OnClickListener thirtyMinutes = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 30 minutes
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 1800000); //1,800,000 milliseconds == 30 minutes

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };

    //restart application in 45 minutes...
    View.OnClickListener fortyFiveMinutes = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 45 minutes
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 2700000); //2,700,000 milliseconds == 45 minutes

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };

    //restart application in 1 hour...
    View.OnClickListener oneHour = new View.OnClickListener() {
        public void onClick(View v) {

            //send helpful message to end-user
            Toast.makeText(getApplicationContext(), "Timer has been set :)", Toast.LENGTH_SHORT).show();

            //exit app && restart on timer
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        // Actions to do after 1 hour
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }, 3600000); //3,600,000 milliseconds == 1 hour

            } finally {

                //press back button on TimerActivity
                finish();
                //send connection back to receiver && press home button, exiting app
                receiverExitApp();

            }

        }
    };


    /*
    Important Developer Note: the use of this Receiver "breaks" the Android Activity LifeCycle
    However, there are times in life when you want to do kick ass shit, and must therefore
    break the rules in order to do said kick ass shit.
    */
    public void receiverExitApp() {

        //send connection back to receiver in "MenuActivity"
        ((ResultReceiver)getIntent().getParcelableExtra("finisher")).send(1, new Bundle());

        //go to home screen
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);

    }

}