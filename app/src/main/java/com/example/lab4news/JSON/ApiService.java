package com.example.lab4news.JSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Динара on 13.10.2017.
 */

public interface ApiService {


    @GET("articlesearch.json")
    Call<JSONmodel> getNews(@Query("api-key") String key);

}

