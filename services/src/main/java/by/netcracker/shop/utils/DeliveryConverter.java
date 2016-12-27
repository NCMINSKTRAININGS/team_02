package by.netcracker.shop.utils;


import by.netcracker.shop.dto.DeliveryDto;
import by.netcracker.shop.pojo.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryConverter implements Converter<Delivery,DeliveryDto>{
    @Override
    public DeliveryDto convertToFront(Delivery delivery) {
        DeliveryDto deliveryDto= new DeliveryDto();
        deliveryDto.setId(delivery.getId());
        deliveryDto.setName(delivery.getName());
        deliveryDto.setDescription(delivery.getDescription());

        return deliveryDto;
    }

    @Override
    public Delivery convertToLocal(DeliveryDto deliveryDto, Delivery delivery) {
        delivery.setName(deliveryDto.getName());
        delivery.setDescription(deliveryDto.getDescription());
        return delivery;
    }
}
