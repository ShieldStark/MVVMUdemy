package com.example.mvvmudemy.mvvm;

import androidx.appcompat.app.AppCompatActivity;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class MVVMActivity extends AppCompatActivity {

    private final List<String> listValues=new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView list;
    private CountriesViewModel viewModel;
    private Button retryButton;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmactivity);
        setTitle("MVVMActivity");
        list=findViewById(R.id.list);
        retryButton=findViewById(R.id.retryButton);
        progress=findViewById(R.id.progress);

        adapter=new ArrayAdapter<>(this,R.layout.row_layout,R.id.listText,listValues);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MVVMActivity.this,"You Clicked"+listValues.get(i),Toast.LENGTH_SHORT).show();
            }
        });
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRetry(view);
            }
        });
        viewModel=new ViewModelProvider(this).get(CountriesViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getCountries().observe((LifecycleOwner) this, countries->{
            if(countries!=null){
                listValues.clear();
                listValues.addAll(countries);
                list.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }
            else {
                list.setVisibility(View.GONE);
            }
        });
        viewModel.getCountriesError().observe((LifecycleOwner) this, error->{
            progress.setVisibility(View.GONE);
            if(error){
                Toast.makeText(this, "getString(R.string.error_message)", Toast.LENGTH_SHORT).show();
                retryButton.setVisibility(View.VISIBLE);
            }
            else {
                retryButton.setVisibility(View.GONE);
            }
        });
    }

    public void onRetry(View view) {
        viewModel.onRefresh();
        list.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
    }

    public static Intent getIntent(Context context){
        return new Intent(context, MVVMActivity.class);
    }

}