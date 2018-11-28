package fall2018.csc2017.GameCentre.games.puzzle.presenter;

import java.util.List;

public class TileState {

    private List<String> moves;
    private int undos;
    private String time;
    private String dimensions;

    TileState() {}

    TileState(List<String> moves, int undos, String time, String dimensions) {
        this.moves = moves;
        this.undos = undos;
        this.time = time;
        this.dimensions = dimensions;
    }
    
    public String getLatestMoveStr() {
        return moves.get(0);
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }

    public int getUndos() {
        return undos;
    }

    public void setUndos(int undos) {
        this.undos = undos;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
