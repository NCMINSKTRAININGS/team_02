package by.netcracker.shop.utils;

import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {
    public UserDTO toUserDTO(User user) {
        UserDTO userDTO;
        if (user == null)
            return null;
        userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setDiscount(user.getDiscount());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public User toUserPOJO(UserDTO userDTO, User user, List<Order> orders) {
        if (userDTO == null || user == null)
            return null;
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDiscount(userDTO.getDiscount());
        user.setBirthday(userDTO.getBirthday());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        user.setPassword(userDTO.getPassword());
        user.setOrders(orders);
        return user;
    }
}
