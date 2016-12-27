package by.netcracker.shop.pojo;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage  extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image", nullable = false)
    private String image;

    public ProductImage() {
        super();
    }

    public ProductImage(Product product, String image) {
        this(null, product, image);
    }

    public ProductImage(ProductImage image) {
        this(image.getId(), image.getProduct(), image.getImage());
    }

    public ProductImage(Long id, Product product, String image) {
        super(id);
        this.product = product;
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
