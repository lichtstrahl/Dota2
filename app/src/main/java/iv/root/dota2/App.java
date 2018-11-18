package iv.root.dota2;

import android.app.Application;
import android.provider.Settings;
import android.util.Log;

import iv.root.dota2.retrofit.Dota2API;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {
    private static Retrofit retrofit;
    private static final String GLOBAL_TAG = "DOTA2";
    private static final String URL = "https://api.opendota.com";

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
    public static Dota2API getDotaAPI() {
        return retrofit.create(Dota2API.class);
    }

    public static void logI(String msg) {
        Log.i(GLOBAL_TAG, msg);
    }

    public static void logE(String msg) {
        Log.e(GLOBAL_TAG, msg);
    }
}
