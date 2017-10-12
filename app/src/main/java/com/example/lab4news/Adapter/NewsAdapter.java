package com.example.lab4news.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab4news.DetailsActivity;
import com.example.lab4news.News.News;
import com.example.lab4news.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Динара on 13.10.2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    java.util.List<News> List;


    public NewsAdapter(Context context, List<News> newsList) {
        mContext = context;
        this.List = newsList;
        for (int i = 0; i < newsList.size(); i++){
            System.out.println(newsList.get(i).toString());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("ViewHolder", "Create");
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("ViewHolder", "Bind");
        Log.d("position", String.valueOf(position));
        MyViewHolder viewHolder = (MyViewHolder) holder;
        News news = List.get(position);
        viewHolder.setPosition(position);
        viewHolder.Title.setText(news.getTitle().getPrintHeadline());
        viewHolder.Date.setText(news.getDate());

        String baseUrl = "https://nytimes.com/";
        String imageURL = baseUrl;
        if (news.imageURL.isEmpty() == false) {
            Log.e("image!", news.getMultimedium().get(0).getUrl());
            imageURL += news.getMultimedium().get(0).getUrl();
            System.out.println("IMAGE" + imageURL);
        }
        Picasso.with(mContext).load(imageURL)
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.Image);

    }

    @Override
    public int getItemCount() {
        return List.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView Title;
        TextView Date;
        ImageView Image;
        int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.title);
            Date = (TextView) itemView.findViewById(R.id.date);
            Image = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }


        public void setPosition(int position){
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
            intent.putExtra("news", List.get(position));
            mContext.startActivity(intent);
        }
    }
}
