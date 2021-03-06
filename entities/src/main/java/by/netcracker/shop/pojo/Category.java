package by.netcracker.shop.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;

    public Category() {
        super();
    }

    public Category(String name, String description, String image) {
        this(null, name, description, image);
    }

    public Category(Category category) {
        this(category.getId(), category.getName(),
                category.getDescription(), category.getImage());
    }

    public Category(Long id, String name, String description, String image) {
        super(id);
        this.name = name;
        this.description = description;
        this.image = image;
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
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.image);
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
        final Category other = (Category) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.image, other.image);
    }

    @Override
    public String toString() {
        return "Category{" + "name=" + name + ", description=" + description + ", image=" + image + '}';
    }

}
