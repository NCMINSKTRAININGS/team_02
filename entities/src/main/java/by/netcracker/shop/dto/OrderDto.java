package by.netcracker.shop.dto;

import by.netcracker.shop.pojo.Product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;

    @NotNull
    @Size(min=1, max=200)
    private String comment;

    @NotNull
    private Integer price;

    private Long userId;
    private Long deliveryId;
    private Long paymentId;

    private List<Product> productsId=new ArrayList<>();

    public OrderDto() {
    }

    public OrderDto(Long id, String comment, Integer price,
                    Long userId, Long deliveryId, Long paymentId,
                    List<Product> productsId) {
        this.id = id;
        this.comment = comment;
        this.price = price;
        this.userId = userId;
        this.deliveryId = deliveryId;
        this.paymentId = paymentId;
        this.productsId = productsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public List<Product> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Product> productsId) {
        this.productsId = productsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;

        OrderDto orderDto = (OrderDto) o;

        if (!getId().equals(orderDto.getId())) return false;
        if (!getComment().equals(orderDto.getComment())) return false;
        if (!getPrice().equals(orderDto.getPrice())) return false;
        if (!getUserId().equals(orderDto.getUserId())) return false;
        if (getDeliveryId() != null ? !getDeliveryId().equals(orderDto.getDeliveryId()) : orderDto.getDeliveryId() != null)
            return false;
        if (getPaymentId() != null ? !getPaymentId().equals(orderDto.getPaymentId()) : orderDto.getPaymentId() != null)
            return false;
        return getProductsId().equals(orderDto.getProductsId());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getComment().hashCode();
        result = 31 * result + getPrice().hashCode();
        result = 31 * result + getUserId().hashCode();
        result = 31 * result + (getDeliveryId() != null ? getDeliveryId().hashCode() : 0);
        result = 31 * result + (getPaymentId() != null ? getPaymentId().hashCode() : 0);
        result = 31 * result + getProductsId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", userId=" + userId +
                ", deliveryId=" + deliveryId +
                ", paymentId=" + paymentId +
                ", productsId=" + productsId +
                '}';
    }
}
