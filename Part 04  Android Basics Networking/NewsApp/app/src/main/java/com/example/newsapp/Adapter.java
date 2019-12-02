package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<NewsItem> {
    public Adapter(@NonNull Context context, int resource, @NonNull List<NewsItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.titleView = convertView.findViewById(R.id.article_title);
            viewHolder.sectionView = convertView.findViewById(R.id.article_section);
            viewHolder.authorView = convertView.findViewById(R.id.article_author);
            viewHolder.dateView = convertView.findViewById(R.id.date);
            viewHolder.timeView = convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }


        NewsItem current = getItem(position);
        if (current != null) {
            String title = current.getmTitle();
            viewHolder.titleView.setText(title);

            String author = current.getmAuthor();
            if (author == null || author.isEmpty()) {
                author = "The Guardian";
            }
            viewHolder.authorView.setText(author);
            String section = current.getmSection();
            viewHolder.sectionView.setText(section);

            String time = "";
            String date = "";
            String articleTime = current.getTime();
            if (articleTime != null && !articleTime.isEmpty()) {

                int timeIndex = articleTime.indexOf('T');
                time = articleTime.substring(timeIndex + 1, articleTime.length() - 1);
                date = articleTime.substring(0, timeIndex);

            }
            viewHolder.timeView.setText(time);
            viewHolder.dateView.setText(date);
        }
        return convertView;

    }
    private static class ViewHolder {
        TextView titleView;
        TextView authorView;
        TextView sectionView;
        TextView timeView;
        TextView dateView;
    }
}
