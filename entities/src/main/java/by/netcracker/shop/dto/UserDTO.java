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

    public UserDTO(UserDTO user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getDiscount(), user.getStatus(), user.getBirthday(), user.getRole());
    }

    public UserDTO(Long id, String firstName, String lastName, String username, String password,
                   String email, Double discount, UserStatus status, Date birthday,
                   UserRole role) {
        this.id = id;
        this.firstName = firstName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (id != null ? !id.equals(userDTO.id) : userDTO.id != null) return false;
        if (firstName != null ? !firstName.equals(userDTO.firstName) : userDTO.firstName != null) return false;
        if (lastName != null ? !lastName.equals(userDTO.lastName) : userDTO.lastName != null) return false;
        if (username != null ? !username.equals(userDTO.username) : userDTO.username != null) return false;
        if (password != null ? !password.equals(userDTO.password) : userDTO.password != null) return false;
        if (email != null ? !email.equals(userDTO.email) : userDTO.email != null) return false;
        if (discount != null ? !discount.equals(userDTO.discount) : userDTO.discount != null) return false;
        if (status != userDTO.status) return false;
        if (birthday != null ? !birthday.equals(userDTO.birthday) : userDTO.birthday != null) return false;
        return role == userDTO.role;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", discount=" + discount +
                ", status=" + status +
                ", birthday=" + birthday +
                ", role=" + role +
                '}';
    }
}
