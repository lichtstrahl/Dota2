package iv.root.dota2.retrofit;

import com.google.gson.annotations.SerializedName;

public class ProfileDTO {
    @SerializedName("avatarfull")
    String avatarURL;
    @SerializedName("account_id")
    int accountID;

    public String getAvatarURL() {
        return avatarURL;
    }

    public int getAccountID() {
        return accountID;
    }
}
