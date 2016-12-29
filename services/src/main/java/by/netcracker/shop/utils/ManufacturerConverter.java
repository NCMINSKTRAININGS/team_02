package by.netcracker.shop.utils;

import by.netcracker.shop.dto.ManufacturerDTO;
import by.netcracker.shop.pojo.Manufacturer;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerConverter {
    public ManufacturerDTO toManufacturerDTO(Manufacturer manufacturer) {
        ManufacturerDTO manufacturerDTO;
        if (manufacturer == null)
            return null;
        manufacturerDTO = new ManufacturerDTO();
        manufacturerDTO.setId(manufacturer.getId());
        manufacturerDTO.setName(manufacturer.getName());
        manufacturerDTO.setDescription(manufacturer.getDescription());
        manufacturerDTO.setLogo(manufacturer.getLogo());
        return manufacturerDTO;
    }

    public Manufacturer toManufacturerPOJO(ManufacturerDTO manufacturerDTO, Manufacturer manufacturer) {
        if (manufacturerDTO == null || manufacturer == null)
            return null;
        manufacturer.setName(manufacturerDTO.getName());
        manufacturer.setDescription(manufacturerDTO.getDescription());
        manufacturer.setLogo(manufacturerDTO.getLogo());
        return manufacturer;
    }
}
