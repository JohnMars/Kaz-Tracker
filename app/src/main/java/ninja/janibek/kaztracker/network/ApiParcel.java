package ninja.janibek.kaztracker.network;

import org.json.JSONException;
import org.json.JSONObject;

import ninja.janibek.kaztracker.model.Tracking;

/**
 * Created by janibek on 7/20/15.
 * janibek.marshal@gmail.com
 */
class ApiParcel {

    private static final String TRACK_ID = "trackid";
    private static final String TIMESTAMP = "timestamp";
    private static final String REG_DATE = "reg_date";
    private static final String RECIPIENT_NAME = "recipient_name";
    private static final String RECIPIENT_ADDRESS = "recipient_address";
    private static final String DLV_DEP_NAME = "dlv_dep_name";
    private static final String DLV_POST_INDEX = "dlv_postindex";
    private static final String POST_TYPE = "post_type";
    private static final String X_STATUS = "x_status";

    public static Tracking parceTracking(JSONObject json) throws JSONException {
        String trackId = json.getString(TRACK_ID);
        String timestamp = json.getString(TIMESTAMP);
        Tracking.Builder builder = new Tracking.Builder(trackId, timestamp);

        String value;
        int intValue;
        if (json.has(REG_DATE)) {
            value = json.getString(REG_DATE);
            builder.setRegDate(value);
        }
        if (json.has(RECIPIENT_NAME)) {
            value = json.getString(RECIPIENT_NAME);
            builder.setRecipientName(value);
        }
        if (json.has(RECIPIENT_ADDRESS)) {
            value = json.getString(RECIPIENT_ADDRESS);
            builder.setRecipientName(value);
        }
        if (json.has(DLV_DEP_NAME)) {
            value = json.getString(DLV_DEP_NAME);
            builder.setDlbDepName(value);
        }
        if (json.has(DLV_POST_INDEX)) {
            value = json.getString(DLV_POST_INDEX);
            builder.setDlbPostIndex(value);
        }
        if (json.has(POST_TYPE)) {
            intValue = json.getInt(POST_TYPE);
            builder.setPostType(intValue);
        }
        if (json.has(X_STATUS)) {
            value = json.getString(X_STATUS);
            builder.setxStatus(value);
        }

        return builder.build();
    }
}
