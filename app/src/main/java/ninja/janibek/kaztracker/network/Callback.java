package ninja.janibek.kaztracker.network;

/**
 * Created by janibek on 6/30/15.
 * Version 1.0
 */
public interface Callback<T> {

    void onSuccess(T result);

    void onError(String reason, int code);
}
