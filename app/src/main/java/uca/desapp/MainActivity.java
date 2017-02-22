package uca.desapp;

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

        Call<List<Tweet>> call = apiInterface.getTweets();
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if(response != null) {
                    Log.i(TAG, response.body().toString());
                    for(Tweet tweet : response.body()) {

                        Log.i(TAG, tweet.getText());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {

            }
        });
    }


}
