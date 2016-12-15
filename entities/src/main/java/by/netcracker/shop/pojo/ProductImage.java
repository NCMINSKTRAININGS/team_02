package by.netcracker.shop.pojo;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Blob;

@Entity
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Integer productId;

    @Column(name = "image", nullable = false)
    private Blob image;

    public ProductImage() {
    }

    public ProductImage(Integer productId, Blob image) {
        this.productId = productId;
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductImage that = (ProductImage) o;

        if (!id.equals(that.id)) return false;
        return productId.equals(that.productId);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + productId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", productId=" + productId +
                ", image=" + image +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
