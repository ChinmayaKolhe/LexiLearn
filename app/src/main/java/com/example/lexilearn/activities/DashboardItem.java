package com.example.lexilearn.activities;
public class DashboardItem {
    private String title;
    private int iconResId; // Resource ID for an icon

    public DashboardItem(String title, int iconResId) {
        this.title = title;
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public int getIconResId() {
        return iconResId;
    }
}
