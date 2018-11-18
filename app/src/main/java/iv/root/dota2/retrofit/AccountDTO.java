package iv.root.dota2.retrofit;

import com.google.gson.annotations.SerializedName;

public class AccountDTO {
    @SerializedName("profile")
    ProfileDTO profile;

    public ProfileDTO getProfile() {
        return profile;
    }
}
