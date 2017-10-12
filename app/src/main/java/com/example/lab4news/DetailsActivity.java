package com.example.lab4news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab4news.News.News;
import com.squareup.picasso.Picasso;

/**
 * Created by Динара on 13.10.2017.
 */

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        News news = getIntent().getParcelableExtra("news");

        TextView title = (TextView) findViewById(R.id.title);
        TextView text = (TextView) findViewById(R.id.date);
        TextView date = (TextView) findViewById(R.id.desc);
        ImageView image = (ImageView) findViewById(R.id.image);

        title.setText(news.getTitle().getPrintHeadline());
        text.setText(news.getDate());
        date.setText(news.getDesc());
        String baseUrl = "https://nytimes.com/";
        String imageURL = baseUrl;
        if (news.imageURL.isEmpty() == false) {
            Log.e("image!", news.getMultimedium().get(0).getUrl());
            imageURL += news.getMultimedium().get(0).getUrl();
            System.out.println("IMAGE" + imageURL);
        }
        Picasso.with(this).load(imageURL)
                .placeholder(R.drawable.placeholder)
                .into(image);

    }
}
