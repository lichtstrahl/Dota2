package iv.root.dota2.retrofit;

import com.google.gson.annotations.SerializedName;

public class TeamDTO {
    @SerializedName("name")
    String name;

    public String getName() {
        return name;
    }
}
