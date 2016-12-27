package by.netcracker.shop.pojo;

import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "first_name", length = 45)
    private String firstName;
    @Column(name = "last_name", length = 45)
    private String lastName;
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "discount", length = 2)
    private Double discount;
    @Column(name = "status", columnDefinition = "ENUM('ONLINE', 'OFLINE', 'REMOVED', 'BANNED')", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "role", columnDefinition = "ENUM('CLIENT', 'ADMIN')")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders;

    public User(){
        super();
    }

    public User(String firstName, String lastName, String username, String password, String email, Double discount,
                UserStatus status, Date birthday, UserRole role, List<Order> orders) {
        this(null, firstName, lastName, username, password, email, discount, status, birthday, role, orders);
    }

    public User(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getDiscount(), user.getStatus(), user.getBirthday(), user.getRole(), user.getOrders());
    }

    public User(Long id, String firstName, String lastName, String username, String password,
                String email, Double discount, UserStatus status, Date birthday, UserRole role,List<Order> orders) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.discount = discount;
        this.status = status;
        this.birthday = birthday;
        this.role = role;
        this.orders = orders;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (discount != user.discount) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (status != user.status) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
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
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", discount=" + discount +
                ", status=" + status +
                ", birthday=" + birthday +
                ", role=" + role +
                "} " + super.toString();
    }
}
