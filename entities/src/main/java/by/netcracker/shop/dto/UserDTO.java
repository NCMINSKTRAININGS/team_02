package by.netcracker.shop.dto;

import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

public class UserDTO {
    private Long id;
    @Size(max = 45)
    private String firstName;
    @Size(max = 45)
    private String lastName;
    @NotBlank
    @Size(min = 5, max = 45)
    private String username;
    @NotBlank
    @Size(min = 5, max = 45)
    private String password;
    private String email;
    private Double discount;
    private UserStatus status;
    private Date birthday;
    private UserRole role;

    public UserDTO() {
    }

    public UserDTO(String firstname, String lastName, String username, String password,
                   String email, Double discount, UserStatus status, Date birthday,
                   UserRole role) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.discount = discount;
        this.status = status;
        this.birthday = birthday;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
