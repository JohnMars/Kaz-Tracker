package ninja.janibek.kaztracker.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import ninja.janibek.kaztracker.config.Credential;
import ninja.janibek.kaztracker.model.Tracking;

/**
 * Created by janibek on 6/29/15.
 * janibek.marshal@gmail.com
 */
public class ApiClient {

    public static final String TRACK_ONE = "/api/v1/%s";
    public static final String TRACK_EVENT_ONE = "/api/v1/%s/event";

    private static ApiClient sClient;
    private static AsyncHttpClient sAsyncClient;
    private final String endPoint;

    public static void init(Credential credential) {
        sClient = new ApiClient(credential);
        sAsyncClient = new AsyncHttpClient();
    }

    protected ApiClient(Credential credential) {
        this.endPoint = credential.getEndpoint();
    }

    public static ApiClient getInstance() {
        return sClient;
    }

    public void getTrackInfo(String trackNumber, final Callback<Tracking> callback) {
        String method = String.format(TRACK_ONE, trackNumber);
        sAsyncClient.get(endPoint + method, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Tracking tracking = ApiParcel.parceTracking(response);
                    callback.onSuccess(tracking);
                } catch (JSONException e) {
                    callback.onError(e.getLocalizedMessage(), statusCode);
                }
            }
        });
    }
}
