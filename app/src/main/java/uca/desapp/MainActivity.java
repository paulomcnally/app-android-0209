package uca.desapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import uca.desapp.ui.activities.SignUpActivity;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


                if(response != null) {

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

        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }


}
