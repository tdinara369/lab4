package com.example.lab4news.JSON;

import com.example.lab4news.News.News;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Динара on 13.10.2017.
 */

public class ResponseModel {

    @SerializedName("docs")
    @Expose
    private List<News> docs = null;


    public List<News> getNews() {
        return docs;
    }



}
