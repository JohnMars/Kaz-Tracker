package ninja.janibek.kaztracker.config;

import android.support.annotation.NonNull;

/**
 * Created by janibek on 6/29/15.
 * Version 1.0
 */
public class BasicCredential extends Credential {

    private final String mEndPoint;

    public BasicCredential(String mEndPoint) {
        this.mEndPoint = mEndPoint;
    }

    @NonNull
    @Override
    public String getEndpoint() {
        return mEndPoint;
    }
}
