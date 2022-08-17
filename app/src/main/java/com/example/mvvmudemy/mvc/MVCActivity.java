package com.example.mvvmudemy.mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmudemy.R;

public class MVCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvcactivity);
    }
    public static Intent getIntent(Context context){
        return new Intent(context, MVCActivity.class);
    }
}