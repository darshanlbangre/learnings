package myplayer.darshan.com.myplayer;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import myplayer.darshan.com.myplayer.adapter.AllSongsAdapter;
import myplayer.darshan.com.myplayer.serviceobjects.Song;

/**
 * Created by DBangre on 29-10-2015.
 */
public class Tab1 extends Fragment {

    private RecyclerView allSongsView;
    private AllSongsAdapter allSongsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_1, container, false);

        allSongsView = (RecyclerView) view.findViewById(R.id.all_songs);
        allSongsView.setLayoutManager(new LinearLayoutManager(getActivity()));

        allSongsAdapter = new AllSongsAdapter(getActivity());
        allSongsView.setAdapter(allSongsAdapter);
        getAllSongs();
        return view;
    }

    public ArrayList<Song> getAllSongs() {

        Cursor cursor;
        ArrayList<Song> songsList = new ArrayList<Song>();
        Uri songsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " !=0";
        String[] star = {"*"};
        if (isSDCardPresent()) {
            cursor = getActivity().getContentResolver().query(songsUri, star, selection, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Song song = new Song();
                        String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        String[] res = data.split("\\.");
                        song.setName(res[0]);
                        song.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                        song.setFullPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                        song.setAlbumName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                        song.setUri(ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                                cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID))));
                        Double duration = cursor.getDouble(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                                duration = (duration/1000)/60;
                        DecimalFormat df = new DecimalFormat("#.##");
                        song.setDuration(df.format(duration));
                        int albumId = cursor.getInt(cursor.getColumnIndex(android.provider.MediaStore.Audio.Albums._ID));

                        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                        Uri uri = ContentUris.withAppendedId(sArtworkUri, albumId);
                        ContentResolver content = getActivity().getContentResolver();
                        InputStream in = null;
                        try {
                            in = content.openInputStream(uri);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Bitmap artwork = BitmapFactory.decodeStream(in);

                        song.setAlbumImage(artwork);

                        songsList.add(song);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        }
        allSongsAdapter.setSongsList(songsList);
        return songsList;
    }

    private static boolean isSDCardPresent() {
        return android.os.Environment.getExternalStorageState().
                equals(Environment.MEDIA_MOUNTED);
    }
}
