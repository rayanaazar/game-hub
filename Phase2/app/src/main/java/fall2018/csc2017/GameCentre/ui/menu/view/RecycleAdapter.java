/*
 * Adapted from : https://github.com/mitchtabian/Recyclerview/blob/master/HorizontalRecyclerView/app/src/main/java/pluralsight/com/horizontalrecyclerview/RecyclerViewAdapter.java
 *
 * The following adapter will be incorporated for creating a horizontal recycle view for the games
 */

package fall2018.csc2017.GameCentre.ui.menu.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;
import fall2018.csc2017.GameCentre.R;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();
    private ArrayList<String> games;
    private ArrayList<String> imagesForGames;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> games, ArrayList<String> gameImages) {
        this.games = games;
        this.imagesForGames = gameImages;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gameitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(context)
                .asBitmap()
                .load(imagesForGames.get(position))
                .into(holder.image);

        holder.name.setText(games.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + games.get(position));
                Toast.makeText(context, games.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesForGames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
        }
    }
}