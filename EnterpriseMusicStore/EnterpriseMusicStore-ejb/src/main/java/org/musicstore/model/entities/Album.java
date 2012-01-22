package org.musicstore.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(name = Album.FIND_ALL, query = "SELECT a FROM Album a"),
    @NamedQuery(name = Album.FIND_BY_ID, query = "SELECT a FROM Album a WHERE a.id = :id")})
public class Album implements Serializable  {

    public static final String FIND_ALL = "Album.findAll";
    public static final String FIND_BY_ID = "Album.findById";
        
    @Id @GeneratedValue
    private Long id;
    private String name;
    private double price;
    private double totalAmount;
    private String albumArtUrl = "cover.png";

    @ManyToOne
    private Genre genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    public void setAlbumArtUrl(String albumArtUrl) {
        this.albumArtUrl = albumArtUrl;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.musicstore.model.entities.Album[Id=" + id + "]";
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setTotalAmount(double price) {
        this.totalAmount = price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }


}
