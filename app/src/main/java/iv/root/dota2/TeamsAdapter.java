package iv.root.dota2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import iv.root.dota2.retrofit.TeamDTO;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamViewHolder> {
    private List<TeamDTO> listTeams;
    private LayoutInflater inflater;
    private View.OnClickListener listener;

    public TeamsAdapter(List<TeamDTO> list, LayoutInflater inf, View.OnClickListener l) {
        listTeams = list;
        inflater = inf;
        listener = l;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TeamViewHolder(inflater.inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder teamViewHolder, int i) {
        teamViewHolder.bindNewsItemView(i);
    }

    @Override
    public int getItemCount() {
        return listTeams.size();
    }

    public void append(TeamDTO team) {
        listTeams.add(team);
        notifyItemInserted(listTeams.size()-1);
    }

    public TeamDTO getItem(int pos) {
        return listTeams.get(pos);
    }

    class TeamViewHolder extends RecyclerView.ViewHolder {
        private TextView viewName;
        private ImageView viewImage;

        TeamViewHolder(View view) {
            super(view);
            // Здесь находим все наши элементы
            viewName = view.findViewById(R.id.viewName);
            viewImage = view.findViewById(R.id.image);
            view.setOnClickListener(listener);
        }

        public void bindNewsItemView(int pos) {
            TeamDTO team = listTeams.get(pos);
            viewName.setText(team.getName());
            GlideApp.with(viewName.getContext()).load(team.getLogoURL()).into(viewImage);

            if (team.getLogoURL() == null)
                App.logI("NULL image URL: " + pos);
        }
    }
}
