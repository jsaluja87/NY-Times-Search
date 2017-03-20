package com.codepath.nytimessearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jsaluja on 3/19/2017.
 */

public class Headline {

    @SerializedName("main")
    @Expose
    private String main;

    public String getMain() {
        return main;
    }

}
