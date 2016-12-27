package by.netcracker.shop.utils;


import by.netcracker.shop.dto.DeliveryDTO;
import by.netcracker.shop.pojo.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryConverter {
    public DeliveryDTO toDeliveryDTO(Delivery delivery) {
        DeliveryDTO deliveryDTO;
        if (delivery == null)
            return null;
        deliveryDTO = new DeliveryDTO();
        deliveryDTO.setId(delivery.getId());
        deliveryDTO.setName(delivery.getName());
        deliveryDTO.setDescription(delivery.getDescription());
        return deliveryDTO;
    }

    public Delivery toDeliveryPOJO(DeliveryDTO deliveryDTO, Delivery delivery) {
        if (deliveryDTO == null || delivery == null)
            return null;
        delivery.setName(deliveryDTO.getName());
        delivery.setDescription(deliveryDTO.getDescription());
        return delivery;
    }
}
