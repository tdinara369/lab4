package com.example.lab4news.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab4news.Adapter.NewsAdapter;
import com.example.lab4news.JSON.ApiService;
import com.example.lab4news.JSON.JSONmodel;
import com.example.lab4news.News.News;
import com.example.lab4news.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Динара on 13.10.2017.
 */

public class NewsFragment extends Fragment {
    NewsAdapter newsAdapter;
    List<News> newsList = new ArrayList<>();
    String key = "4e8dedf5baf2422fa16b29277b0f8504";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().
                readTimeout(60, TimeUnit.SECONDS).
                connectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/search/v2/")
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<JSONmodel> newsCall = service.getNews(key);

        newsCall.enqueue(new Callback<JSONmodel>() {
            @Override
            public void onResponse(Call<JSONmodel> call, Response<JSONmodel> response) {
                if (response.isSuccessful()) {
                    Log.e("RESPONSE", response.toString());
                    JSONmodel jsoNmodel = response.body();
                    List<News> newses = jsoNmodel.getResponse().getNews();
                    System.out.println(response.body());
                    for (News news : newses) {
                        //Log.e("News", news.getTitle().getPrintHeadline());
                        if (news.imageURL.isEmpty() == false){
                            Log.d("Image URL", news.getMultimedium().get(0).getUrl());
                        }
                    }
                    newsList.clear();
                    newsList.addAll(newses);
                    newsAdapter.notifyDataSetChanged();
                }
                else {
                    Log.e("Not response", response.toString());
                }
            }

            @Override
            public void onFailure(Call<JSONmodel> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        newsAdapter = new NewsAdapter(getActivity(), newsList);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(newsAdapter);

        //new DatabaseAsync().execute();
        return view;
    }


}

