package com.example.trabajounidad5_listamultimedia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MediaPlayerActivity extends AppCompatActivity {

    private TextView titleText;
    private VideoView videoView;
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private Button backButton;  // Declaramos el botón para volver atrás

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        // Inicializar vistas
        titleText = findViewById(R.id.media_title);
        videoView = findViewById(R.id.video_view);
        playButton = findViewById(R.id.play_button);
        backButton = findViewById(R.id.back_button); // Inicializamos el botón de retroceder

        // Obtener los datos de la Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String type = intent.getStringExtra("type");

        titleText.setText(title);

        // Verificar el tipo de medio (VIDEO o AUDIO)
        if ("VIDEO".equals(type)) {
            // Reproducir Video
            String url = intent.getStringExtra("url");
            Uri videoUri = Uri.parse(url);
            videoView.setVideoURI(videoUri);

            // Configurar controles de video
            MediaController mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);

            videoView.start(); // Comienza a reproducir el video
        } else if ("AUDIO".equals(type)) {
            // Reproducir Audio
            int audioResId = intent.getIntExtra("audioResId", -1);
            if (audioResId != -1) {
                mediaPlayer = MediaPlayer.create(this, audioResId);
                playButton.setVisibility(Button.VISIBLE); // Mostrar botón para reproducir el audio

                playButton.setOnClickListener(v -> {
                    if (mediaPlayer != null) {
                        if (!mediaPlayer.isPlaying()) {
                            mediaPlayer.start(); // Iniciar la reproducción del audio
                            playButton.setText("Pausar");
                        } else {
                            mediaPlayer.pause(); // Pausar audio
                            playButton.setText("Reproducir");
                        }
                    }
                });
            }
        }

        // Función para el botón
        backButton.setOnClickListener(v -> {
            finish(); // Finaliza la actividad y vuelve atrás
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberar recursos cuando se cierre la actividad
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
