package by.netcracker.shop.utils;

import by.netcracker.shop.dto.ManufacturerDTO;
import by.netcracker.shop.pojo.Manufacturer;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerConverter implements Converter<Manufacturer, ManufacturerDTO> {

    @Override
    public ManufacturerDTO convertToFront(Manufacturer manufacturer) {
        ManufacturerDTO dto = new ManufacturerDTO();
        dto.setId(manufacturer.getId());
        dto.setName(manufacturer.getName());
        dto.setDescription(manufacturer.getDescription());
        dto.setLogo(manufacturer.getLogo());
        return dto;
    }

    @Override
    public Manufacturer convertToLocal(ManufacturerDTO manufacturerDTO, Manufacturer manufacturer) {
        manufacturer.setName(manufacturerDTO.getName());
        manufacturer.setDescription(manufacturerDTO.getDescription());
        manufacturer.setLogo(manufacturerDTO.getLogo());
        return manufacturer;
    }
}
