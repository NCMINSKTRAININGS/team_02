package by.netcracker.shop.pojo;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "product_image")
public class ProductImage  extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Lob
    @Column(name = "image", nullable = false)
    private Blob image;

    public ProductImage() {
    }

    public ProductImage(Product product, Blob image) {
        this.product = product;
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductImage that = (ProductImage) o;

        if (!id.equals(that.id)) return false;
        return product.equals(that.product);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", productId=" + product +
                ", image=" + image +
                '}';
    }
}
