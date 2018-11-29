package fall2018.csc2017.GameCentre.games.puzzle.presenter;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.List;

public class TileFirebaseConnection implements Serializable {

    // Database Path Variables
    private final String GAME = "slidingTiles";
    private final String ACCOUNTS = "accounts";
    private final String MOVES = "moveStr";
    private final String TIME = "time";
    private final String DIMENSIONS = "dimensions";
    private final String UNDOS = "undos";

    TileFirebaseConnection() {}

    /**
     * Saves the board manager to the current user's current game data on the database
     * @param manager the BoardManager to be saved
     */
    public void save(BoardManager manager) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference userRef = myRef.child(ACCOUNTS).child(user.getUid()).child(GAME);
        // Push all the user data to the database
        // TODO: Add time
        userRef.child(TIME).setValue("123:456:789");
        userRef.child(DIMENSIONS).setValue(Integer.toString(manager.getNumRows()) + "x" + Integer.toString(manager.getNumCols()));
        userRef.child(UNDOS).setValue(manager.getUndos());
        userRef.child(MOVES).push().setValue(manager.toString());
    }

    /**
     * Loads the current user's latest data from the current game tab in the database
     * @return the TileState associated with all the user's current data from the database
     */
    public TileState load() {
        final TileState state = new TileState();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        myRef.child(ACCOUNTS).child(user.getUid()).child(GAME).addValueEventListener(new ValueEventListener() {
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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        myRef.child(ACCOUNTS).child(user.getUid()).child(GAME).addValueEventListener(new ValueEventListener() {
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
