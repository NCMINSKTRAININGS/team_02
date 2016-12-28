package by.netcracker.shop.dto;

import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

public class UsersOrdersDTO {
    private Long userId;
    @Size(max = 45)
    private String firstName;
    @Size(max = 45)
    private String lastName;
    @NotBlank
    @Size(min = 5, max = 45)
    private String username;
    private String email;
    private Double discount;
    private UserStatus userStatus;
    private Date birthday;
    private UserRole userRole;
    private Boolean isProduced;
    private Integer orderIsntProduced;
    private Integer orderIsProduced;
    private Double orderPrice;

    public UsersOrdersDTO() {
    }

    public UsersOrdersDTO(Long userId, String firstName, String lastName,
                          String username, String email, Double discount,
                          UserStatus userStatus, Date birthday, UserRole userRole,
                          Boolean isProduced, Integer orderIsntProduced,
                          Integer orderIsProduced, Double orderPrice) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.discount = discount;
        this.userStatus = userStatus;
        this.birthday = birthday;
        this.userRole = userRole;
        this.isProduced = isProduced;
        this.orderIsntProduced = orderIsntProduced;
        this.orderIsProduced = orderIsProduced;
        this.orderPrice = orderPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Boolean getProduced() {
        return isProduced;
    }

    public void setProduced(Boolean produced) {
        isProduced = produced;
    }

    public Integer getOrderIsntProduced() {
        return orderIsntProduced;
    }

    public void setOrderIsntProduced(Integer orderIsntProduced) {
        this.orderIsntProduced = orderIsntProduced;
    }

    public Integer getOrderIsProduced() {
        return orderIsProduced;
    }

    public void setOrderIsProduced(Integer orderIsProduced) {
        this.orderIsProduced = orderIsProduced;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersOrdersDTO)) return false;

        UsersOrdersDTO that = (UsersOrdersDTO) o;

        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        if (getDiscount() != null ? !getDiscount().equals(that.getDiscount()) : that.getDiscount() != null)
            return false;
        if (getUserStatus() != that.getUserStatus()) return false;
        if (getBirthday() != null ? !getBirthday().equals(that.getBirthday()) : that.getBirthday() != null)
            return false;
        if (getUserRole() != that.getUserRole()) return false;
        if (isProduced != null ? !isProduced.equals(that.isProduced) : that.isProduced != null) return false;
        if (getOrderIsntProduced() != null ? !getOrderIsntProduced().equals(that.getOrderIsntProduced()) : that.getOrderIsntProduced() != null)
            return false;
        if (getOrderIsProduced() != null ? !getOrderIsProduced().equals(that.getOrderIsProduced()) : that.getOrderIsProduced() != null)
            return false;
        return getOrderPrice() != null ? getOrderPrice().equals(that.getOrderPrice()) : that.getOrderPrice() == null;

    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getDiscount() != null ? getDiscount().hashCode() : 0);
        result = 31 * result + (getUserStatus() != null ? getUserStatus().hashCode() : 0);
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        result = 31 * result + (getUserRole() != null ? getUserRole().hashCode() : 0);
        result = 31 * result + (isProduced != null ? isProduced.hashCode() : 0);
        result = 31 * result + (getOrderIsntProduced() != null ? getOrderIsntProduced().hashCode() : 0);
        result = 31 * result + (getOrderIsProduced() != null ? getOrderIsProduced().hashCode() : 0);
        result = 31 * result + (getOrderPrice() != null ? getOrderPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsersOrdersDTO{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", discount=" + discount +
                ", userStatus=" + userStatus +
                ", birthday=" + birthday +
                ", userRole=" + userRole +
                ", isProduced=" + isProduced +
                ", orderIsntProduced=" + orderIsntProduced +
                ", orderIsProduced=" + orderIsProduced +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
