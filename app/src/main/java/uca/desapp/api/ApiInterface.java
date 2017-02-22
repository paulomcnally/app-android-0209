package uca.desapp.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import uca.desapp.models.Tweet;

public interface ApiInterface {

    @GET("tweets")
    Call<List<Tweet>> getTweets();
}
