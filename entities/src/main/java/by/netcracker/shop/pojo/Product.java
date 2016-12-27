package by.netcracker.shop.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(targetEntity = Manufacturer.class)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "keywords", nullable = false)
    private String keywords;

    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;

    @OneToMany(mappedBy = "primaryKey.product",
    cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Product() {
    }

    public Product(Product product) {
        this(product.getCategory(), product.getManufacturer(), product.getName(), product.getDescription(),
                product.getPrice(), product.getKeywords(), product.getQuantityInStock());
    }

    public Product(Category category, Manufacturer manufacturer, String name,
                   String description, Double price, String keywords, Integer quantityInStock) {
        this.category = category;
        this.manufacturer = manufacturer;
        this.name = name;
        this.description = description;
        this.price = price;
        this.keywords = keywords;
        this.quantityInStock = quantityInStock;

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        return manufacturer != null ? manufacturer.equals(product.manufacturer) : product.manufacturer == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (quantityInStock != null ? quantityInStock.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", manufacturer=" + manufacturer +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", keywords='" + keywords + '\'' +
                ", quantityInStock=" + quantityInStock +
                '}';
    }
}