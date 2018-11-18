package iv.root.dota2.retrofit;

import com.google.gson.annotations.SerializedName;

public class TeamDTO {
    @SerializedName("name")
    String name;
    @SerializedName("logo_url")
    String logoURL;
    @SerializedName("team_id")
    int teamID;

    public String getName() {
        return name;
    }
    public String getLogoURL() {
        return logoURL;
    }
    public int getTeamID() {
        return teamID;
    }
}
