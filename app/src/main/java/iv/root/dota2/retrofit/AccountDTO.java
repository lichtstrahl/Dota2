package iv.root.dota2.retrofit;

import com.google.gson.annotations.SerializedName;

public class AccountDTO {
    @SerializedName("leaderboard_rank")
    int rank;

    @SerializedName("profile")
    ProfileDTO profile;

    public int getRank() {
        return rank;
    }

    public ProfileDTO getProfile() {
        return profile;
    }
}
