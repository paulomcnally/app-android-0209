package uca.desapp.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import uca.desapp.models.SignInResponseModel;
import uca.desapp.models.Tweet;
import uca.desapp.models.User;

public interface ApiInterface {

    @GET("tweets")
    Call<List<Tweet>> getTweets();

    @POST("tweets")
    Call<Tweet> createTweet(@Body Tweet tweet);

    @POST("Users")
    Call<User> signUp(@Body User user);

    @POST("Users/login")
    Call<SignInResponseModel> signIn(@Body User user);
}
