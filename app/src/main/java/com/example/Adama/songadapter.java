package com.example.Adama;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class songadapter extends RecyclerView.Adapter<myview> {
    public songadapter(List<Song> songs) {
        this.songs = songs;
    }

    List<Song> songs;
    Context context;
    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_song,parent,false);
        myview viewHolder = new myview((songView));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        Song song = songs.get(position);
        TextView artist = holder.artist;
        artist.setText(song.getArtiste());
        TextView title = holder.text;
        title.setText(song.getTitle());
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaySongActivity.playList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
