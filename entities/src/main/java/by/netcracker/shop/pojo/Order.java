package by.netcracker.shop.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /// @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")
    @Column(name = "user_id")
    private Long userId;

   // @ManyToOne(fetch = FetchType.LAZY,optional = false)
    //@JoinColumn(name = "payment_id")
    @Column(name = "payment_id")
    private Long paymentId;

    //@ManyToOne(optional = false)
    //@JoinColumn(name = "delivery_id")
    @Column(name = "delivery_id")
    private Long deliveryId;

    @Column(name="comment", nullable=false)
    private String comment;

    @Column(name="price", nullable=false)
    private Integer price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_product",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> productsId=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
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

    public List<Product> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Product> productsId) {
        this.productsId = productsId;
    }

    public Order() {
    }

    public Order(Long userId, Long paymentId, Long deliveryId,
                 String comment, Integer price, List<Product> productsId) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.deliveryId = deliveryId;
        this.comment = comment;
        this.price = price;
        this.productsId = productsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getId() != null ? !getId().equals(order.getId()) : order.getId() != null) return false;
        if (getUserId() != null ? !getUserId().equals(order.getUserId()) : order.getUserId() != null) return false;
        if (getPaymentId() != null ? !getPaymentId().equals(order.getPaymentId()) : order.getPaymentId() != null)
            return false;
        if (getDeliveryId() != null ? !getDeliveryId().equals(order.getDeliveryId()) : order.getDeliveryId() != null)
            return false;
        if (getComment() != null ? !getComment().equals(order.getComment()) : order.getComment() != null) return false;
        if (getPrice() != null ? !getPrice().equals(order.getPrice()) : order.getPrice() != null) return false;
        return getProductsId() != null ? getProductsId().equals(order.getProductsId()) : order.getProductsId() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getPaymentId() != null ? getPaymentId().hashCode() : 0);
        result = 31 * result + (getDeliveryId() != null ? getDeliveryId().hashCode() : 0);
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getProductsId() != null ? getProductsId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", paymentId=" + paymentId +
                ", deliveryId=" + deliveryId +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", productsId=" + productsId +
                '}';
    }
}
