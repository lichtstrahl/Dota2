package iv.root.dota2.retrofit;

import com.google.gson.annotations.SerializedName;

public class PlayerDTO {
    @SerializedName("account_id")
    int accountID;
    @SerializedName("name")
    String name;


    public int getAccountID() {
        return accountID;
    }

    public String getName() {
        return name;
    }
}
