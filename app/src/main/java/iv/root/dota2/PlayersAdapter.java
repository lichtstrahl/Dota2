package iv.root.dota2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import iv.root.dota2.retrofit.Player;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {
    private List<Player> list;
    private LayoutInflater inflater;

    public PlayersAdapter(List<Player> l, LayoutInflater i) {
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

    public void append(Player player) {
        list.add(player);
        notifyItemInserted(list.size()-1);
    }


    class PlayersViewHolder extends RecyclerView.ViewHolder {
        private TextView viewName;
        private ImageView viewAvatar;

        public PlayersViewHolder(View view) {
            super(view);

            viewName = view.findViewById(R.id.viewName);
            viewAvatar = view.findViewById(R.id.viewAvatar);
        }

        void bindPlayer(int pos) {
            Player player = list.get(pos);
            viewName.setText(player.getName());

            GlideApp.with(viewAvatar.getContext()).load(player.getAvatarURL()).into(viewAvatar);
        }
    }
}
