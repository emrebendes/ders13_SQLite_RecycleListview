package com.emre.recyclelistview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.emre.recyclelistview.databinding.ActivityDetayBinding;
import com.emre.recyclelistview.databinding.ActivityEkleBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class Ekle extends AppCompatActivity {
    private ActivityEkleBinding binding;
    ActivityResultLauncher<Intent> intentActivityResultLauncher;
    ActivityResultLauncher<String> permissionActivityResultLauncher;
    Bitmap resim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEkleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        registerL();

    }

    public void resimSec(View view)
    {
        //sdk 18 sonrasında izin istiyor öncesinde bu kodlara gerek yok
        //her android versiyonunda bazı yasaklar yok ContextCompat bu uyumu kontrol ediyor
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                // açıklama göstermemiz gerekiyor mu
                Snackbar.make(view,"galeri için izin gerekmekte",Snackbar.LENGTH_INDEFINITE).setAction("izin ver", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // izin istenmemiş izin iste
                        permissionActivityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                }).show();
            }else{
                // izin istenmemiş izin iste
                permissionActivityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }


        }else{
            // izin verilmiş galeriye git
            Intent intentGaleri = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intentActivityResultLauncher.launch(intentGaleri);

        }
    }


    public void registerL()
    {
        intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()== RESULT_OK)
                {
                    Intent gelenIntent = result.getData();
                    if (gelenIntent != null)
                    {
                        Uri yol = gelenIntent.getData();
                        //binding.imageView2.setImageURI(yol);
                        try {

                            if (Build.VERSION.SDK_INT>=28) {
                                ImageDecoder.Source source = ImageDecoder.createSource(Ekle.this.getContentResolver(), yol);
                                resim = ImageDecoder.decodeBitmap(source);
                                binding.imageView2.setImageBitmap(resim);
                            }else{
                                resim= MediaStore.Images.Media.getBitmap(Ekle.this.getContentResolver(),yol);
                                binding.imageView2.setImageBitmap(resim);
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        permissionActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result)
                {
                    Intent intentGaleri = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intentActivityResultLauncher.launch(intentGaleri);
                }else{
                    Toast.makeText(Ekle.this,"izin gerekiyor",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    public void ekleButton(View view)
    {
        String name = binding.name.getText().toString();
        String city = binding.city.getText().toString();

        //"Bitmap b = Bitmap.createScaledBitmap(resim,200,200,true);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        resim.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] im= outputStream.toByteArray();

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("uni",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS university " +
                    "(name VARCHAR,city VARCHAR, image BLOB)");

            String sq="INSERT INTO  university (name,city,image) VALUES (?,?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sq);
            sqLiteStatement.bindString(1,name);
            sqLiteStatement.bindString(2,city);
            sqLiteStatement.bindBlob(3,im);
            sqLiteStatement.execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}