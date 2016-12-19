package by.netcracker.shop.utils;

public interface ConverterToLocal<Model,DTO> {
    Model converToLocal(DTO dto,Model model);
}
