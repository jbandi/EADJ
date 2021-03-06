package org.musicstore.web;

import org.musicstore.model.entities.Album;
import org.musicstore.repositories.AlbumQuery;
import org.musicstore.repositories.AlbumRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@RequestScoped
public class SearchPresenter {

    @Inject private AlbumRepository albumRepository;
    private List<Album> result;

    private String title;
    private String genreName;

    // @PostConstruct
    public void performSearch(){
        AlbumQuery albumQuery = new AlbumQuery();
        albumQuery.setTitle(title);
        albumQuery.setGenreName(genreName);

        result = albumRepository.getAlbums(albumQuery);
    }

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

    public List<Album> getResult(){
        return result;
    }
}

