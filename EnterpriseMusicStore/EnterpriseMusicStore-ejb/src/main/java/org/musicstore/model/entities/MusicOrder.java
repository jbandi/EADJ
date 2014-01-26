package org.musicstore.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = MusicOrder.FIND_ALL, query = "SELECT o FROM MusicOrder o"),
        @NamedQuery(name = MusicOrder.FIND_BY_EMAIL, query = "SELECT o FROM MusicOrder o WHERE o.email = :email")})
public class MusicOrder implements Serializable {

    public static final String FIND_ALL = "MusicOrder.findAll";
    public static final String FIND_BY_EMAIL = "MusicOrder.findByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String city;
    private String zip;
    private String country;

    private Double finalAmount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private List<OrderItem> orderItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Double getFinalAmount() {

        if (finalAmount == null)
            return getTotalAmount();
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Double getTotalAmount(){
        Double sum = 0d;
        for(OrderItem orderItem : orderItems) {
            sum += orderItem.getPrice();
        }
        return sum;
    }
}
