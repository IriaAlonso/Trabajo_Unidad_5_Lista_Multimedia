package com.example.trabajounidad5_listamultimedia;

public class MediaItem {
    public enum Type { VIDEO, AUDIO, WEB }

    private String title;
    private String url;  // Para VIDEO y WEB
    private int audioResId; // Para AUDIO
    private Type type;

    // Constructor para VIDEO y WEB
    public MediaItem(String title, Type type, String url, int audioResId) {
        this.title = title;
        this.url = url;
        this.audioResId = audioResId;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getAudioResId() {
        return audioResId;
    }

    public Type getType() {
        return type;
    }
}
