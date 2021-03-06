package com.projects.wens.kandoeteami.themes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.ListOrganisationActivity;
import com.projects.wens.kandoeteami.session.ListSessionActivity;
import com.projects.wens.kandoeteami.session.SessionGameActivity;
import com.projects.wens.kandoeteami.start.SplashActivity;
import com.projects.wens.kandoeteami.user.UserActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class ListThemeActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    public static final String PREFS_NAME = "MyPrefs";
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.menu_actions);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.theme_menu_search:
                        break;
                    case R.id.theme_menu_filter:
                }
                return false;
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);



        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0, 0);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().openOptionsMenu();
        mDrawerToggle.syncState();

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        if (null == savedInstanceState) {
            Bundle b = getIntent().getExtras();
            Boolean all = b.getBoolean("allThemes");
            int organisationId = b.getInt("organisationId");
            initFragment(ListThemeFragment.newInstance(all,organisationId));
        }

        String firstname = this.getSharedPreferences(PREFS_NAME, 0).getString("firstname", null);
        String lastname = this.getSharedPreferences(PREFS_NAME, 0).getString("lastname", null);
        String email = this.getSharedPreferences(PREFS_NAME, 0).getString("email", null);
        TextView tvUsername = (TextView) findViewById(R.id.nav_username);
        tvUsername.setText(firstname+" "+lastname);
        TextView tvEmail = (TextView) findViewById(R.id.nav_email);
        tvEmail.setText(email);

        CircleImageView circleImage = (CircleImageView) findViewById(R.id.profile_image);
        String profilePictureUrl = this.getSharedPreferences(PREFS_NAME,0).getString("profilepicture", null);
        if (profilePictureUrl != null || profilePictureUrl != ""){
            Picasso.with(this.getApplicationContext()).load(PICASSO_BASEURL + profilePictureUrl).into(circleImage);
        }
    }


    private void initFragment(Fragment themeFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, themeFragment);
        transaction.commit();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        //Hier gaan de menu opties uitgewerkt worden
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Intent i;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_ic_organisaitons:
                                i = new Intent(getApplicationContext(), ListOrganisationActivity.class);

                                startActivity(i);
                                finish();
                                break;
                            case R.id.nav_ic_profile:
                                i = new Intent(getApplicationContext(), UserActivity.class);

                                startActivity(i);
                                finish();
                                break;
                            case R.id.nav_ic_logout:
                                //Removing token
                                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("token", "");
                                editor.remove("token");

                                // Commit the edits!
                                editor.commit();

                                i = new Intent(getApplicationContext(), SplashActivity.class);
                                startActivity(i);
                                finish();
                                break;
                            case R.id.nav_ic_kandoes:
                                i = new Intent(getApplicationContext(), ListSessionActivity.class);
                                startActivity(i);
                                finish();
                                break;
                            case R.id.nav_ic_themes:
                                i = new Intent(getApplicationContext(), ListThemeActivity.class);
                                i.putExtra("allThemes", true);
                                i.putExtra("organisationId",0);
                                startActivity(i);
                                finish();
                                break;
                            case R.id.nav_ic_help:
                                break;
                            case R.id.nav_ic_about:
                                break;

                        }
                        return false;
                    }
                }
        );
    }
}
