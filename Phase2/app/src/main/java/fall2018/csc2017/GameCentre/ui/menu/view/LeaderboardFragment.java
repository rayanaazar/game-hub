package fall2018.csc2017.GameCentre.ui.menu.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import fall2018.csc2017.GameCentre.R;

public class LeaderboardFragment extends Fragment  {

    private static final String TAG = LeaderboardFragment.class.getSimpleName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tabView = inflater.inflate(R.layout.leaderboard_fragment, container, false);

        FragmentTabHost tabHost = tabView.findViewById(android.R.id.tabhost);

        tabHost.setup(getActivity(), getChildFragmentManager(), R.id.tabcontentData);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("TTT"), TTTTab.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Puzzle"), PuzzleTab.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Matching Game"), MatchingGameTab.class, null);

        return tabView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
