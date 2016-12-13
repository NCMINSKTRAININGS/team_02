package by.netcracker.shop.converter;

/**
 * Created by j on 8.12.16.
 */
public interface Converter<Model, DTO> extends ConverterToLocal<Model,DTO> ,ConvertToFront<Model,DTO>{
}
