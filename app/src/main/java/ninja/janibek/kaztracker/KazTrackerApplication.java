package ninja.janibek.kaztracker;

import android.app.Application;

import ninja.janibek.kaztracker.config.BasicCredential;
import ninja.janibek.kaztracker.config.Credential;
import ninja.janibek.kaztracker.network.ApiClient;
import ninja.janibek.kaztracker.utils.Logger;

/**
 * Created by janibek on 6/28/15.
 */
public class KazTrackerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.setLevel(Logger.Level.DEBUG);
        Credential credential = new BasicCredential(BuildConfig.BASE_URL);
        ApiClient.init(credential);
    }
}
