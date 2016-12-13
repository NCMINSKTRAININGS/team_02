package by.netcracker.shop.converter;

/**
 * Created by j on 8.12.16.
 */
public interface ConvertToFront<Model, DTO> {
    DTO convertToFront(Model model);
}
