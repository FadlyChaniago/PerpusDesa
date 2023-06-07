package com.example.perpusdesa.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perpusdesa.R;
import com.example.perpusdesa.adapter.BookmarkListAdapter;
import com.example.perpusdesa.model.Bookmark;
import com.example.perpusdesa.viewmodel.BookmarkViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    private BookmarkViewModel bookmarkViewModel;
    private BookmarkListAdapter adapter;
    private BottomNavigationView bottomNavigationView;
    private EditText titleEditText, urlEditText;
    private Button addBookmarkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        bottomNavigationView = findViewById(R.id.book);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bookmark) {
                    return true;
                } else if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewBookmark);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookmarkListAdapter(this);
        recyclerView.setAdapter(adapter);

        bookmarkViewModel = new ViewModelProvider(this).get(BookmarkViewModel.class);
        bookmarkViewModel.getAllBookmarks().observe(BookmarkActivity.this, new Observer<List<Bookmark>>() {
            @Override
            public void onChanged(List<Bookmark> bookmarks) {
                adapter.setBookmarks(bookmarks);
            }
        });


        titleEditText = findViewById(R.id.titleEditText);
        urlEditText = findViewById(R.id.urlEditText);
        addBookmarkButton = findViewById(R.id.addBookmarkButton);

        addBookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String url = urlEditText.getText().toString().trim();

                if (title.isEmpty() || url.isEmpty()) {
                    Toast.makeText(BookmarkActivity.this, "Please enter both title and URL", Toast.LENGTH_SHORT).show();
                } else {
                    Bookmark newBookmark = new Bookmark(title, url);
                    bookmarkViewModel.insertBookmark(newBookmark);

                    titleEditText.setText("");
                    urlEditText.setText("");
                }
            }
        });
    }
}