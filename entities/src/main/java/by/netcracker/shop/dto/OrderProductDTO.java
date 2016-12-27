package by.netcracker.shop.dto;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderProductDTO {
    private Long orderId;
    @NotNull
    @Size(min=1, max=200)
    private String orderComment;
    @NotNull
    @Min(1) @Max(999999999)
    private Double orderPrice;
    @NotNull
    private Long userId;
    @NotBlank
    @NotNull
    private String username;
    private Long deliveryId;
    private Long paymentId;

    private Boolean isProduced;
    private Long productId;
    @NotBlank
    @Size(max = 45)
    private String productName;
    private String productDescription;
    @NotNull
    private Double productPrice;
    private String keywords;
    @NotNull
    private Integer quantityInStock;
    @NotNull
    private Integer productQuantityInOrder;

    public OrderProductDTO() {
    }

    public OrderProductDTO(Long orderId, String orderComment, Double orderPrice,
                           Long userId, String username, Long deliveryId,
                           Long paymentId, Boolean isProduced, Long productId,
                           String productName, String productDescription,
                           Double productPrice, String keywords,
                           Integer quantityInStock, Integer productQuantityInOrder) {
        this.orderId = orderId;
        this.orderComment = orderComment;
        this.orderPrice = orderPrice;
        this.userId = userId;
        this.username = username;
        this.deliveryId = deliveryId;
        this.paymentId = paymentId;
        this.isProduced = isProduced;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.keywords = keywords;
        this.quantityInStock = quantityInStock;
        this.productQuantityInOrder = productQuantityInOrder;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getProduced() {
        return isProduced;
    }

    public void setProduced(Boolean produced) {
        isProduced = produced;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Integer getProductQuantityInOrder() {
        return productQuantityInOrder;
    }

    public void setProductQuantityInOrder(Integer productQuantityInOrder) {
        this.productQuantityInOrder = productQuantityInOrder;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProductDTO)) return false;

        OrderProductDTO that = (OrderProductDTO) o;

        if (getOrderId() != null ? !getOrderId().equals(that.getOrderId()) : that.getOrderId() != null) return false;
        if (getOrderComment() != null ? !getOrderComment().equals(that.getOrderComment()) : that.getOrderComment() != null)
            return false;
        if (getOrderPrice() != null ? !getOrderPrice().equals(that.getOrderPrice()) : that.getOrderPrice() != null)
            return false;
        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getDeliveryId() != null ? !getDeliveryId().equals(that.getDeliveryId()) : that.getDeliveryId() != null)
            return false;
        if (getPaymentId() != null ? !getPaymentId().equals(that.getPaymentId()) : that.getPaymentId() != null)
            return false;
        if (isProduced != null ? !isProduced.equals(that.isProduced) : that.isProduced != null) return false;
        if (getProductId() != null ? !getProductId().equals(that.getProductId()) : that.getProductId() != null)
            return false;
        if (getProductName() != null ? !getProductName().equals(that.getProductName()) : that.getProductName() != null)
            return false;
        if (getProductDescription() != null ? !getProductDescription().equals(that.getProductDescription()) : that.getProductDescription() != null)
            return false;
        if (getProductPrice() != null ? !getProductPrice().equals(that.getProductPrice()) : that.getProductPrice() != null)
            return false;
        if (getKeywords() != null ? !getKeywords().equals(that.getKeywords()) : that.getKeywords() != null)
            return false;
        if (getQuantityInStock() != null ? !getQuantityInStock().equals(that.getQuantityInStock()) : that.getQuantityInStock() != null)
            return false;
        return getProductQuantityInOrder() != null ? getProductQuantityInOrder().equals(that.getProductQuantityInOrder()) : that.getProductQuantityInOrder() == null;

    }

    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getOrderComment() != null ? getOrderComment().hashCode() : 0);
        result = 31 * result + (getOrderPrice() != null ? getOrderPrice().hashCode() : 0);
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getDeliveryId() != null ? getDeliveryId().hashCode() : 0);
        result = 31 * result + (getPaymentId() != null ? getPaymentId().hashCode() : 0);
        result = 31 * result + (isProduced != null ? isProduced.hashCode() : 0);
        result = 31 * result + (getProductId() != null ? getProductId().hashCode() : 0);
        result = 31 * result + (getProductName() != null ? getProductName().hashCode() : 0);
        result = 31 * result + (getProductDescription() != null ? getProductDescription().hashCode() : 0);
        result = 31 * result + (getProductPrice() != null ? getProductPrice().hashCode() : 0);
        result = 31 * result + (getKeywords() != null ? getKeywords().hashCode() : 0);
        result = 31 * result + (getQuantityInStock() != null ? getQuantityInStock().hashCode() : 0);
        result = 31 * result + (getProductQuantityInOrder() != null ? getProductQuantityInOrder().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderProductDTO{" +
                "orderId=" + orderId +
                ", orderComment='" + orderComment + '\'' +
                ", orderPrice=" + orderPrice +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", deliveryId=" + deliveryId +
                ", paymentId=" + paymentId +
                ", isProduced=" + isProduced +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", keywords='" + keywords + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", productQuantityInOrder=" + productQuantityInOrder +
                '}';
    }
}
