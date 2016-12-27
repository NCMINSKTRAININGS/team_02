package by.netcracker.shop.utils;

import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {
    public UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBirthday(user.getBirthday());
        dto.setDiscount(user.getDiscount());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public User toUserPOJO(UserDTO userDTO, User user, List<Order> orders) {
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
