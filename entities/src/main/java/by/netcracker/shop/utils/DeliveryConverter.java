package by.netcracker.shop.utils;


import by.netcracker.shop.dto.DeliveryDTO;
import by.netcracker.shop.pojo.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryConverter implements Converter<Delivery,DeliveryDTO>{
    @Override
    public DeliveryDTO convertToFront(Delivery delivery) {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setId(delivery.getId());
        deliveryDTO.setName(delivery.getName());
        deliveryDTO.setDescription(delivery.getDescription());

        return deliveryDTO;
    }

    @Override
    public Delivery convertToLocal(DeliveryDTO deliveryDTO, Delivery delivery) {
        delivery.setName(deliveryDTO.getName());
        delivery.setDescription(deliveryDTO.getDescription());
        return delivery;
    }
}
