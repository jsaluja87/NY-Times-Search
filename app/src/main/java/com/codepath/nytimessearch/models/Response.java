package com.codepath.nytimessearch.models;

/**
 * Created by jsaluja on 3/19/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response {
    @SerializedName("docs")
    @Expose
    private ArrayList<Article> articles;

    public ArrayList<Article> getArticles() {
        return articles;
    }
}