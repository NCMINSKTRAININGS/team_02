package by.netcracker.shop.dto;

import by.netcracker.shop.pojo.Delivery;
import by.netcracker.shop.pojo.Payment;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.User;

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

    private User user;
    private Delivery delivery;
    private Payment payment;

    private List<Product> products=new ArrayList<>();

    public OrderDto() {
    }

    public OrderDto(Long id, String comment, Integer price,
                    User user, Delivery delivery, Payment payment,
                    List<Product> products) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;

        OrderDto orderDto = (OrderDto) o;

        if (getId() != null ? !getId().equals(orderDto.getId()) : orderDto.getId() != null) return false;
        if (getComment() != null ? !getComment().equals(orderDto.getComment()) : orderDto.getComment() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(orderDto.getPrice()) : orderDto.getPrice() != null) return false;
        if (getUser() != null ? !getUser().equals(orderDto.getUser()) : orderDto.getUser() != null) return false;
        if (getDelivery() != null ? !getDelivery().equals(orderDto.getDelivery()) : orderDto.getDelivery() != null)
            return false;
        if (getPayment() != null ? !getPayment().equals(orderDto.getPayment()) : orderDto.getPayment() != null)
            return false;
        return getProducts() != null ? getProducts().equals(orderDto.getProducts()) : orderDto.getProducts() == null;

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
        return "OrderDto{" +
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
