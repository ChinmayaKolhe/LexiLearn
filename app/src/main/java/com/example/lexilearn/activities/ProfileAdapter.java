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

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private Context context;
    private List<ProfileItem> profileItemList;

    public ProfileAdapter(Context context, List<ProfileItem> profileItemList) {
        this.context = context;
        this.profileItemList = profileItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_profile_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfileItem item = profileItemList.get(position);
        holder.menuTitle.setText(item.getTitle());
        holder.menuIcon.setImageResource(item.getIconResId());
    }

    @Override
    public int getItemCount() {
        return profileItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView menuTitle;
        ImageView menuIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuTitle = itemView.findViewById(R.id.menuTitle);
            menuIcon = itemView.findViewById(R.id.menuIcon);
        }
    }
}
