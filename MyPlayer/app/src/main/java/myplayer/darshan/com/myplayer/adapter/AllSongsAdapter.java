package myplayer.darshan.com.myplayer.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import myplayer.darshan.com.myplayer.MainActivity;
import myplayer.darshan.com.myplayer.R;
import myplayer.darshan.com.myplayer.serviceobjects.Song;

/**
 * Created by DBangre on 30-10-2015.
 */
public class AllSongsAdapter extends RecyclerView.Adapter<AllSongsAdapter.ViewHolderAllSongs> {

    private LayoutInflater layoutInflater;
    private ArrayList<Song> songsList;

    public AllSongsAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setSongsList(ArrayList<Song> songsList) {
        this.songsList = songsList;
        notifyItemRangeChanged(0, songsList.size());
    }

    @Override
    public ViewHolderAllSongs onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.songs_view, parent, false);
        ViewHolderAllSongs viewHolderAllSongs = new ViewHolderAllSongs(view);

        return viewHolderAllSongs;
    }

    @Override
    public void onBindViewHolder(ViewHolderAllSongs holder, int position) {

        Song song = songsList.get(position);
        holder.songName.setText(song.getName());
        holder.songAlbum.setText(song.getAlbumName());
        holder.songDuration.setText(song.getDuration());
        holder.songAlbumArt.setImageBitmap(song.getAlbumImage());


    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    static class ViewHolderAllSongs extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView image;
        private TextView songName;
        private TextView songDuration;
        private TextView songAlbum;
        private ImageView songAlbumArt;

        public ViewHolderAllSongs(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.all_songs);
            songName = (TextView) itemView.findViewById(R.id.songName);
            songDuration = (TextView) itemView.findViewById(R.id.songDuration);
            songAlbum = (TextView) itemView.findViewById(R.id.songAlbum);
            songName.setTypeface(Typeface.create("sans-serif-thin", Typeface.BOLD));
            songAlbumArt = (ImageView) itemView.findViewById(R.id.songImage);

            songName.setOnClickListener(this);
            songAlbum.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (v == itemView) {
                Toast.makeText(itemView.getContext(), "Visiting Card Clicked is ==>" + songName.getText(), Toast.LENGTH_SHORT).show();
            }
            v.animate();

        }
    }
}
