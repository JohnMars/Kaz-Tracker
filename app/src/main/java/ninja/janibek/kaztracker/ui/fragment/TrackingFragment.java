package ninja.janibek.kaztracker.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ninja.janibek.kaztracker.R;
import ninja.janibek.kaztracker.model.Tracking;
import ninja.janibek.kaztracker.network.ApiClient;
import ninja.janibek.kaztracker.network.Callback;
import ninja.janibek.kaztracker.ui.adapter.TrackingAdapter;
import ninja.janibek.kaztracker.utils.Logger;

/**
 * Created by janibek on 6/28/15.
 * janibek.marshal@gmail.com
 */
public class TrackingFragment extends BaseFragment {
    public static final String TAG = Logger.makeLogTag("TrackingFragment");

    private TrackingAdapter mAdapter;
    private List<Tracking> mTrackingList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTrackingList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tracking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new TrackingAdapter(getActivity(), mTrackingList);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_tracking_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
        getTracking();
    }

    private void getTracking() {
        ApiClient.getInstance().getTrackInfo("LJ676355265US", new Callback<Tracking>() {
            @Override
            public void onSuccess(Tracking result) {
                mTrackingList.add(result);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String reason, int code) {
                Logger.e(TAG, reason);
            }
        });
    }
}
