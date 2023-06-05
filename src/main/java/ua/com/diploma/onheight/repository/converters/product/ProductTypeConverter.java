package ua.com.diploma.onheight.repository.converters.product;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.com.diploma.onheight.model.product.ProductType;

@Converter(autoApply = true)
public class ProductTypeConverter implements AttributeConverter<ProductType, String> {

    @Override
    public String convertToDatabaseColumn(ProductType productType) {
        if (productType == null) {
            return null;
        }
        return productType.getKey();
    }

    @Override
    public ProductType convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return ProductType.getByKey(s);
    }
}
