package fall2018.csc2017.GameCentre.ui.menu.view.leaderboard;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.view.View;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fall2018.csc2017.GameCentre.R;

class TabPresenter {

    // View Variables
    private View view;
    private Fragment frag;
    private String tab;

    // Firebase Variables
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference();
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // Database Path
    private final String LEADERBOARDS = "leaderboards";
    private final String ACCOUNTS = "accounts";
    private final String HIGHSCORE = "highScore";

    TabPresenter(View view, Fragment frag, String tab) {
        this.view = view;
        this.frag = frag;
        this.tab = tab;
    }

    /**
     * Updates the scores for the current game with data from the Firebase Database
     */
    void updateScores() {
        // Fetch from the database
        myRef.child(LEADERBOARDS).child(this.tab).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int[] idArr = {R.id.firstID, R.id.secondID, R.id.thirdID, R.id.forthID, R.id.fifthID,
                        R.id.sixthID, R.id.seventhID, R.id.eighthID, R.id.ninthID, R.id.tenthID};
                int[] nameArr = {R.string.first, R.string.second, R.string.third, R.string.forth,
                        R.string.fifth, R.string.sixth, R.string.seventh, R.string.eighth,
                        R.string.ninth, R.string.tenth};


                int idx = 0;
                for (DataSnapshot entryDataSnapshot : dataSnapshot.getChildren()) {
                    if(idx < idArr.length) {
                        TextView place = view.findViewById(idArr[idx]);
                        place.setText(frag.getString(nameArr[idx], "", 0));
                        LeaderboardEntry entry = entryDataSnapshot.getValue(LeaderboardEntry.class);
                        place.setText(frag.getString(nameArr[idx], entry.getUsername(), entry.getScore()));
                        idx++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    /**
     * Updates the text that displays your high score from the database for the current game
     */
    void updateMyScore() {
        myRef.child(ACCOUNTS).child(user.getUid()).child(this.tab).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(user.getUid());
                TextView myScore = view.findViewById(R.id.myScore);
                try {
                    myScore.setText(frag.getString(R.string.myScore, dataSnapshot.child(HIGHSCORE).getValue().toString()));
                } catch (NullPointerException n) {
                    // The user hasn't played yet
                    myScore.setText(frag.getString(R.string.myScore, "N/A"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
