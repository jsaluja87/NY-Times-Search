package com.codepath.nytimessearch.models;

/**
 * Created by jsaluja on 3/19/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Multimedia {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return "http://www.nytimes.com/" + url;
    }
}