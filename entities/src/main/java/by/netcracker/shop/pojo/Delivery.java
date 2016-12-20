package by.netcracker.shop.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "delivery")
public class Delivery extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery)) return false;
        if (!super.equals(o)) return false;

        Delivery delivery = (Delivery) o;

        if (getName() != null ? !getName().equals(delivery.getName()) : delivery.getName() != null) return false;
        return getDescription() != null ? getDescription().equals(delivery.getDescription()) : delivery.getDescription() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
