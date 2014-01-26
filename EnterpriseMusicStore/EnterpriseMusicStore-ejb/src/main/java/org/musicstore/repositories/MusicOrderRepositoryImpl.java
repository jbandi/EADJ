package org.musicstore.repositories;

import org.musicstore.model.entities.Genre;
import org.musicstore.model.entities.MusicOrder;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

//@Stateless
//@Remote(GenreRepository.class)
@Named
public class MusicOrderRepositoryImpl implements Serializable {
    
    @PersistenceContext(unitName="EnterpriseMusicStore")
    private EntityManager em;

    public List<MusicOrder> getOrders(){
        List<MusicOrder> resultList = em.createNamedQuery(MusicOrder.FIND_ALL).getResultList();
        return resultList;
    }

    public List<MusicOrder> getOrdersByEmail(String email) {
        List<MusicOrder> resultList = em.createNamedQuery(MusicOrder.FIND_BY_EMAIL).setParameter("email", email).getResultList();
        return resultList;
    }
}
