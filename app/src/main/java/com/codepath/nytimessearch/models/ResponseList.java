package com.codepath.nytimessearch.models;

/**
 * Created by jsaluja on 3/19/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseList {
    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }
}
