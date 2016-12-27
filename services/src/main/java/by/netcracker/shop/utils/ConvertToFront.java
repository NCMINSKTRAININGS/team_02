package by.netcracker.shop.utils;

public interface ConvertToFront<Model, DTO> {
    DTO convertToFront(Model model);
}
