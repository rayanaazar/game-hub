package fall2018.csc2017.GameCentre.Leaderboard;

public interface LeaderboardContract {

    interface View{
        void displayScores();
        void displayName();
    }

    interface Presenter{
        void updateScores();
        void updateName();
    }
}
