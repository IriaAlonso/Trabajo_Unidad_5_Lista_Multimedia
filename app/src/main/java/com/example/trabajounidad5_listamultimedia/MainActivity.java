package com.example.trabajounidad5_listamultimedia;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recicle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<MediaItem> mediaList = new ArrayList<>();

        // Agregar videos
        mediaList.add(new MediaItem("Video 1 - Flores", MediaItem.Type.VIDEO, "android.resource://" + getPackageName() + "/" + R.raw.video1, 0));
        mediaList.add(new MediaItem("Video 2 - Mar", MediaItem.Type.VIDEO, "android.resource://" + getPackageName() + "/" + R.raw.video2, 0));

        // Agregar audios
        mediaList.add(new MediaItem("Audio 1 - Oleaje", MediaItem.Type.AUDIO, null, R.raw.audio1));
        mediaList.add(new MediaItem("Audio 2 - Pelicula de terror", MediaItem.Type.AUDIO, null, R.raw.audio2));

        // Agregar webs
        mediaList.add(new MediaItem("Web 1 - FP", MediaItem.Type.WEB, "https://fpadistancia.edu.xunta.gal/", 0));
        mediaList.add(new MediaItem("Web 2 - Google", MediaItem.Type.WEB, "https://www.google.com/", 0));

        MediaAdapter adapter = new MediaAdapter(this, mediaList);
        recyclerView.setAdapter(adapter);
    }
}
