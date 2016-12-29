package by.netcracker.shop.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class OrderDTO {
    private Long id;

    @NotNull
    @Size(min=1, max=200)
    private String comment;

    @NotNull
    @Min(1) @Max(999999999)
    private Double price;

    private Long userId;
    private Long deliveryId;
    private Long paymentId;
    private Set<Long> productsId = new HashSet<>();
    private Boolean isProduced;

    public OrderDTO() {
    }

    public OrderDTO(OrderDTO orderDTO) {
        this(orderDTO.getId(), orderDTO.getComment(), orderDTO.getPrice(), orderDTO.getUser(),
                orderDTO.getDelivery(), orderDTO.getPayment(), orderDTO.getProducts());
    }

    public OrderDTO(Long id, String comment, Double price,
                    Long userId, Long deliveryId, Long paymentId,
                    Set<Long> productsId, Boolean isProduced) {
        this.id = id;
        this.comment = comment;
        this.price = price;
        this.userId = userId;
        this.deliveryId = deliveryId;
        this.paymentId = paymentId;
        this.productsId = productsId;
        this.isProduced = isProduced;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Set<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(Set<Long> productsId) {
        this.productsId = productsId;
    }

    public Boolean getProduced() {
        return isProduced;
    }

    public void setProduced(Boolean produced) {
        isProduced = produced;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;

        OrderDTO orderDTO = (OrderDTO) o;

        if (getId() != null ? !getId().equals(orderDTO.getId()) : orderDTO.getId() != null) return false;
        if (getComment() != null ? !getComment().equals(orderDTO.getComment()) : orderDTO.getComment() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(orderDTO.getPrice()) : orderDTO.getPrice() != null) return false;
        if (getUserId() != null ? !getUserId().equals(orderDTO.getUserId()) : orderDTO.getUserId() != null)
            return false;
        if (getDeliveryId() != null ? !getDeliveryId().equals(orderDTO.getDeliveryId()) : orderDTO.getDeliveryId() != null)
            return false;
        if (getPaymentId() != null ? !getPaymentId().equals(orderDTO.getPaymentId()) : orderDTO.getPaymentId() != null)
            return false;
        if (getProductsId() != null ? !getProductsId().equals(orderDTO.getProductsId()) : orderDTO.getProductsId() != null)
            return false;
        return isProduced != null ? isProduced.equals(orderDTO.isProduced) : orderDTO.isProduced == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getDeliveryId() != null ? getDeliveryId().hashCode() : 0);
        result = 31 * result + (getPaymentId() != null ? getPaymentId().hashCode() : 0);
        result = 31 * result + (getProductsId() != null ? getProductsId().hashCode() : 0);
        result = 31 * result + (isProduced != null ? isProduced.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", userId=" + userId +
                ", deliveryId=" + deliveryId +
                ", paymentId=" + paymentId +
                ", productsId=" + productsId +
                ", isProduced=" + isProduced +
                '}';
    }
}
