package com.example.project1;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @POST("/addUser")
    Call<Post> savePost(@Body Post post);

    @GET("/users")
    Call<List<Post>> doGetListResources();
}