package ua.com.diploma.onheight.repository.converters.request;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.com.diploma.onheight.model.request.PaymentMethod;

@Converter(autoApply = true)
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, String> {
    
    @Override
    public String convertToDatabaseColumn(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        return paymentMethod.getKey();
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return PaymentMethod.get(s);
    }
}
