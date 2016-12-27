package by.netcracker.shop.pojo;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"order\"")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = true, targetEntity = Payment.class)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToOne(optional = true, targetEntity = Delivery.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Product.class)
    @JoinTable(name = "order_product",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> products = new HashSet<>();

    public Order() {
    }

    public Order(User user, Payment payment, Delivery delivery,
                 String comment, Double price, Set<Product> products) {
        this.user = user;
        this.payment = payment;
        this.delivery = delivery;
        this.comment = comment;
        this.price = price;
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (getUser() != null ? !getUser().equals(order.getUser()) : order.getUser() != null) return false;
        if (getPayment() != null ? !getPayment().equals(order.getPayment()) : order.getPayment() != null) return false;
        if (getDelivery() != null ? !getDelivery().equals(order.getDelivery()) : order.getDelivery() != null)
            return false;
        if (getComment() != null ? !getComment().equals(order.getComment()) : order.getComment() != null) return false;
        if (getPrice() != null ? !getPrice().equals(order.getPrice()) : order.getPrice() != null) return false;
        return getProducts() != null ? getProducts().equals(order.getProducts()) : order.getProducts() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
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
                "user=" + user +
                ", payment=" + payment +
                ", delivery=" + delivery +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", products=" + products +
                '}';
    }
}
