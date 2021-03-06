package com.projects.wens.kandoeteami.themes;

import com.projects.wens.kandoeteami.organisation.data.ChildItem;
import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.retrofit.service.ThemeService;
import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.session.data.SessionState;
import com.projects.wens.kandoeteami.themes.data.Theme;
import com.projects.wens.kandoeteami.user.data.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ThemeDetailPresenter implements ThemeDetailContract.UserActionListener {
    private final ThemeDetailContract.View view;
    private final ThemeService service;

    public ThemeDetailPresenter(ThemeDetailContract.View view, ThemeService service){
        this.view = view;
        this.service = service;
    }

    @Override
    public void loadTheme(final String token, final int id) {
        service.getTheme("Bearer " + token, id, new Callback<Theme>() {
            @Override
            public void success(Theme theme, Response response) {
                getUsersThema(theme, token, id);
                view.showSuccesMessage("Successfully loaded theme");
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getMessage());
            }
        });
    }

    private void getUsersThema(final Theme thema, String token, int id){
        final List<GroupItem> groupItems = new ArrayList<>();
        service.getSessionOfTheme("Bearer " + token, id, new Callback<List<SessionDTO>>() {
            @Override
            public void success(List<SessionDTO> sessionDTOs, Response response) {
                int inProgress = 0;
                for (SessionDTO sDTO: sessionDTOs){
                    GroupItem item = new GroupItem("SESSION " + sDTO.getSessionId() + " MEMBERS");
                    List<User> users = sDTO.getUsers();
                    for (User u: users){
                        ChildItem child = new ChildItem(u.getPerson().getFirstname(), u.getPerson().getLastname(), "MEMBER", u.getProfilePicture());
                        item.addChildren(child);
                    }
                    if (sDTO.getState() == SessionState.IN_PROGRESS){
                        inProgress++;
                    }
                    groupItems.add(item);
                }
                int dtoSize = sessionDTOs.size();
                view.showTheme(thema,groupItems, dtoSize, dtoSize);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getMessage());
            }
        });
    }


}
