package iv.root.dota2.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dota2API {
    @GET("/api/teams")
    Call<List<TeamDTO>> getAllTeam();

    @GET("/api/proPlayers")
    Call<List<PlayerDTO>> getAllPlayers();
}
