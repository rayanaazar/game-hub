package fall2018.csc2017.GameCentre.games.puzzle.presenter;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class TileFirebaseConnection {

    // Database Path Variables
    private final String GAME = "slidingTiles";
    private final String ACCOUNTS = "accounts";
    private final String MOVES = "moveStr";
    private final String TIME = "time";
    private final String DIMENSIONS = "dimensions";
    private final String UNDOS = "undos";

    // Firebase Variables
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference();
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private final DatabaseReference userRef = myRef.child(ACCOUNTS).child(user.getUid()).child(GAME);

    /**
     * Saves the board manager to the current user's current game data on the database
     * @param manager the BoardManager to be saved
     */
    public void save(BoardManager manager) {
        DatabaseReference userRef = myRef.child(ACCOUNTS).child(user.getUid()).child(GAME);
        // Push all the user data to the database
        // TODO: Add time
        userRef.child(TIME).setValue("123:456:789");
        userRef.child(DIMENSIONS).setValue(Integer.toString(manager.getNumRows()) + "x" + Integer.toString(manager.getNumCols()));
        userRef.child(UNDOS).setValue(manager.getUndos());
    }

    /**
     * Saves the board manager to the current user's current game data on the database
     * @param manager the BoardManager to be saved
     */
    public void saveRegular(BoardManager manager) {
        save(manager);
        userRef.child(MOVES).push().setValue(manager.getBoard().toString());
    }

    /**
     * Saves the board manager to the current user's current game data on the database.
     * This only happens on the initial save, overwriting previous moveStrings
     * @param manager the BoardManager to be saved
     */
    public void saveInit(BoardManager manager) {
        save(manager);
        // Remove any old values
        userRef.child(MOVES).removeValue();
        userRef.child(MOVES).push().setValue(manager.getBoard().toString());
    }

    /**
     * Loads the current user's latest data from the current game tab in the database
     * @return the TileState associated with all the user's current data from the database
     */
    public TileState load() {
        final TileState state = new TileState();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TileState currState = dataSnapshot.getValue(TileState.class);
                // Give state all of currState's attributes
                state.setMoves(currState.getMoves());
                state.setUndos(currState.getUndos());
                state.setTime(currState.getTime());
                state.setDimensions(currState.getDimensions());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return state;
    }

    /**
     * Loads the user's previous data from the current game tab in the database
     * @return the TileState associated with all the user's previous data from the database
     */
    public TileState loadPrevMoves() {
        final TileState state = new TileState();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TileState currState = dataSnapshot.getValue(TileState.class);
                // Give all the moves, except the last
                state.setMoves(currState.getMoves().subList(0, currState.getMoves().size() - 2));
                state.setUndos(currState.getUndos());
                state.setTime(currState.getTime());
                state.setDimensions(currState.getDimensions());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return state;
    }

    /**
     * Gets score atributes from the database, and calculates the score.
     * @return the current score
     */
    public int getScore() {
        // TODO: incorporate time into the score
        List<String> moves = load().getMoves();
        // If we havent made any moves yet, score is 0
        if(moves == null) {
            return 0;
        }
        return load().getMoves().size() - 1;
    }
}
