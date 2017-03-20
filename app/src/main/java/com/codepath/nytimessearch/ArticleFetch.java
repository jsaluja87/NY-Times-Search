package com.codepath.nytimessearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.codepath.nytimessearch.RecyclerViewAdapter.ArticleDisplayAdapter;
import com.codepath.nytimessearch.models.Article;
import com.codepath.nytimessearch.models.ResponseList;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.codepath.nytimessearch.SearchActivity.NY_API_KEY;

/**
 * Created by jsaluja on 3/19/2017.
 */

public class ArticleFetch {
    Context mContext;
    public ArticleFetch(Context context) {
        mContext = context;
    }

    public interface MyApiEndpointInterface {

        @GET("articlesearch.json")
        Call<ResponseList> loadOptions(@Query("api-key") String key,
                                       @Query("q") String searchQuery,
                                       @Query("page") int searchPage,
                                       @Query("sort") String sortOrder,
                                       @Query("begin_date") String newBeginDate,
                                       @Query("fq") String newsDeskOptions);
    }

    public void ArticleFetch(String searchQuery, int searchPageNumber, String newsSortingOrder, String newsBeginDate,
                             String newsDeskOptions, ArrayList<Article> articles, ArticleDisplayAdapter adapter) {

        String url = "https://api.nytimes.com/svc/search/v2/";

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        MyApiEndpointInterface apiService = retrofit.create(MyApiEndpointInterface.class);

        Call<ResponseList> call = apiService.loadOptions(NY_API_KEY, searchQuery, searchPageNumber,
                newsSortingOrder, newsBeginDate, newsDeskOptions);

        Log.d("MY_URL_STRING", "URL String is" + call.request().url());
        call.enqueue(new Callback<ResponseList>() {
            @Override
            public void onResponse(Call<ResponseList> call, Response<ResponseList> response) {
                ArrayList<Article> responseList = null;
                try {
                    responseList = response.body().getResponse().getArticles();
                    articles.addAll(responseList);
                    adapter.notifyItemRangeInserted(adapter.getItemCount(), responseList.size());
                    if(articles.size() == 0) {
                        Toast.makeText(mContext, "Your Query fetched no items! Please try something else!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseList> call, Throwable t) {
                Log.d("DEBUG", "Response failed");
                //Check for Network
                if(!isNetworkAvailable()) {
                    alert_user("Network Issue", "Please connect the device to an internet network!");
                } else {
                    if(!isOnline()) {
                        alert_user("Network Issue", "Device network does not have internet access!");
                    } else {
                        Log.d("DEBUG", "!!!Device has internet access!!!");
                    }
                }
            }
        });
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }

    public void alert_user(String title, String message) {
        AlertDialog.Builder dialog;
        dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setNegativeButton("Ok",
                (dialog1, which) -> dialog1.cancel());
        AlertDialog alertD = dialog.create();
        alertD.show();
    }
}

