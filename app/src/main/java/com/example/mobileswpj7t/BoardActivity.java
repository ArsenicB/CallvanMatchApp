package com.example.mobileswpj7t;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobileswpj7t.databinding.ActivityTaxiBinding;

public class BoardActivity extends DrawerBaseActivity {

    ActivityTaxiBinding activityTaxiBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTaxiBinding = ActivityTaxiBinding.inflate(getLayoutInflater());
        setContentView(activityTaxiBinding.getRoot());
        allocateActivityTitle("게시판");
    }
}