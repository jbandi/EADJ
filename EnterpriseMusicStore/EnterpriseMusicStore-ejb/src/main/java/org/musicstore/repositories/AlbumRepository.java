package org.musicstore.repositories;

import java.util.List;
import org.musicstore.model.entities.Album;

public interface AlbumRepository {

    public Album getAlbum(Long id);
    public List<Album> getAlbums(AlbumQuery albumQuery);

}
