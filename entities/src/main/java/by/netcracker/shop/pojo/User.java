package by.netcracker.shop.pojo;

import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", length = 45)
    private String firstName;
    @Column(name = "last_name", length = 45)
    private String lastName;
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "salt", nullable = false, length = 255)
    private String salt;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "discount", length = 2)
    private int discount;
    @Column(name = "status", columnDefinition = "ENUM('online', 'ofline', 'removed', 'banned')", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "role", columnDefinition = "ENUM('CLIENT', 'ADMIN')")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(){
        super();
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
}
