package org.musicstore.web;

import org.musicstore.model.entities.MusicOrder;
import org.musicstore.repositories.MusicOrderRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class OrderAdminPresenter implements Serializable {

    @Inject
    MusicOrderRepository musicOrderRepository;

    private List<MusicOrder> musicOrders = new ArrayList<>();

    @PostConstruct
    public void loadMusicOrders() {
        musicOrders = musicOrderRepository.getOrders();
    }

    public List<MusicOrder> getMusicOrders() {
        return musicOrders;
    }
}
