package com.example.mvvmudemy.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmudemy.MainActivity;
import com.example.mvvmudemy.R;
import com.example.mvvmudemy.mvc.MVCActivity;

public class MVVMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmactivity);
    }
    public static Intent getIntent(Context context){
        return new Intent(context, MVVMActivity.class);
    }
}