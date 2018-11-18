package iv.root.dota2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import iv.root.dota2.retrofit.PlayerDTO;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {
    private List<PlayerDTO> list;
    private LayoutInflater inflater;

    public PlayersAdapter(List<PlayerDTO> l, LayoutInflater i) {
        list = l;
        inflater = i;
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PlayersViewHolder(inflater.inflate(R.layout.player_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder playersViewHolder, int i) {
        playersViewHolder.bindPlayer(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void append(PlayerDTO player) {
        list.add(player);
        notifyItemInserted(list.size()-1);
    }


    class PlayersViewHolder extends RecyclerView.ViewHolder {
        private TextView viewName;

        public PlayersViewHolder(View view) {
            super(view);

            viewName = view.findViewById(R.id.viewName);
        }

        void bindPlayer(int pos) {
            PlayerDTO player = list.get(pos);
            viewName.setText(player.getName());
        }
    }
}
