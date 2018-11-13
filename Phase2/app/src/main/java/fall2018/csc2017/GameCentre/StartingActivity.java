package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

/**
 * The initial activity_tiles_scores for the sliding puzzle tile game.
 */
public class StartingActivity extends ActivityReaderWriter {

    /**
     * The board manager for this activity_tiles_scores
     */
    BoardManager boardManager;

    /**
     * The authentication manager
     */
    private FirebaseAuth auth;

    /**
     * The onCreate method for StartingActivity
     * @param savedInstanceState the savedInstanceState to be used
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        createMenu();
    }

    /**
     * Create the main menu using CircleMenu
     */
    private void createMenu() {
        CircleMenu circleMenu = findViewById(R.id.circleMenu);
        int color = Color.parseColor("#5BC348");
        circleMenu.setMainMenu(color, R.drawable.add, R.drawable.remove);
        circleMenu.addSubMenu(color, R.drawable.game);
        circleMenu.addSubMenu(color, R.drawable.load);
        circleMenu.addSubMenu(color, R.drawable.account);
        circleMenu.addSubMenu(color, R.drawable.add);
        circleMenu.addSubMenu(color, R.drawable.logout);
        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {
                if (i == 0) {
                    switchToDifficulty();
                } else if (i == 1) {
                    executeLoadGameFunctionality();
                } else if (i == 2) {
                    startActivity(new Intent(StartingActivity.this, ProfileMenu.class));
                } else if (i == 3) {
                    startActivity(new Intent(StartingActivity.this, ScoresActivity.class));
                } else {
                    logOut();
                }
            }

        });
    }


    /**
     * Activate the load button.
     */
    private void executeLoadGameFunctionality() {
        boardManager.setMoveStack(load());
        makeToastLoadedText();
        switchToGame();
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToDifficulty() {
        Intent tmp = new Intent(this, DifficultyActivity.class);
        startActivity(tmp);
    }

    /**
     * Log out from firebase
     */
    public void logOut() {
        auth.signOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }


}
