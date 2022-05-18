package com.emre.recyclelistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.emre.recyclelistview.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<University> uni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        University nevu = new University("Nevşehir Üniversitesi","Nevşehir",R.drawable.nevu);
        University eru = new University("Erciyes Üniversitesi","Kayseri",R.drawable.eru);
        University itu = new University("İstenbul Teknik Üniversitesi","İstanbul",R.drawable.itu);
        University metu = new University("Ortadoğu Teknik Üniversitesi","Ankara",R.drawable.metu);
        uni = new ArrayList<>();

        uni.add(eru);
        uni.add(nevu);
        uni.add(metu);
        uni.add(itu);

        //1. adım liste tipini belirleme
        binding.rView.setLayoutManager(new GridLayoutManager(this,2));//LinearLayoutManager(this));
        binding.rView.setAdapter(new UniAdapter(uni));

    }
}