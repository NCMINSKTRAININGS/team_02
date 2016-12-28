package by.netcracker.shop.utils;

import by.netcracker.shop.dto.UsersOrdersDTO;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.OrderProduct;
import by.netcracker.shop.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersOrdersConverter {

    public UsersOrdersDTO toUsersOrdersDTO(User user, List<Order> usersOrder) {

        if (user == null||usersOrder==null) {
            return null;
        }else {
            UsersOrdersDTO usersOrdersDTO=new UsersOrdersDTO();
            usersOrdersDTO.setUserId(user.getId());
            usersOrdersDTO.setUsername(user.getUsername());
            usersOrdersDTO.setFirstName(user.getFirstName());
            usersOrdersDTO.setLastName(user.getLastName());
            usersOrdersDTO.setEmail(user.getEmail());
            usersOrdersDTO.setBirthday(user.getBirthday());
            usersOrdersDTO.setDiscount(user.getDiscount());
            usersOrdersDTO.setUserRole(user.getRole());
            usersOrdersDTO.setUserStatus(user.getStatus());
            usersOrdersDTO.setOrderIsntProduced(0);
            usersOrdersDTO.setOrderIsProduced(0);
            usersOrdersDTO.setOrderPrice(0d);
            for (Order order:usersOrder) {
                if (!order.getProduced()) {
                    usersOrdersDTO.setOrderIsntProduced(usersOrdersDTO.getOrderIsntProduced()+1);
                    for (OrderProduct product:order.getOrderProducts()) {
                        usersOrdersDTO.setOrderPrice(usersOrdersDTO.getOrderPrice()+product.getProduct().getPrice());
                    }
                }else {
                    usersOrdersDTO.setOrderIsProduced(usersOrdersDTO.getOrderIsProduced()+1);
                }
            }
            return usersOrdersDTO;
        }
    }

}
