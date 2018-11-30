package fall2018.csc2017.GameCentre.games.puzzle.presenter;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TileFirebaseConnection {

    // Database Path Variables
    private static final String GAME = "slidingTiles";
    private static final String ACCOUNTS = "accounts";
    private static final String MOVES = "moveStr";
    private static final String TIME = "time";
    private static final String DIMENSIONS = "dimensions";
    private static final String UNDOS = "undos";

    // Firebase Variables
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference myRef = database.getReference();
    private static final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static final DatabaseReference userRef = myRef.child(ACCOUNTS).child(user.getUid()).child(GAME);

    public static TileState loadState;

    public static boolean canLoad() {
        return (userRef.child(MOVES).getKey() != null);
    }

    /**
     * Saves the board manager to the current user's current game data on the database
     * @param manager the BoardManager to be saved
     */
    public static void save(BoardManager manager) {
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
    public static void saveRegular(BoardManager manager) {
        save(manager);
        userRef.child(MOVES).push().setValue(manager.getBoard().toString());
    }

    /**
     * Saves the board manager to the current user's current game data on the database.
     * This only happens on the initial save, overwriting previous moveStrings
     * @param manager the BoardManager to be saved
     */
    public static void saveInit(BoardManager manager) {
        save(manager);
        // Remove any old values
        userRef.child(MOVES).removeValue();
        userRef.child(MOVES).push().setValue(manager.getBoard().toString());
    }

    /**
     * Loads the current user's latest data from the current game tab in the database
     * @return the TileState associated with all the user's current data from the database
     */
    public static void load() {
        ValueEventListener event = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loadState = dataSnapshot.getValue(TileState.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        };
        userRef.addListenerForSingleValueEvent(event);
    }

    /**
     * Loads the user's previous data from the current game tab in the database
     * @return the TileState associated with all the user's previous data from the database
     */
    public static TileState loadPrevMoves() {
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
    public static int getScore() {
        // TODO: incorporate time into the score
        load();
        List<String> moves = loadState.getMoves();
        // If we havent made any moves yet, score is 0
        if(moves == null) {
            return 0;
        }
        return load().getMoves().size() - 1;
    }
}
