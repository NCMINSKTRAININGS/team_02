package by.netcracker.shop.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.Objects;

@Entity
@Table(name = "manufacturer")
public class Manufacturer extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @NotNull
    @Column(name = "description")
    private String description;
    @Column(name = "logo")
    private Blob logo;

    public Manufacturer() {
        super();
    }

    public Manufacturer(String name, String description, Blob logo) {
        this.name = name;
        this.description = description;
        this.logo = logo;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the logo
     */
    public Blob getLogo() {
        return logo;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo(Blob logo) {
        this.logo = logo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.description);
        hash = 19 * hash + Objects.hashCode(this.logo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Manufacturer other = (Manufacturer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.logo, other.logo);
    }

    @Override
    public String toString() {
        return "Manufacturer{" + "name=" + name + ", description=" + description + ", logo=" + logo + '}';
    }

}
