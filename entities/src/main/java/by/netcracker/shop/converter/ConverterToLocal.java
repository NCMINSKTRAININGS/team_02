package by.netcracker.shop.converter;

/**
 * Created by j on 8.12.16.
 */
public interface ConverterToLocal<Model,DTO> {
    Model converToLocal(DTO dto,Model model);
}
