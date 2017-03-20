package com.codepath.nytimessearch;

import android.app.PendingIntent;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codepath.nytimessearch.RecyclerViewAdapter.ArticleDisplayAdapter;
import com.codepath.nytimessearch.RecyclerViewAdapter.EndlessRecyclerViewScrollListener;
import com.codepath.nytimessearch.RecyclerViewAdapter.ItemClickSupport;
import com.codepath.nytimessearch.databinding.ActivitySearchBinding;
import com.codepath.nytimessearch.models.Article;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements FilterFragment.OnFragmentInteractionListener {

    final static int GRIDVIEW_SPACING = 15;
    final static int NUM_GRID_COLUMNS = 2;
    final static String NY_API_KEY = "f62c615cd2d042b38811033f498160db";
    ArrayList<Article> articles;
    ArticleDisplayAdapter adapter;
    RecyclerView displayArticles;
    private StaggeredGridLayoutManager gridLayoutManager;
    String newsSortingOrder, newsBeginDate, newsDeskOptions;
    private EndlessRecyclerViewScrollListener scrollListener;
    String searchQuery;
    int searchPageNumber=0;
    Boolean querySearchedBefore=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActivitySearchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        displayArticles = binding.RecycleViewResultsId;

        Toast.makeText(this, "What would you like to read?", Toast.LENGTH_LONG).show();

        setupRecycleAdapter();
        setupEndlessScrolling();
        setupChromeItemClickListener();

    }

    public void setupRecycleAdapter() {
        articles = new ArrayList<>();
        adapter = new ArticleDisplayAdapter(this, articles);
        displayArticles.setAdapter(adapter);
        displayArticles.setHasFixedSize(true);
        gridLayoutManager = new StaggeredGridLayoutManager(NUM_GRID_COLUMNS, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        displayArticles.setLayoutManager(gridLayoutManager);
        int spacingInPixels = GRIDVIEW_SPACING;
        displayArticles.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }

    private void setupEndlessScrolling() {
        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                ArticleFetch fetch = new ArticleFetch(SearchActivity.this);
                searchPageNumber = page;
                fetch.ArticleFetch(searchQuery, searchPageNumber, newsSortingOrder, newsBeginDate, newsDeskOptions, articles, adapter );
            }
        };
        // Adds the scroll listener to RecyclerView
        displayArticles.addOnScrollListener(scrollListener);

    }
    private void setupChromeItemClickListener() {
        ItemClickSupport.addTo(displayArticles).setOnItemClickListener(
                (recyclerView, position, v) -> {
                    String ClickedItemUrl = articles.get(position).getWebUrl();

                    //Adding Toolbar Icon
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_share);
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, ClickedItemUrl);
                    int requestCode = 100;
                    PendingIntent pendingIntent = PendingIntent.getActivity(SearchActivity.this,
                            requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

                    builder.setToolbarColor(ContextCompat.getColor(SearchActivity.this, R.color.colorPrimary));
                    builder.addDefaultShareMenuItem();
                    builder.setActionButton(bitmap, "Share Link", pendingIntent, true);

                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(SearchActivity.this, Uri.parse(ClickedItemUrl));

                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.MIActionSearchId);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                querySearchedBefore = true;
                searchView.clearFocus();
                OnArticleSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MIFilterId:
                FragmentManager fm = getSupportFragmentManager();

                FilterFragment filterFragmentObject = FilterFragment.newInstance();
                filterFragmentObject.show(fm, "fragment_edit_name");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void OnArticleSearch(String query) {
        //Clearing out the current results
        int ArticleArraySize = this.articles.size();
        this.articles.clear();
        adapter.notifyItemRangeRemoved(0, ArticleArraySize);
        scrollListener.resetState();

        searchPageNumber=0;
        //Important. Set query to global query object
        searchQuery = query;
        ArticleFetch fetch = new ArticleFetch(SearchActivity.this);
        fetch.ArticleFetch(searchQuery, searchPageNumber, newsSortingOrder, newsBeginDate, newsDeskOptions, articles, adapter );
    }

    public void onFinishEditDialog(String SortingOrder, String InputDate, String DeskOptions) {

        newsSortingOrder = SortingOrder;
        newsBeginDate = InputDate;
        newsDeskOptions = DeskOptions;
        if(querySearchedBefore) {
            int ArticleArraySize = this.articles.size();
            this.articles.clear();
            adapter.notifyItemRangeRemoved(0, ArticleArraySize);
            ArticleFetch fetch = new ArticleFetch(SearchActivity.this);
            fetch.ArticleFetch(searchQuery, searchPageNumber, newsSortingOrder, newsBeginDate, newsDeskOptions, articles, adapter );
        }
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}

