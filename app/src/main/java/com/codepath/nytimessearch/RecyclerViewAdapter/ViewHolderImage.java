package com.codepath.nytimessearch.RecyclerViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.nytimessearch.R;

/**
 * Created by jsaluja on 3/16/2017.
 */

public class ViewHolderImage extends RecyclerView.ViewHolder {
    public ImageView articleThumbnail;
    public TextView articleHeadline;
    public TextView articleNewsDesk;
    public TextView articleSnippet;

    public ImageView getArticleThumbnail() {
        return articleThumbnail;
    }
    public TextView getArticleHeadline() {
        return articleHeadline;
    }
    public TextView getArticleNewsDesk() {return articleNewsDesk;}
    public TextView getArticleSnippet() {return articleSnippet;}

    public ViewHolderImage(View itemView) {
        super(itemView);
        articleThumbnail = (ImageView)itemView.findViewById(R.id.RvImageId);
        articleHeadline = (TextView) itemView.findViewById(R.id.RvHeadlineId);
        articleNewsDesk = (TextView) itemView.findViewById(R.id.RvNewsDeskId);
        articleSnippet = (TextView) itemView.findViewById(R.id.RvSnippetId);
    }
}
