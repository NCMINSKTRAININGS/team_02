package by.netcracker.shop.dto;

public class ProductImageDTO {

    private Long id;

    private Long productId;

    private String productName;

    private String image;

    public ProductImageDTO() {
    }

    public ProductImageDTO(Long productId, String productName, String image) {
        this(null, productId, productName, image);
    }

    public ProductImageDTO(ProductImageDTO imageDTO) {
        this(imageDTO.getId(), imageDTO.getProductId(), imageDTO.getProductName(), imageDTO.getImage());
    }

    public ProductImageDTO(Long id, Long productId, String productName, String image) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

        ProductImageDTO dto = (ProductImageDTO) o;

        if (!id.equals(dto.id)) return false;
        if (productId != null ? !productId.equals(dto.productId) : dto.productId != null) return false;
        if (productName != null ? !productName.equals(dto.productName) : dto.productName != null) return false;
        return image != null ? image.equals(dto.image) : dto.image == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductImageDTO{" +
                "id=" + id +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
