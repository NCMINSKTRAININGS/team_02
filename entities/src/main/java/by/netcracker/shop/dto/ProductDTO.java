package by.netcracker.shop.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDTO {

    private Long id;

    private Long categoryId;

    private Long manufacturerId;

    private String manufacturerName;

    private String categoryName;

    @NotBlank
    @Size(max = 45)
    private String name;

    private String description;

    @NotNull
    private Double price;

    private String keywords;

    @NotNull
    private Integer quantityInStock;

    private String image;

    public ProductDTO() {
    }

    public ProductDTO(Long id, Long categoryId, Long manufacturerId, String manufacturerName, String categoryName,
                      String name, String description, Double price, String keywords, Integer quantityInStock, String image) {
        this.id = id;
        this.categoryId = categoryId;
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.categoryName = categoryName;
        this.name = name;
        this.description = description;
        this.price = price;
        this.keywords = keywords;
        this.quantityInStock = quantityInStock;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

        ProductDTO dto = (ProductDTO) o;

        if (!id.equals(dto.id)) return false;
        if (categoryId != null ? !categoryId.equals(dto.categoryId) : dto.categoryId != null) return false;
        if (manufacturerId != null ? !manufacturerId.equals(dto.manufacturerId) : dto.manufacturerId != null) return false;
        if (!name.equals(dto.name)) return false;
        if (description != null ? !description.equals(dto.description) : dto.description != null) return false;
        if (!price.equals(dto.price)) return false;
        if (keywords != null ? !keywords.equals(dto.keywords) : dto.keywords != null) return false;
        return quantityInStock.equals(dto.quantityInStock);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (manufacturerId != null ? manufacturerId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (quantityInStock != null ? quantityInStock.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
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
