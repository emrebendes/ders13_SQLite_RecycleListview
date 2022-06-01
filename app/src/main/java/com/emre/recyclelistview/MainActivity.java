package com.emre.recyclelistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.emre.recyclelistview.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<University> uni;
    //public static University selectedU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        uni = new ArrayList<>();

        SQLiteDatabase database = this.openOrCreateDatabase("uni",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS university " +
                "(name VARCHAR,city VARCHAR, image BLOB)");
        //String sql ="INSERT INTO university (name,city,image) VALUES ('Ortadoğu Teknik Üniversitesi','Ankara',"+R.drawable.metu+")";
        //database.execSQL(sql);

        Cursor cursor = database.rawQuery("SELECT * FROM university",null);
        while(cursor.moveToNext())
        {
            String name = cursor.getString(0);
            String city = cursor.getString(1);
            byte[] imgByte = cursor.getBlob(2);

            Bitmap img = BitmapFactory.decodeByteArray(imgByte,0,imgByte.length);
            uni.add(new University(name,city,img));
        }

        /*University nevu = new University("Nevşehir Üniversitesi","Nevşehir",R.drawable.nevu);
        University eru = new University("Erciyes Üniversitesi","Kayseri",R.drawable.eru);
        University itu = new University("İstenbul Teknik Üniversitesi","İstanbul",R.drawable.itu);
        University metu = new University("Ortadoğu Teknik Üniversitesi","Ankara",R.drawable.metu);*/


        /*uni.add(eru);
        uni.add(nevu);
        uni.add(metu);
        uni.add(itu);*/

        //1. adım liste tipini belirleme
        binding.rView.setLayoutManager(new LinearLayoutManager(this));//LinearLayoutManager(this));
        binding.rView.setAdapter(new UniAdapter(uni));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.ekle)
        {
            Intent intent = new Intent(this, Ekle.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }


}