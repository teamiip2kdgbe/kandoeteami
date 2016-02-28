package com.projects.wens.kandoeteami.register;

import com.projects.wens.kandoeteami.register.data.RegisterDTO;
import com.projects.wens.kandoeteami.retrofit.service.RegisterService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by michaelkees on 28/02/16.
 */
public class RegisterPresenter implements RegisterContract.UserActionListener {
    private final RegisterContract.view view;
    private final RegisterService service;

    public RegisterPresenter(RegisterContract.view view, RegisterService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void register() {
        RegisterDTO registerDTO = new RegisterDTO();
        String email = view.getEmail();
        String password = view.getPassword();
        String username = view.getUsername();

        if (validate()){
            registerDTO.setUsername(username);
            registerDTO.setEmail(email);
            registerDTO.setPassword(password);

            service.register(registerDTO, new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    view.showSuccessMessage(s);
                    view.showLoginActivity();
                }

                @Override
                public void failure(RetrofitError error) {
                    view.showErrorMessage(error.getMessage());
                }
            });
        }

    }

    private boolean validate() {
        if(view.getEmail().isEmpty()){
            view.showEmailError("Email is empty");
            return false;
        }

        if(view.getPassword().isEmpty()){
            view.showPasswordError("Password is empty!");
            return false;
        }

        if(!view.getRetypePassword().equals(view.getPassword())){
            view.showRetypePasswordError("Passwords do not match!");
            return false;
        }
        return true;
    }
}
