package fall2018.csc2017.GameCentre.ui.menu.presenter;

import com.google.firebase.auth.FirebaseAuth;

import fall2018.csc2017.GameCentre.ui.menu.ProfileContract;

public class ProfilePresenter implements ProfileContract.Presenter {

    private final FirebaseAuth auth;
    private ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        this.auth = FirebaseAuth.getInstance();
    }

    @Override
    public void logout() {
        auth.signOut();
        view.redirectToLogin();
    }
}
