package iv.root.dota2.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Dota2API {
    @GET("/api/teams")
    Call<List<TeamDTO>> getAllTeam();

    @GET("/api/proPlayers")
    Call<List<PlayerDTO>> getAllPlayers();

    @GET("/api/teams/{teamID}/players")
    Call<List<PlayerDTO>> getTeamPlayers(@Path("teamID") int teamID);

    @GET("/api/players/{account_id}")
    Call<AccountDTO> getPlayerAccount(@Path("account_id") int accountID);
}
