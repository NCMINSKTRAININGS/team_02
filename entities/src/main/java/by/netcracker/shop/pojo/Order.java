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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Long deliveryId;

    @Column(name="comment", nullable=false)
    private String comment;

    @Column(name="price", nullable=false)
    private Integer price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_product",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Long> productsId=new ArrayList<>();

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

    public List<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Long> productsId) {
        this.productsId = productsId;
    }

    public Order() {
    }

    public Order(List<Long> productsId, Long userId, Long paymentId,
                 Long deliveryId, String comment, Integer price) {
        this.productsId = productsId;
        this.userId = userId;
        this.paymentId = paymentId;
        this.deliveryId = deliveryId;
        this.comment = comment;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (userId != null ? !userId.equals(order.userId) : order.userId != null) return false;
        if (paymentId != null ? !paymentId.equals(order.paymentId) : order.paymentId != null) return false;
        if (deliveryId != null ? !deliveryId.equals(order.deliveryId) : order.deliveryId != null) return false;
        if (comment != null ? !comment.equals(order.comment) : order.comment != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        return productsId != null ? productsId.equals(order.productsId) : order.productsId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (paymentId != null ? paymentId.hashCode() : 0);
        result = 31 * result + (deliveryId != null ? deliveryId.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (productsId != null ? productsId.hashCode() : 0);
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
