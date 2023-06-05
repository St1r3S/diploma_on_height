package ua.com.diploma.onheight.repository.converters.product;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.com.diploma.onheight.model.product.PriceCurrency;

@Converter(autoApply = true)
public class PriceCurrencyConverter implements AttributeConverter<PriceCurrency, String> {

    @Override
    public String convertToDatabaseColumn(PriceCurrency priceCurrency) {
        if (priceCurrency == null) {
            return null;
        }
        return priceCurrency.getKey();
    }

    @Override
    public PriceCurrency convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return PriceCurrency.getByKey(s);
    }
}
