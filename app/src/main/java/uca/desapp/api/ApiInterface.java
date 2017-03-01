package uca.desapp.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import uca.desapp.models.SignInResponseModel;
import uca.desapp.models.Tweet;
import uca.desapp.models.User;
import uca.desapp.utils.SessionUtil;

public interface ApiInterface {

    @GET("tweets")
    Call<List<Tweet>> getTweets(@Header(SessionUtil.ACCESS_TOKEN_HEADER) String accessToken);

    @POST("tweets")
    Call<Tweet> createTweet(@Body Tweet tweet);

    @POST("Users")
    Call<User> signUp(@Body User user);

    @POST("Users/login")
    Call<SignInResponseModel> signIn(@Body User user);
}
