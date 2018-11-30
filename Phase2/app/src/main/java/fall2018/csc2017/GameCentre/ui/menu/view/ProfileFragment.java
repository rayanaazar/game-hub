package fall2018.csc2017.GameCentre.ui.menu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.ui.login.view.LoginActivity;
import fall2018.csc2017.GameCentre.ui.menu.ProfileContract;
import fall2018.csc2017.GameCentre.ui.menu.presenter.ProfilePresenter;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    ProfilePresenter profilePresenter;
    Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        profilePresenter = new ProfilePresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        btn = Objects.requireNonNull(getView()).findViewById(R.id.logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePresenter.logout();
            }
        });
    }

    @Override
    public void redirectToLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}
