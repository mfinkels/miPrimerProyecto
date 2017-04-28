package com.example.matias.morfando;

import com.github.paolorotolo.appintro.AppIntro;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Matias on 4/28/2017.
 */

public class myIntro extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {

        //adding the three slides for introduction app you can ad as many you needed
        addSlide(AppIntroSampleSlider.newInstance(R.layout.activity_intro_first));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.activity_intro_second));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.activity_intro_final));

        // Show and Hide Skip and Done buttons
        showStatusBar(false);
        showSkipButton(false);

        // Turn vibration on and set intensity
        // You will need to add VIBRATE permission in Manifest file
        setVibrate(true);
        setVibrateIntensity(30);

        //Add animation to the intro slider
        setDepthAnimation();
    }

    @Override
    public void onSkipPressed() {
        // Do something here when users click or tap on Skip button.
        Toast.makeText(getApplicationContext(),
                getString(R.string.app_intro_skip), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), activityLogIn.class);
        startActivity(i);
    }

    @Override
    public void onNextPressed() {
        // Do something here when users click or tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something here when users click or tap tap on Done button.
        Intent i = new Intent(this, activityLogIn.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something here when slide is changed
    }

}
