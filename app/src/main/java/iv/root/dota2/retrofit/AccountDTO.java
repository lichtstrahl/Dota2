package iv.root.dota2.retrofit;

import com.google.gson.annotations.SerializedName;

public class AccountDTO {
    @SerializedName("leaderboard_rank")
    int rank;

    public int getRank() {
        return rank;
    }
}
