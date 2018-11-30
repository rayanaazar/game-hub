package fall2018.csc2017.GameCentre.games.puzzle.presenter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TileState implements Serializable {

    private Map<String, String> moves;
    private int undos;
    private String dimensions;
    private String highScore;

    TileState() {}

    TileState(Map<String, String> moves, int undos, String dimensions, String highScore) {
        this.moves = moves;
        this.undos = undos;
        this.dimensions = dimensions;
        this.highScore = highScore;
    }

    public String getHighScore() { return highScore; }

    public void setHighScore(String highScore) { this.highScore = highScore; }

    public String getLatestMoveStr() {
        return moves.get("-LSXvMWeF6S2yBIPwCnt");
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Map<String, String> getMoves() {
        return moves;
    }

    public void setMoves(Map<String, String>moves) {
        this.moves = moves;
    }

    public int getUndos() {
        return undos;
    }

    public void setUndos(int undos) {
        this.undos = undos;
    }
}
