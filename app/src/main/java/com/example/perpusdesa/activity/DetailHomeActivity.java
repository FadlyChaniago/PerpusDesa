package com.example.perpusdesa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.perpusdesa.R;

public class DetailHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");

        // Tampilkan detail gambar
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .load(image)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);
    }
}