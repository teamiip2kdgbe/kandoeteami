package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;


public interface SubThemaService {
    /**
     *
     * @param token
     * @param callback
     */
    @GET("/subThemes")
    void getSubThemes(@Header("Authorization") String token, Callback<List<SubTheme>> callback);

    /**
     *
     * @param token
     * @param id
     * @param callback
     */
    @GET("/subThemes/{themeId}")
    void getSubTheme(@Header("Authorization") String token, @Path("themeId") int id,Callback<SubTheme> callback);

    /**
     *
     * @param token
     * @param id
     * @param callback
     */
    @GET("/themes/{themeId}/subThemes")
    void getSubthemesByThemeId(@Header("Authorization") String token, @Path("themeId") int id, Callback<List<SubTheme>> callback);

    /**
     *
     * @param token
     * @param id
     * @param callback
     */
    @GET("/sessions/subtheme/{subThemeId}")
    void getSessionBySubThemeId(@Header("Authorization") String token, @Path("subThemeId") int id, Callback<List<SessionDTO>> callback);
}
