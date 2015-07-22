package ninja.janibek.kaztracker.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ninja.janibek.kaztracker.R;
import ninja.janibek.kaztracker.model.Tracking;

/**
 * Created by janibek on 7/19/15.
 * janibek.marshal@gmail.com
 */
public class TrackingAdapter extends RecyclerView.Adapter<TrackingAdapter.TrackingViewHolder> {

    private List<Tracking> mTrackingList;
    private LayoutInflater inflater;

    public TrackingAdapter(@NonNull Context context, @Nullable List<Tracking> trackingList) {
        this.mTrackingList = trackingList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public TrackingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_item_tracking, parent, false);
        return new TrackingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackingViewHolder holder, int position) {
        Tracking tracking = mTrackingList.get(position);
        holder.bindViewHolder(tracking);
    }

    @Override
    public int getItemCount() {
        return mTrackingList != null ? mTrackingList.size() : 0;
    }

    protected static class TrackingViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.layout_item_tracking_text_view_id)
        TextView trackingIdView;
        @InjectView(R.id.layout_item_tracking_text_view_status)
        TextView trackingStatusView;

        public TrackingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void bindViewHolder(Tracking tracking) {
            trackingIdView.setText(tracking.getTrackerId());
            trackingStatusView.setText(tracking.getxStatus());
        }
    }
}
