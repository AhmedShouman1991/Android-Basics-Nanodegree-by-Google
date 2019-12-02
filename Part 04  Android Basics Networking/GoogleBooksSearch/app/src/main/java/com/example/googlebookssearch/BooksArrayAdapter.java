package com.example.googlebookssearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BooksArrayAdapter extends ArrayAdapter<Book> {
    public BooksArrayAdapter(@NonNull Context context, @NonNull List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Book currentBook = getItem(position);

        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView bookTitle = listItem.findViewById(R.id.book_title);
        bookTitle.setText(currentBook.getTitle());

        TextView bookAuthor = listItem.findViewById(R.id.book_author);
        bookAuthor.setText(currentBook.getAuthorName());

        return listItem;
    }
}
