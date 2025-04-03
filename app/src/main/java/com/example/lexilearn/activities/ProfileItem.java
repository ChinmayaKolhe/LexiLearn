package com.example.lexilearn.activities;

public class ProfileItem {
    private String title;
    private int iconResId;

    public ProfileItem(String title, int iconResId) {
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
