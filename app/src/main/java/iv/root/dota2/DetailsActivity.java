package iv.root.dota2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import iv.root.dota2.retrofit.AccountDTO;
import iv.root.dota2.retrofit.Player;
import iv.root.dota2.retrofit.PlayerDTO;
import iv.root.dota2.retrofit.ProfileDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    private static final String INTENT_TEAM_ID = "intent:team_id";
    private RecyclerView recyclerView;
    private List<PlayerDTO> players = new LinkedList<>();
    private PlayersAdapter adapter;

    public static void start(Activity activity, int teamId) {
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putExtra(INTENT_TEAM_ID, teamId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int teamID = bundle.getInt(INTENT_TEAM_ID);

            adapter = new PlayersAdapter(new LinkedList<>(), LayoutInflater.from(this));
            recyclerView = findViewById(R.id.listView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);

            App.getDotaAPI().getTeamPlayers(teamID).enqueue(new PlayersCallback());
        } else App.logE("Bundle is NULL");
    }

    class PlayersCallback implements Callback<List<PlayerDTO>> {
        @Override
        public void onResponse(Call<List<PlayerDTO>> call, Response<List<PlayerDTO>> response) {
            List<PlayerDTO> list = response.body();

            if (list != null) {
                players = list;
                Toast.makeText(DetailsActivity.this, ""+players.size(), Toast.LENGTH_SHORT).show();
                for (PlayerDTO player : list)
                    App.getDotaAPI().getPlayerAccount(player.getAccountID()).enqueue(new AccountCallback());
            } else
                App.logI("Body is NULL");
        }

        @Override
        public void onFailure(Call<List<PlayerDTO>> call, Throwable t) {
            App.logE(t.getMessage());
        }
    }

    class AccountCallback implements Callback<AccountDTO> {
        @Override
        public void onResponse(Call<AccountDTO> call, Response<AccountDTO> response) {
            AccountDTO account = response.body();
            if (account != null) {
                ProfileDTO profile = account.getProfile();
                if (profile != null) {
                    PlayerDTO player = findPlayer(profile.getAccountID());
                    if (player != null) {
                        Player p = new Player(player.getName(), profile.getAvatarURL());
                        adapter.append(p);
                    }
                }
            } else
                App.logI("Body is NULL");
        }

        @Override
        public void onFailure(Call<AccountDTO> call, Throwable t) {
            App.logE(t.getMessage());
        }
    }

    private PlayerDTO findPlayer(int accountID) {
        for (PlayerDTO player : players)
            if (player.getAccountID() == accountID)
                return player;
        return null;
    }
}
