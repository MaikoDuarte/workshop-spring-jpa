package com.maikoduarte.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maikoduarte.course.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
  private Instant moment;

  private Integer orderStatus;


  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "client_id")
  private User client;


  @OneToMany(mappedBy = "id.order")
  private final Set<OrderItem> items = new HashSet<>();

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
  private Payment payment;

  public Order() {
  }

  @SuppressWarnings("OverridableMethodCallInConstructor")
  public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
    this.id = id;
    this.moment = moment;
    setOrderStatus(orderStatus);
    this.client = client;
    
  }

  public OrderStatus getOrderStatus() {
    return OrderStatus.valueOf(orderStatus);
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    if(orderStatus != null) {
      this.orderStatus = orderStatus.getCode();

    }

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getMoment() {
    return moment;
  }

  public void setMoment(Instant moment) {
    this.moment = moment;
  }

  public User getClient() {
    return client;
  }

  public void setClient(User client) {
    this.client = client;
  }

  
  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public Double getTotal() {
    double sum = 0.0;
    for (OrderItem x : items) {
      sum += x.getSubTotal();
    }
    return sum;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((moment == null) ? 0 : moment.hashCode());
    result = prime * result + ((client == null) ? 0 : client.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Order other = (Order) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (moment == null) {
      if (other.moment != null)
        return false;
    } else if (!moment.equals(other.moment))
      return false;
    if (client == null) {
      if (other.client != null)
        return false;
    } else if (!client.equals(other.client))
      return false;
    return true;
  }



  

}