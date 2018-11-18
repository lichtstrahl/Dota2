package iv.root.dota2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.LinkedList;
import java.util.List;

import iv.root.dota2.retrofit.TeamDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    RecyclerView listView;
    TeamsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        adapter = new TeamsAdapter(new LinkedList<>(), LayoutInflater.from(this), view -> {
            int pos = listView.getChildAdapterPosition(view);
            TeamDTO team = adapter.getItem(pos);
            DetailsActivity.start(this, team.getTeamID());
        });

        listView = findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listView.setAdapter(adapter);

        App.getDotaAPI().getAllTeam().enqueue(new TeamCallback());
    }

    class TeamCallback implements Callback<List<TeamDTO>> {
        @Override
        public void onResponse(Call<List<TeamDTO>> call, Response<List<TeamDTO>> response) {
            App.logI("Successful response");
            List<TeamDTO> teams = response.body();
            if (teams != null) {
                for (TeamDTO team : teams)
                    adapter.append(team);

            } else
                App.logI("Body is NULL");
        }

        @Override
        public void onFailure(Call<List<TeamDTO>> call, Throwable t) {
            App.logE(t.getMessage());
        }
    }
}
