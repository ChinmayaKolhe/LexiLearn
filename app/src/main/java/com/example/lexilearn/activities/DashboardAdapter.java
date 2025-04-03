package com.example.lexilearn.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lexilearn.R;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {
    private List<DashboardItem> itemList;
    private Context context;

    public DashboardAdapter(Context context, List<DashboardItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // FIX: Ensure the correct item layout file is used
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DashboardItem item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.icon.setImageResource(item.getIconResId());
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0; // Avoid NullPointerException
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            icon = itemView.findViewById(R.id.itemIcon);
        }
    }
}
