package com.example.mvvmudemy.mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvvmudemy.R;

import java.util.ArrayList;
import java.util.List;

public class MVCActivity extends AppCompatActivity {
    private List<String> listValues=new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView list;
    private CountriesController controller;
    private Button retryButton;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvcactivity);
        setTitle("MVC Activity");

        list=findViewById(R.id.list);
        retryButton=findViewById(R.id.retryButton);
        progress=findViewById(R.id.progress);

        controller=new CountriesController(MVCActivity.this);
        adapter=new ArrayAdapter<>(this,R.layout.row_layout,R.id.listText,listValues);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MVCActivity.this,"You Clicked"+listValues.get(i),Toast.LENGTH_SHORT).show();
            }
        });
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRetry(view);
            }
        });

    }
    public void setValues(List<String> values){
        listValues.clear();
        listValues.addAll(values);
        retryButton.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }
    public static Intent getIntent(Context context){
        return new Intent(context, MVCActivity.class);
    }
    public void onRetry(View view) {
        controller.onRefresh();
        list.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
    }

    public void onError() {
        Log.d("onError","MVCACtivity");
        Toast.makeText(this, "getString(R.string.error_message)", Toast.LENGTH_SHORT).show();
        progress.setVisibility(View.GONE);
        list.setVisibility(View.GONE);
        retryButton.setVisibility(View.VISIBLE);
    }
}