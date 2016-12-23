package by.netcracker.shop.dto;

import by.netcracker.shop.pojo.Product;

public class ProductImageDTO {

    private Long id;

    private Product product;

    private String image;

    public ProductImageDTO() {
    }

    public ProductImageDTO(Product product, String image) {
        this.product = product;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        ProductImageDTO that = (ProductImageDTO) o;

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
