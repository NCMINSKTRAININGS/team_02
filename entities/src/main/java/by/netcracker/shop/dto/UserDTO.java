package by.netcracker.shop.dto;


import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.pojo.Order;

import java.util.Date;
import java.util.List;

public class UserDTO {
    private String firstname;

    private String lastName;
    private String username;
    private String password;
    private String salt;
    private String email;
    private int discount;
    private UserStatus status;
    private Date birthday;
    private UserRole role;
    private List<Order> orders;

    public UserDTO() {
    }

    public UserDTO(String firstname, String lastName, String username, String password,
                   String salt, String email, int discount, UserStatus status, Date birthday,
                   UserRole role, List<Order> orders) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.discount = discount;
        this.status = status;
        this.birthday = birthday;
        this.role = role;
        this.orders = orders;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {


        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        if (getDiscount() != userDTO.getDiscount()) return false;
        if (getFirstname() != null ? !getFirstname().equals(userDTO.getFirstname()) : userDTO.getFirstname() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(userDTO.getLastName()) : userDTO.getLastName() != null)
            return false;
        if (getUsername() != null ? !getUsername().equals(userDTO.getUsername()) : userDTO.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(userDTO.getPassword()) : userDTO.getPassword() != null)
            return false;
        if (getSalt() != null ? !getSalt().equals(userDTO.getSalt()) : userDTO.getSalt() != null) return false;
        if (getEmail() != null ? !getEmail().equals(userDTO.getEmail()) : userDTO.getEmail() != null) return false;
        if (getStatus() != userDTO.getStatus()) return false;
        if (getBirthday() != null ? !getBirthday().equals(userDTO.getBirthday()) : userDTO.getBirthday() != null)
            return false;
        if (getRole() != userDTO.getRole()) return false;
        return getOrders() != null ? getOrders().equals(userDTO.getOrders()) : userDTO.getOrders() == null;

    }

    @Override
    public int hashCode() {
        int result = getFirstname() != null ? getFirstname().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getSalt() != null ? getSalt().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + getDiscount();
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getOrders() != null ? getOrders().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", discount=" + discount +
                ", status=" + status +
                ", birthday=" + birthday +
                ", role=" + role +
                ", orders=" + orders +
                '}';
    }
}
