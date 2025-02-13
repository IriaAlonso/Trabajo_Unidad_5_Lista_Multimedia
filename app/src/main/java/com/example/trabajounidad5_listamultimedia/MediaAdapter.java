package com.example.trabajounidad5_listamultimedia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {
    private final List<MediaItem> mediaList;
    private final Context context;

    public MediaAdapter(Context context, List<MediaItem> mediaList) {
        this.context = context;
        this.mediaList = mediaList;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        MediaItem item = mediaList.get(position);
        holder.title.setText(item.getTitle());

        holder.title.setOnClickListener(v -> {
            // Mostrar un AlertDialog con el título y el tipo del ítem
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Detalles del ítem");

            // Construir el mensaje del Dialog (solo Título y Tipo)
            String message = "Título: " + item.getTitle() + "\n" +
                    "Tipo: " + item.getType().toString();

            builder.setMessage(message)
                    .setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss())
                    .setNegativeButton("Abrir", (dialog, which) -> {
                        // Acción dependiendo del tipo de medio
                        if (item.getType() == MediaItem.Type.WEB) {
                            // Abrir URL en navegador
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                            context.startActivity(browserIntent);
                        } else if (item.getType() == MediaItem.Type.VIDEO) {
                            // Abrir Video en la actividad correspondiente
                            Intent videoIntent = new Intent(context, MediaPlayerActivity.class);
                            videoIntent.putExtra("title", item.getTitle());
                            videoIntent.putExtra("type", item.getType().toString());
                            videoIntent.putExtra("url", item.getUrl());
                            context.startActivity(videoIntent);
                        } else if (item.getType() == MediaItem.Type.AUDIO) {
                            // Reproducir audio
                            Intent audioIntent = new Intent(context, MediaPlayerActivity.class);
                            audioIntent.putExtra("title", item.getTitle());
                            audioIntent.putExtra("type", item.getType().toString());
                            audioIntent.putExtra("audioResId", item.getAudioResId());
                            context.startActivity(audioIntent);
                        }
                    })
                    .create()
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.media_title);
        }
    }
}
