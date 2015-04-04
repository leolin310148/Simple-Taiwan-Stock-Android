package me.leolin.stock.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;
import com.google.inject.Inject;
import me.leolin.stock.R;
import me.leolin.stock.business.UserService;
import me.leolin.stock.core.AppConfig;
import me.leolin.stock.util.FacebookCallbackWrapper;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

import java.util.Arrays;

/**
 * @author leolin
 */
public class DrawerMenuFragment extends RoboFragment {

    private static final String LOG_TAG = DrawerMenuFragment.class.getSimpleName();

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;

    @InjectView(R.id.profilePictureView)
    private ProfilePictureView profilePictureView;
    @InjectView(R.id.loginButton)
    private Button loginButton;
    @InjectView(R.id.textViewUserName)
    private TextView textViewUserName;
    @InjectView(R.id.textViewMenuIndustry)
    private TextView textViewMenuIndustry;

    @Inject
    private UserService userService;


    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     * @param toolbar      The Toolbar of the activity.
     */
    public void setup(DrawerLayout drawerLayout, Toolbar toolbar) {
        mDrawerLayout = drawerLayout;

        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.myPrimaryDarkColor));

        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) return;
                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) return;
                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(() -> mActionBarDrawerToggle.syncState());

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, FacebookCallbackWrapper.<LoginResult>create()
                .doOnSuccess(loginResult -> {
                    updateUi();
                    userService.refreshUser();
                }).doOnError(e -> Toast.makeText(getActivity(), "無法登入", Toast.LENGTH_SHORT).show()));

        loginButton.setOnClickListener(v -> {
            AccessToken token = AccessToken.getCurrentAccessToken();
            if (token == null) {
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends", "email"));
            } else {
                LoginManager.getInstance().logOut();
            }
        });


        View.OnClickListener menuClickListener = v -> {
            mDrawerLayout.closeDrawers();
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(AppConfig.ACTION_SELECT_MENU).putExtra(AppConfig.EXTRA_SELECT_MENU, v.getId()));
        };
        textViewMenuIndustry.setOnClickListener(menuClickListener);

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                updateUi();
            }
        };

        updateUi();
        userService.refreshUser();
    }


    private void updateUi() {
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            profilePictureView.setProfileId(profile.getId());
            textViewUserName.setText(profile.getName());
            loginButton.setText(R.string.button_text_logout);

        } else {
            profilePictureView.setProfileId(null);
            textViewUserName.setText(R.string.info_not_login);
            loginButton.setText(R.string.button_text_login);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }


}
