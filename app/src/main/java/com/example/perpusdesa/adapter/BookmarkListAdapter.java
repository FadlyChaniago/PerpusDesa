package com.example.perpusdesa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perpusdesa.R;
import com.example.perpusdesa.model.Bookmark;

import java.util.ArrayList;
import java.util.List;

public class BookmarkListAdapter extends RecyclerView.Adapter<BookmarkListAdapter.BookmarkViewHolder> {
    private Context context;
    private List<Bookmark> bookmarkList;

    public BookmarkListAdapter(Context context) {
        this.context = context;
        this.bookmarkList = new ArrayList<>();

    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        bookmarkList = bookmarks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bookmark, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        Bookmark bookmark = bookmarkList.get(position);
        holder.titleTextView.setText(bookmark.getTitle());
        holder.urlTextView.setText(bookmark.getUrl());
    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView urlTextView;

        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            urlTextView = itemView.findViewById(R.id.urlTextView);
        }
    }
}
