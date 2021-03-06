package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

public interface ThemeService {
    /**
     * rest call get themes
     * @param token
     * @param callback
     */
    @GET("/themes/currentUser")
    void getThemes(@Header("Authorization") String token, Callback<List<Theme>> callback);

    /**
     * rest call find themes by organisation id
     * @param token
     * @param organisationId
     * @param callback
     */
    @GET("/organisations/{id}/themes")
    void getThemesOfOrganisation(@Header("Authorization") String token,@Path("id") int organisationId, Callback<List<Theme>> callback);

    /**
     * rest call find theme by id
     * @param token
     * @param themeId
     * @param callback
     */
    @GET("/themes/{themeId}")
    void getTheme(@Header("Authorization") String token, @Path("themeId") int themeId, Callback<Theme> callback);

    /**
     * rest call find sessions' theme
     * @param token
     * @param themeId
     * @param callback
     */
    @GET("/sessions/theme/{themeId}")
    void getSessionOfTheme(@Header("Authorization") String token, @Path("themeId") int themeId, Callback<List<SessionDTO>> callback);

}
