package by.netcracker.shop.pojo;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order extends AbstractEntity {
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(optional = false,targetEntity = Payment.class)
    @JoinColumn(name = "payment_id")
    //@Column(name = "payment_id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_id")
    //@Column(name = "delivery_id")
    private Delivery delivery;

    @Column(name="comment", nullable=false)
    private String comment;

    @Column(name="price", nullable=false)
    private Integer price;

    @ManyToMany(cascade = CascadeType.ALL,targetEntity = Product.class)
    @JoinTable(name = "order_product",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products=new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Order() {
    }

    public Order(Long userId, Payment payment, Delivery delivery,
                 String comment, Integer price, List<Product> products) {
        this.userId = userId;
        this.payment = payment;
        this.delivery = delivery;
        this.comment = comment;
        this.price = price;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getId() != null ? !getId().equals(order.getId()) : order.getId() != null) return false;
        if (getUserId() != null ? !getUserId().equals(order.getUserId()) : order.getUserId() != null) return false;
        if (getPayment() != null ? !getPayment().equals(order.getPayment()) : order.getPayment() != null) return false;
        if (getDelivery() != null ? !getDelivery().equals(order.getDelivery()) : order.getDelivery() != null)
            return false;
        if (getComment() != null ? !getComment().equals(order.getComment()) : order.getComment() != null) return false;
        if (getPrice() != null ? !getPrice().equals(order.getPrice()) : order.getPrice() != null) return false;
        return getProducts() != null ? getProducts().equals(order.getProducts()) : order.getProducts() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getPayment() != null ? getPayment().hashCode() : 0);
        result = 31 * result + (getDelivery() != null ? getDelivery().hashCode() : 0);
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", payment=" + payment +
                ", delivery=" + delivery +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", products=" + products +
                '}';
    }
}
