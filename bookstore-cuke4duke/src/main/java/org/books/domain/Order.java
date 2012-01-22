package org.books.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_ORDER")
public class Order {

    public enum Status {
        Submitted, InProgress, Closed, Canceled
    }
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	private double price;
	
    @Enumerated(EnumType.STRING)
    private Order.Status status = Status.Submitted;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PaymentInfo paymentInfo;
    
    @OneToMany (cascade = CascadeType.ALL)
	private List<LineItem> lineItems = new ArrayList<LineItem>();
	
	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public long getId(){
		return id;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	
    public Order.Status getStatus() {
        return status;
    }
    public void setStatus(Order.Status status) {
        this.status = status;
    }

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}
	
}
