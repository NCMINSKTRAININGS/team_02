package by.netcracker.shop.pojo;

import javax.persistence.*;

/**
 * Created by j on 9.12.16.
 */
@Entity
@Table(name = "payment")
public class Payment extends AbstractEntity {
    @Column(name="name", nullable=false)
    private String name;

    @Column(name="description")
    private String description;

    public Payment() {
    }

    public Payment(String name, String description) {
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
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        if (getId() != null ? !getId().equals(payment.getId()) : payment.getId() != null) return false;
        if (getName() != null ? !getName().equals(payment.getName()) : payment.getName() != null) return false;
        return getDescription() != null ? getDescription().equals(payment.getDescription()) : payment.getDescription() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
