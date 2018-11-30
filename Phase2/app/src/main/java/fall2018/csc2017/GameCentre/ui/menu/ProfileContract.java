package fall2018.csc2017.GameCentre.ui.menu;

public interface ProfileContract {
    interface View
    {
        void redirectToLogin();
    }
    interface Presenter
    {
        void logout();
    }
}
