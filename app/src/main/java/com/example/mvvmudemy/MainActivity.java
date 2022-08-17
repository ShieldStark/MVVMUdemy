package com.example.mvvmudemy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvvmudemy.mvc.MVCActivity;
import com.example.mvvmudemy.mvp.MVPActivity;
import com.example.mvvmudemy.mvvm.MVVMActivity;

public class MainActivity extends AppCompatActivity {
    private Button mvc;
    private Button mvp;
    private Button mvvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mvc=findViewById(R.id.mvc);
        mvp=findViewById(R.id.mvp);
        mvvm=findViewById(R.id.mvvm);

        mvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMVC(view);
            }
        });
        mvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMVP(view);
            }
        });
        mvvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMVVM(view);
            }
        });
    }
    public void onMVC(View v){
        startActivity(MVCActivity.getIntent(this));
    }
    public void onMVP(View v){
        startActivity(MVPActivity.getIntent(this));
    }
    public void onMVVM(View v){
        startActivity(MVVMActivity.getIntent(this));
    }

}