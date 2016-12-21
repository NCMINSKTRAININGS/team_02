package by.netcracker.shop.pojo;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name = "product")
public class Product extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "manufacturer_id")
    private Integer manufacturerId;
    @NotBlank
    @Size(min = 2, max = 45)
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    //    @NotBlank
    @Column(name = "price", nullable = false)
    private Integer price;
    @Column(name = "keywords", nullable = false)
    private String keywords;
    //    @NotEmpty
    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;

    public Product() {
    }

    public Product(Product product) {
        this(product.getCategoryId(), product.getManufacturerId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getKeywords(), product.getQuantityInStock());
    }

    public Product(Integer categoryId, Integer manufacturerId, String name,
                   String description, Integer price, String keywords, Integer quantityInStock) {
        this.categoryId = categoryId;
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.keywords = keywords;
        this.quantityInStock = quantityInStock;

    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        if (categoryId != null ? !categoryId.equals(product.categoryId) : product.categoryId != null) return false;
        return manufacturerId != null ? manufacturerId.equals(product.manufacturerId) : product.manufacturerId == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (manufacturerId != null ? manufacturerId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", manufacturerId=" + manufacturerId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", keywords='" + keywords + '\'' +
                ", quantityInStock=" + quantityInStock +
                '}';
    }
}