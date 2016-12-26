package by.netcracker.shop.dto;

import by.netcracker.shop.pojo.Category;
import by.netcracker.shop.pojo.Manufacturer;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDTO {

    private Long id;

    private Category category;

    private Manufacturer manufacturer;

    @NotBlank
    @Size(max = 45)
    private String name;

    private String description;

    @NotNull
    private Integer price;

    private String keywords;

    @NotNull
    private Integer quantityInStock;

    private String image;

    public ProductDTO() {
    }

    public ProductDTO(Long id, Category category, Manufacturer manufacturer, String name, String description,
                      Integer price, String keywords, Integer quantityInStock, String image) {
        this.id = id;
        this.category = category;
        this.manufacturer = manufacturer;
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
        if (category != null ? !category.equals(dto.category) : dto.category != null) return false;
        if (manufacturer != null ? !manufacturer.equals(dto.manufacturer) : dto.manufacturer != null) return false;
        if (!name.equals(dto.name)) return false;
        if (description != null ? !description.equals(dto.description) : dto.description != null) return false;
        if (!price.equals(dto.price)) return false;
        if (keywords != null ? !keywords.equals(dto.keywords) : dto.keywords != null) return false;
        return quantityInStock.equals(dto.quantityInStock);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
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
        return "ProductDTO{" +
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
