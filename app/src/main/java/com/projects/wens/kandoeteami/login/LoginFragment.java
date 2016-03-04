package com.projects.wens.kandoeteami.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.ListOrganisationActivity;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.LoginService;
import com.projects.wens.kandoeteami.retrofit.service.UserService;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class LoginFragment extends Fragment implements LoginContract.view {
    private LoginContract.UserActionListener mLoginActionListener;
    private LoginService service;
    private UserService userService;
    public static final String PREFS_NAME = "MyPrefs";
    private ProgressDialog progressDialog;
    private static final CallbackManager CALLBACK_MANAGER = CallbackManager.Factory.create();;

    //DECLARATION COMPONENTS
    private Button mLogin_button;
    private LoginButton loginButtonFB;
    private EditText mUsername;
    private EditText mPassword;



    public LoginFragment(){
        //Requires empty public contructor
    }

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null){
            String email = getActivity().getIntent().getExtras().getString("EMAIL");
            mUsername.setText(email);
        }

        service = ServiceGenerator.createService(LoginService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        userService = ServiceGenerator.createService(UserService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        mLoginActionListener = new LoginPresenter(this,service, userService);


        // Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getActivity());
        // especially, if you're using Facebook UI elements.


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        //SharedPreferences om user
        String username = getActivity().getSharedPreferences(PREFS_NAME,0).getString("username", null);
        mUsername = (EditText) root.findViewById(R.id.login_username);
        mUsername.setText(username);
        mPassword = (EditText) root.findViewById(R.id.login_password);
        mLogin_button = (Button) root.findViewById(R.id.btnLogin);
        mLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginActionListener.login();
            }
        });
        loginButtonFB = (LoginButton) root.findViewById(R.id.login_button_facebook);

        loginButtonFB.registerCallback(CALLBACK_MANAGER, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                mLoginActionListener.loginWithFacebook(profile.getName());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                showErrorMessage(error.getMessage());
            }
        });
        return root;
    }

    public void onResume(){
        super.onResume();
    }

    @Override
    public void showErrorMessage(String message) {
        //Crouton
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        //Crouton
        Crouton.makeText(getActivity(), message, Style.CONFIRM).show();
    }

    @Override
    public void showOrganisationsActivity() {

        Intent i = new Intent(getContext(), ListOrganisationActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @Override
    public void saveToken(String token) {
        // Storing token
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", token);

        // Commit the edits!
        editor.commit();
    }



    @Override
    public String getUsername() {
        return mUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }

    @Override
    public void showProgressLogin() {
        progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }

    @Override
    public void stopProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showUsernameError(String message) {
        mUsername.setError(message);
    }

    @Override
    public void showPasswordError(String message) {
        mPassword.setError(message);
    }

    @Override
    public void saveUserDetails(String username,String profilepicture) {
        // Storing token
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", username);
        editor.putString("profilepicture", profilepicture);

        // Commit the edits!
        editor.commit();
    }
}
