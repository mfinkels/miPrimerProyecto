package com.example.matias.morfando.Intro;

import com.example.matias.morfando.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;


import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
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

        showStatusBar(false);
        //Add animation to the intro slider
        setDepthAnimation();

        askForPermissions(new String[]{Manifest.permission.CAMERA}, 3);
        setSkipText("Saltear");
        setDoneText("Terminar");
    }

    @Override
    public void onSkipPressed(android.support.v4.app.Fragment currentFragment) {
        // Do something here when users click or tap on Skip button.
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(android.support.v4.app.Fragment currentFragment) {
        // Do something here when users click or tap tap on Done button.
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable android.support.v4.app.Fragment oldFragment, @Nullable android.support.v4.app.Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something here when slide is changed
    }

}
