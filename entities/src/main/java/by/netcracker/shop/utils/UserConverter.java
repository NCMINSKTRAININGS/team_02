package by.netcracker.shop.utils;

import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.pojo.User;

public class UserConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO convertToFront(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setSalt(user.getSalt());
        dto.setEmail(user.getEmail());
        dto.setDiscount(user.getDiscount());
        dto.setStatus(user.getStatus());
        dto.setBirthday(user.getBirthday());
        dto.setRole(user.getRole());
        return dto;
    }

    @Override
    public User convertToLocal(UserDTO userDTO, User user) {
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setSalt(userDTO.getSalt());
        user.setEmail(userDTO.getEmail());
        user.setDiscount(userDTO.getDiscount());
        user.setStatus(userDTO.getStatus());
        user.setBirthday(userDTO.getBirthday());
        user.setRole(userDTO.getRole());
        return user;
    }
}
