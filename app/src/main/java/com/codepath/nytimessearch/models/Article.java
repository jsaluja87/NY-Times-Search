package com.codepath.nytimessearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

/**
 * Created by jsaluja on 3/14/2017.
 */
public class Article {
    @SerializedName("web_url")
    @Expose
    private String webUrl;

    @SerializedName("snippet")
    @Expose
    private String snippet;

    @SerializedName("headline")
    @Expose
    private Headline headline;

    @SerializedName("multimedia")
    @Expose
    private List<Multimedia> multimedia;

    @SerializedName("news_desk")
    @Expose
    private String newsDesk;


    public String getWebUrl() {
        return webUrl;
    }

    public String getSnippet() {
        if (Objects.equals(this.snippet, "null")) {
            return "";
        } else {
            return snippet;
        }
    }

    public String getThumbNail() {
        if (this.multimedia.size() > 0) {
            return multimedia.get(0).getUrl();
        } else {
            return "";
        }
    }

    public String getHeadLine() {
        if (Objects.equals(this.headline.getMain(), "null")) {
            return "";
        } else {
            return headline.getMain();
        }

    }

    public String getNewsDesk() {
        if (Objects.equals(this.newsDesk, "null")) {
            return "";
        } else {
            return newsDesk;
        }
    }
}
