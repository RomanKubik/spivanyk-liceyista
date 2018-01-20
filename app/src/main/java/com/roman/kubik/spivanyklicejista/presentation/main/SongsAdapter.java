package com.roman.kubik.spivanyklicejista.presentation.main;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annimon.stream.function.Consumer;
import com.roman.kubik.spivanyklicejista.R;
import com.roman.kubik.spivanyklicejista.domain.song.Song;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Songs main list adapter
 * Created by kubik on 1/20/18.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongHolder> {

    private Consumer<Song> onClickListener;

    private List<Song> songList = new ArrayList<>();

    @Inject
    public SongsAdapter() {
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SongHolder(inflater.inflate(R.layout.item_song, parent, false));
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {
        holder.setItem(songList.get(position));
        holder.setOnItemClickListener(v -> {
            if (onClickListener != null)
                onClickListener.accept(songList.get(holder.getAdapterPosition()));
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public void setOnClickListener(@Nullable Consumer<Song> onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setSongList(@Nullable List<Song> songList) {
        this.songList.clear();
        if (songList != null)
            this.songList.addAll(songList);
        notifyDataSetChanged();
    }

    public void addSongList(@Nullable List<Song> songList) {
        if (songList != null) {
            int possitionStart = this.songList.size();
            this.songList.addAll(songList);
            notifyItemRangeInserted(possitionStart, songList.size());
        }
    }

    static class SongHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvLyrics)
        TextView tvLyrics;

        public SongHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setItem(Song song) {
            tvTitle.setText(song.getTitle());
            tvLyrics.setText(song.getLyrics());
        }

        private void setOnItemClickListener(View.OnClickListener onClickListener) {
            tvTitle.getRootView().setOnClickListener(onClickListener);
        }
    }
}
