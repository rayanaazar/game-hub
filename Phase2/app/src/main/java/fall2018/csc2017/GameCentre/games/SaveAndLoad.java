package fall2018.csc2017.GameCentre.games;

/**
 * The general interface for reading and writing files
 */

public interface SaveAndLoad {

    /**
     * Save the game data from the current game into the database
     */
    void save();

    /**
     * Load the latest game from the database
     */
    void load();
}