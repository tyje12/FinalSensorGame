package com.example.labuser.sensorgame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class MainActivity extends AppCompatActivity implements SensorEventListener{


    private Button startGame;
    private SensorManager mSensorManager;
    private WindowManager mWindowManager;
    private Display mDisplay;
    private Sensor mAccelerometer;
    double i = Math.floor(Math.random() * 2) +1;
    private float z;
    private float y;
    private float x;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startGame = (Button) findViewById(R.id.startGame);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();



    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (mDisplay.getRotation()) {
            case Surface.ROTATION_0:
                x = event.values[0];
                y = event.values[1];
                z = event.values[2];


                break;
            case Surface.ROTATION_90:
                x = -event.values[0];
                y = -event.values[1];
                z = -event.values[2];

                break;
            case Surface.ROTATION_180:
                x = event.values[0];
                y = event.values[1];
                z = event.values[2];

                break;
            case Surface.ROTATION_270:
                x = -event.values[0];
                y = -event.values[1];
                z = -event.values[2];

                break;
        }
        String[] action;
        action = new String[6];
        action[0] = "FORWARD";
        action[1] = "LEFT";
        action[2] = "RIGHT";
        action[3] = "BACK";
        //action[4] = "TAP";
        //action[5] = "FREEZE";
        //action[6] = "SHAKE UP AND DOWN"


        if (i == 0) {
            //display string[0]
            TextView text = (TextView) findViewById(R.id.textView);
            text.setText(action[3]);
            //add a text view that says correct
            if (x > 3) {
                i = Math.floor(Math.random() * 4) + 1;
                TextView scoreT = (TextView) findViewById(R.id.scoreText);
                score++;
                scoreT.setText("Score: " + String.valueOf(score));

            } else if(x<-2) {
                //add text that says wrong
                //subtract a point
                i = Math.floor(Math.random() * 4) + 1;
                TextView scoreT = (TextView) findViewById(R.id.scoreText);
                score--;
                scoreT.setText("Score: " + String.valueOf(score));
            }
        }

        //LEFT
        if (i == 1) {
            TextView text = (TextView) findViewById(R.id.textView);
            text.setText(action[1]);
            //move phone left
            //add a text view that says correct
            if (z < -3) {
                i = Math.floor(Math.random() * 4) + 1;
                TextView scoreT = (TextView) findViewById(R.id.scoreText);
                score++;
                scoreT.setText("Score: " + String.valueOf(score));
            }
            else if (z>2) {
                //add text that says wrong
                i = Math.floor(Math.random() * 4) + 1;
                TextView scoreT = (TextView) findViewById(R.id.scoreText);
                score--;
                scoreT.setText("Score: " + String.valueOf(score));
            }
        }


        //RIGHT
        if (i == 2) {
            TextView text = (TextView) findViewById(R.id.textView);
            text.setText(action[2]);
            //move phone right
            //add a text view that says correct
            if (z > 3) {
                i = Math.floor(Math.random() * 4) + 1;
                TextView scoreT = (TextView) findViewById(R.id.scoreText);
                score++;
                scoreT.setText("Score: " + String.valueOf(score));
            }
            else if(z<-2) {
                //add text that says wrong
                i = Math.floor(Math.random() * 4) + 1;
                TextView scoreT = (TextView) findViewById(R.id.scoreText);
                score--;
                scoreT.setText("Score: " + String.valueOf(score));
            }
        }

        //BACK
        if (i == 3) {
            //display string[0]
            TextView text = (TextView) findViewById(R.id.textView);
            text.setText(action[3]);
            //add a text view that says correct
            if (x < -3) {
                i = Math.floor(Math.random() * 4) + 1;
                TextView scoreT = (TextView) findViewById(R.id.scoreText);
                score++;
                scoreT.setText("Score: " + String.valueOf(score));

            } else if (x>2) {
                //add text that says wrong
                //subtract a point
                i = Math.floor(Math.random() * 4) + 1;
                TextView scoreT = (TextView) findViewById(R.id.scoreText);
                score--;
                scoreT.setText("Score: " + String.valueOf(score));
            }
        }

        /*
        //TAP
        if (i == 4) {
            //set onclick listener for if they touch the screen
            TextView text = (TextView) findViewById(R.id.textView);
            text.setText(action[4]);

            i = Math.floor(Math.random() * 2) + 1;
            TextView scoreT = (TextView) findViewById(R.id.scoreText);
            score++;
            scoreT.setText("Score: " + String.valueOf(score));
        }
        else
        {
            //add text that says wrong
            //subtract a point
            i = Math.floor(Math.random() * 2) +1;
            TextView scoreT = (TextView) findViewById(R.id.scoreText);
            score--;
            scoreT.setText("Score: " + String.valueOf(score));

        }

        //FREEZE
        if (i == 5 && y > 2 || y < 2 || z > 2 || z < 2) {
            //display a 3 second timer
            //if timer runs out and they don't move give point
            //if they move before timer ends, subtract point

            TextView text = (TextView) findViewById(R.id.textView);
            text.setText(action[5]);
            //add a text view that says correct
            i = Math.floor(Math.random() * 2) + 1;
            TextView scoreT = (TextView) findViewById(R.id.scoreText);
            score++;
            scoreT.setText("Score: " + String.valueOf(score));
        }
        else
        {
            //add text that says wrong
            //subtract a point
            i = Math.floor(Math.random() * 2) +1;
            TextView scoreT = (TextView) findViewById(R.id.scoreText);
            score--;
            scoreT.setText("Score: " + String.valueOf(score));

        }
        */
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void startGame(View v) {

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        TextView xT = (TextView) findViewById(R.id.xText);
        xT.setText(String.valueOf(x));
        TextView yT = (TextView) findViewById(R.id.yText);
        yT.setText(String.valueOf(y));
        TextView zT = (TextView) findViewById(R.id.zText);
        zT.setText(String.valueOf(z));

        //got this timer code from android studio development help
        new CountDownTimer(30000, 1000) {
            TextView timer = (TextView) findViewById(R.id.mTextField);

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
                startGame.setVisibility(View.INVISIBLE);
            }

            public void onFinish() {
                timer.setText("done!");
                startGame.setVisibility(View.VISIBLE);
            }
        }.start();
        //end of timer code

    }
}