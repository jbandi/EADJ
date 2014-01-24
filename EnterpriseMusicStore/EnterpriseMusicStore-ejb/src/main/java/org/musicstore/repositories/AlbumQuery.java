package org.musicstore.repositories;

import java.io.Serializable;

public class AlbumQuery implements Serializable {

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

    public void setName(String title) {
        this.title = title;
    }

}
