package by.netcracker.shop.pojo;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nataly on 08.12.2016.
 * ${VERSION}
 */

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Integer manufacturerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "keywords", nullable = false)
    private String keywords;

    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_product", joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private List<Integer> ordersId = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Integer> getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(List<Integer> ordersId) {
        this.ordersId = ordersId;
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
