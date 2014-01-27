package org.musicstore.repositories;

import java.io.Serializable;

public class AlbumQuery implements Serializable {
    // Query objects are passed from the web to ejb. JBoss requires them to be serializable.

    private String title;
    private String genreName;

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
