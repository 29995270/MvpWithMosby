package com.wq.freeze.mvpwithmosby.view;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wangqi on 2016/3/17.
 */
public class SimpleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> dataSrc;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        return new RecyclerView.ViewHolder(textView){ };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(dataSrc.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSrc == null? 0 : dataSrc.size();
    }

    public List<String> getDataSrc() {
        return dataSrc;
    }

    public void setDataSrc(List<String> dataSrc) {
        this.dataSrc = dataSrc;
        notifyDataSetChanged();
    }

    public void appendDataSrc(String extra, boolean isRefresh) {

        if (dataSrc == null) return;

        if (isRefresh) {
            dataSrc.add(0, extra);
            notifyItemInserted(0);
        } else {
            dataSrc.add(extra);
            notifyItemInserted(dataSrc.size());
        }
    }
}
