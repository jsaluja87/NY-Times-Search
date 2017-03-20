package com.codepath.nytimessearch.RecyclerViewAdapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.nytimessearch.models.Article;
import com.codepath.nytimessearch.R;

import java.util.ArrayList;

/**
 * Created by jsaluja on 3/14/2017.
 */

public class ArticleDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private final int IMAGE_LAYOUT = 0;
    private final int TEXT_LAYOUT = 1;

    private Context mContext;
    private ArrayList<Article> mArticles;


    public ArticleDisplayAdapter(Context context, ArrayList<Article> articles) {
        mContext = context;
        mArticles = articles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == IMAGE_LAYOUT) {
            View ViewImage = inflater.inflate(R.layout.article_item, parent, false);
            viewHolder = new ViewHolderImage(ViewImage);
        } else {
            View ViewText = inflater.inflate(R.layout.article_item_no_thumbnail, parent, false);
            viewHolder = new ViewHolderText(ViewText);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if(viewHolder.getItemViewType() == IMAGE_LAYOUT) {
            ViewHolderImage vhImage = (ViewHolderImage) viewHolder;
            configureViewHolderImage(vhImage, position);
        } else {
            ViewHolderText vhText = (ViewHolderText) viewHolder;
            configureViewHolderText(vhText, position);
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if(holder.getItemViewType() == IMAGE_LAYOUT) {
            ViewHolderImage vhImage = (ViewHolderImage) holder;
            Glide.clear(vhImage.articleThumbnail);
        }

    }

    private void configureViewHolderImage(ViewHolderImage holder, int position) {
        Article article = mArticles.get(position);
        holder.articleThumbnail.setImageResource(0);
        Glide.with(mContext).load(article.getThumbNail()).into(holder.articleThumbnail);
        holder.articleHeadline.setText(article.getHeadLine());
        if(article.getNewsDesk() != null) {
            holder.articleNewsDesk.setText(article.getNewsDesk());
        }
        if(article.getSnippet() != null) {
            holder.articleSnippet.setText(article.getSnippet());
        }
    }

    private void configureViewHolderText(ViewHolderText holder, int position) {
        Article article = mArticles.get(position);
        holder.articleHeadline.setText(article.getHeadLine());
        if(article.getNewsDesk() != null) {
            holder.articleNewsDesk.setText(article.getNewsDesk());
        }
        if(article.getSnippet() != null) {
            holder.articleSnippet.setText(article.getSnippet());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Article article = mArticles.get(position);
        if(!TextUtils.isEmpty(article.getThumbNail())){
            return IMAGE_LAYOUT;
        } else {
            return TEXT_LAYOUT;
        }
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

}