package iv.root.dota2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import iv.root.dota2.retrofit.PlayerDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    private static final String INTENT_TEAM_ID = "intent:team_id";

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
            App.getDotaAPI().getTeamPlayers(teamID).enqueue(new Callback<List<PlayerDTO>>() {
                @Override
                public void onResponse(Call<List<PlayerDTO>> call, Response<List<PlayerDTO>> response) {
                    Toast.makeText(DetailsActivity.this, "YES", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<List<PlayerDTO>> call, Throwable t) {
                    Toast.makeText(DetailsActivity.this, "NO", Toast.LENGTH_SHORT).show();
                }
            });

        } else App.logE("Bundle is NULL");

    }
}
