package org.musicstore.repositories;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.musicstore.model.entities.Genre;

@Stateless
@Remote(GenreRepository.class)
public class GenreRepositoryImpl implements GenreRepository {
    
    @PersistenceContext(unitName="EnterpriseMusicStore")
    private EntityManager em;

    public List<Genre> getGenres(){
        List<Genre> resultList = em.createNamedQuery(Genre.FIND_ALL).getResultList();
        return resultList;
    }
}
