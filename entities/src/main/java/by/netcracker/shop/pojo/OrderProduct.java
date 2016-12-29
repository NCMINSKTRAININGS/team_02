package by.netcracker.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_product")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.order",
                joinColumns = @JoinColumn(name = "order_id")),
        @AssociationOverride(name = "primaryKey.product",
        joinColumns = @JoinColumn(name = "product_id"))
})
public class OrderProduct implements Serializable{
    //composite-id key
    @EmbeddedId
    private OrderProductId primaryKey = new OrderProductId();

    //additional fields
    //prUduct!
    @Column(name = "pruduct_quantity")
    private Integer pruductQuantity;
    @Column(name = "product_price")
    private Double price;

    public OrderProductId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(OrderProductId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Order getOrder() {
        return getPrimaryKey().getOrder();
    }

    public void setOrder(Order order) {
        getPrimaryKey().setOrder(order);
    }

    @Transient
    public Product getProduct() {
        return getPrimaryKey().getProduct();
    }

    public void setProduct(Product product) {
        getPrimaryKey().setProduct(product);
    }

    public Integer getPruductQuantity() {
        return pruductQuantity;
    }

    public void setPruductQuantity(Integer pruductQuantity) {
        this.pruductQuantity = pruductQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
