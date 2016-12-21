package by.netcracker.shop.utils;

import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User,UserDTO> {
    @Override
    public UserDTO convertToFront(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstname(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBirthday(user.getBirthday());
        dto.setDiscount(user.getDiscount());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setSalt(user.getSalt());
        dto.setPassword(user.getPassword());
        if (user.getOrders().iterator().hasNext()){
            dto.setOrders(user.getOrders());
        }
        return dto;
    }


    @Override
    public User convertToLocal(UserDTO userDTO, User user) {
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setSalt(userDTO.getSalt());
        user.setDiscount(userDTO.getDiscount());
        user.setBirthday(userDTO.getBirthday());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        user.setPassword(userDTO.getPassword());

        if (userDTO.getOrders().iterator().hasNext()){
            user.setOrders(userDTO.getOrders());
        }
        return user;
    }
}
