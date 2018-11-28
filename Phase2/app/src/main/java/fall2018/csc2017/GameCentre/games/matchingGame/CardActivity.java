package fall2018.csc2017.GameCentre.games.matchingGame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fall2018.csc2017.GameCentre.R;

public class CardActivity extends AppCompatActivity {

    CardPresenter cardPresenter;

    CardView cardView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_game_card);

        cardView = findViewById(R.id.cardButton);
        cardPresenter = new CardPresenter(5, 4, cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView.flip();
            }
        });
    }

}
