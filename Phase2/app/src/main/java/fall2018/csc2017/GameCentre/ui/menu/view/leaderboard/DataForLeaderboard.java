package fall2018.csc2017.GameCentre.ui.menu.view.leaderboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import fall2018.csc2017.GameCentre.R;

public class DataForLeaderboard extends Fragment {

    private TabPresenter presenter;
    TableView<String[]> tableView;

    static String GAME;
    FloatingActionButton floatingBtn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.leaderboard_tab_fragment, container,false);
        tableView =  v.findViewById(R.id.tableView);
        floatingBtn =  v.findViewById(R.id.fab);

        presenter = new TabPresenter(v, this, GAME);
        TableView<String[]> tableView = v.findViewById(R.id.tableView);
        tableView.setHeaderBackgroundColor(Color.parseColor("#B0BEC5"));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), presenter.getprobeHeaders()));
        tableView.setColumnCount(3);
        presenter.updateMyScore();
        tableView.setDataAdapter(new SimpleTableDataAdapter(getActivity(), presenter.fetchData()));
        updateData();
        return v;
    }

    private void updateData()
    {
        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableView.setDataAdapter(new SimpleTableDataAdapter(getActivity(), presenter.fetchData()));

            }
        });
    }

}
