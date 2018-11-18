package iv.root.dota2.retrofit;

public class Player {
    private String name;
    private String avatarURL;

    public Player(String n, String url) {
        name = n;
        avatarURL = url;
    }

    public String getName() {
        return name;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
}
