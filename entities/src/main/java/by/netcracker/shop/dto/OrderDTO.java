package by.netcracker.shop.dto;

import by.netcracker.shop.pojo.Delivery;
import by.netcracker.shop.pojo.Payment;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.User;

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
    private Integer price;

    private User user;
    private Delivery delivery;
    private Payment payment;
    private Set<Product> products = new HashSet<>();

    public OrderDTO() {
    }

    public OrderDTO(Long id, String comment, Integer price,
                    User user, Delivery delivery, Payment payment,
                    Set<Product> products) {
        this.id = id;
        this.comment = comment;
        this.price = price;
        this.user = user;
        this.delivery = delivery;
        this.payment = payment;
        this.products = products;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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
        if (!(o instanceof OrderDTO)) return false;

        OrderDTO orderDTO = (OrderDTO) o;

        if (getId() != null ? !getId().equals(orderDTO.getId()) : orderDTO.getId() != null) return false;
        if (getComment() != null ? !getComment().equals(orderDTO.getComment()) : orderDTO.getComment() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(orderDTO.getPrice()) : orderDTO.getPrice() != null) return false;
        if (getUser() != null ? !getUser().equals(orderDTO.getUser()) : orderDTO.getUser() != null) return false;
        if (getDelivery() != null ? !getDelivery().equals(orderDTO.getDelivery()) : orderDTO.getDelivery() != null)
            return false;
        if (getPayment() != null ? !getPayment().equals(orderDTO.getPayment()) : orderDTO.getPayment() != null)
            return false;
        return getProducts() != null ? getProducts().equals(orderDTO.getProducts()) : orderDTO.getProducts() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getDelivery() != null ? getDelivery().hashCode() : 0);
        result = 31 * result + (getPayment() != null ? getPayment().hashCode() : 0);
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", user=" + user +
                ", delivery=" + delivery +
                ", payment=" + payment +
                ", products=" + products +
                '}';
    }
}
