package myplayer.darshan.com.myplayer.serviceobjects;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DBangre on 30-10-2015.
 */
public class Song {

    private int id;
    private String fullPath;
    private String albumName;
    private Uri uri;
    private String name;
    private String duration;
    private String albumArtURI;
    private Bitmap albumImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbumArtURI() {
        return albumArtURI;
    }

    public void setAlbumArtURI(String albumArtURI) {
        this.albumArtURI = albumArtURI;
    }

    public Bitmap getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(Bitmap albumImage) {
        this.albumImage = albumImage;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", fullPath='" + fullPath + '\'' +
                ", albumName='" + albumName + '\'' +
                ", uri='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}
