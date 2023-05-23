package ua.com.diploma.onheight.repository.converters.request;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.com.diploma.onheight.model.request.DeliveryMethod;

@Converter(autoApply = true)
public class DeliveryMethodConverter implements AttributeConverter<DeliveryMethod, String> {
    @Override
    public String convertToDatabaseColumn(DeliveryMethod deliveryMethod) {
        if (deliveryMethod == null) {
            return null;
        }
        return deliveryMethod.getKey();
    }

    @Override
    public DeliveryMethod convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return DeliveryMethod.get(s);
    }
}
