package fall2018.csc2017.GameCentre.MyScores;

public class UserScore implements MyScoresContract.Model {

    private String userId;
    private int STScore;
    private int TTTScore;
    private int MTScore;

    UserScore(String userId) {
        this.userId = userId;
        // TODO: Fetch from firebase
        this.STScore = 12;
        this.TTTScore = 0;
        this.MTScore = 0;
    }

    public String getUserId() { return userId; }

    public int getSTScore() { return STScore; }

    public int getTTTScore() { return TTTScore; }

    public int getMTScore() { return MTScore; }
}
