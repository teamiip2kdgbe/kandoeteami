package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.user.data.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;


public interface UserService {

    /**
     *
     * @param token
     * @param userId
     * @param callback
     */
    @GET("/users/{userId}")
    void getUserById(@Header("Authorization") String token, @Path("userId") int userId, Callback<User> callback);

    /**
     *
     * @param token
     * @param callback
     */
    @GET("/users/currentUser")
    void getCurrentUser(@Header("Authorization") String token, Callback<User> callback);

    /**
     *
     * @param token
     * @param user
     * @param userCallback
     */
    @POST("/users/updateUser")
    void updateCurrentUser(@Header("Authorization") String token, @Body User user, Callback<User> userCallback);

    /**
     *
     * @param token
     * @param user
     * @param callback
     */
    @POST("/users/changePassword")
    void updateCurrentUserPassword(@Header("Authorization") String token, @Body User user, Callback<String> callback);

}
