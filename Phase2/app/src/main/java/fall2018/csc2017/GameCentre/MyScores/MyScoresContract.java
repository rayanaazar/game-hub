package fall2018.csc2017.GameCentre.MyScores;

public interface MyScoresContract {

    interface Model {
        int getSTScore();
        int getTTTScore();
        int getMTScore();
    }
    interface View {
        void displayScores();
    }
    interface Presenter {
        void updateScores();
    }
}
