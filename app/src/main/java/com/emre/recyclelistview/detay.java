package com.emre.recyclelistview;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.emre.recyclelistview.databinding.ActivityDetayBinding;
import com.emre.recyclelistview.databinding.ActivityMainBinding;

public class detay extends AppCompatActivity {
    private ActivityDetayBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetayBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Intent intent = getIntent();
        University uni = SelectedImage.getInstance().getUni();//selectedU;//(University) intent.getSerializableExtra("uni");
        binding.imageView.setImageBitmap(uni.getLogo());
        binding.textView.setText(uni.getName());
        binding.textView2.setText(uni.getCity());
    }
}