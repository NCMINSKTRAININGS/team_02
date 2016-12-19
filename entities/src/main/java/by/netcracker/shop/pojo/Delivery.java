package by.netcracker.shop.pojo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery")
public class Delivery extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Delivery() {
    }

    public Delivery(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery)) return false;

        Delivery delivery = (Delivery) o;

        if (getId() != null ? !getId().equals(delivery.getId()) : delivery.getId() != null) return false;
        if (getName() != null ? !getName().equals(delivery.getName()) : delivery.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(delivery.getDescription()) : delivery.getDescription() != null)
            return false;
        return getOrders() != null ? getOrders().equals(delivery.getOrders()) : delivery.getOrders() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getOrders() != null ? getOrders().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", orders=" + orders +
                '}';
    }
}
