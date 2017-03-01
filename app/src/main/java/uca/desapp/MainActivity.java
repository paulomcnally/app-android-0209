package uca.desapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uca.desapp.api.Api;
import uca.desapp.api.ApiInterface;
import uca.desapp.models.Tweet;
import uca.desapp.ui.activities.SignInActivity;
import uca.desapp.ui.activities.SignUpActivity;
import uca.desapp.utils.SessionUtil;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.getBase())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Tweet tweet = new Tweet();
        tweet.setText("Hola mundo");
        Call<Tweet> tweetCall = apiInterface.createTweet(tweet);
        tweetCall.enqueue(new Callback<Tweet>() {
            @Override
            public void onResponse(Call<Tweet> call, Response<Tweet> response) {

                Log.i(TAG, response.body().getText());
            }

            @Override
            public void onFailure(Call<Tweet> call, Throwable t) {

            }
        });

       //Log.i(TAG,  apiInterface.getTweets().request().url().toString());

        Call<List<Tweet>> call = apiInterface.getTweets();
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {


                if(response != null && response.body() != null) {

                    for(Tweet tweet : response.body()) {

                        Log.i(TAG, tweet.getText());
                    }
                } else {
                    Log.i(TAG, "Response es nulo");
                }

            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
        */


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        if(!SessionUtil.isActive()) {
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
        } else {
            initData();
        }
    }

    private void initData() {
        Call<List<Tweet>> call = Api.instance().getTweets(SessionUtil.getAccessToken());
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {


                if(response != null && response.body() != null) {

                    // specify an adapter (see also next example)
                    mAdapter = new MyAdapter(response.body());
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Log.i(TAG, "Response es nulo");
                }

            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }


}
