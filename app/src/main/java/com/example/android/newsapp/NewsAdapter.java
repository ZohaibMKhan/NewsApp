package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zohaib on 20/09/16.
 */
public class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout,
                    parent, false);
        }

        final News currentNewsItem = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        TextView sectionName = (TextView) listItemView.findViewById(R.id.sectionName);
        TextView author = (TextView) listItemView.findViewById(R.id.author);

        title.setText(currentNewsItem.getTitle());
        sectionName.setText(currentNewsItem.getSectionName());
        author.setText(currentNewsItem.getAuthor());

        View.OnClickListener newsItemListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = currentNewsItem.getUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                getContext().startActivity(browserIntent);
            }
        };

        listItemView.setOnClickListener(newsItemListener);

        return listItemView;
    }
}
