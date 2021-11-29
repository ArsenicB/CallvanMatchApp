package com.example.mobileswpj7t;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobileswpj7t.databinding.ActivityBoardBinding;

public class TaxiActivity extends DrawerBaseActivity {

    ActivityBoardBinding activityBoardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBoardBinding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(activityBoardBinding.getRoot());
        allocateActivityTitle("콜밴 합승");
    }
}