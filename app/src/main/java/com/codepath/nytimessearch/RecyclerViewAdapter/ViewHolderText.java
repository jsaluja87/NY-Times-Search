package com.codepath.nytimessearch.RecyclerViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.codepath.nytimessearch.R;

/**
 * Created by jsaluja on 3/16/2017.
 */

public class ViewHolderText extends RecyclerView.ViewHolder {
    public TextView articleHeadline;
   public TextView articleNewsDesk;
    public TextView articleSnippet;

    public TextView getArticleHeadline() {return articleHeadline;}

    public TextView getArticleNewsDesk() {return articleNewsDesk;}

    public TextView getArticleSnippet() {return articleSnippet;}

    public ViewHolderText(View itemView) {
        super(itemView);
        articleHeadline = (TextView) itemView.findViewById(R.id.RvNoThumbMainId);
        articleNewsDesk = (TextView) itemView.findViewById(R.id.RvNoThumbNewsDeskId);
        articleSnippet = (TextView) itemView.findViewById(R.id.RvNoThumbSnippetId);
    }
}
