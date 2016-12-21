package by.netcracker.shop.utils;

public interface ConverterToLocal<Model,DTO> {
    Model convertToLocal(DTO dto, Model model);
}
