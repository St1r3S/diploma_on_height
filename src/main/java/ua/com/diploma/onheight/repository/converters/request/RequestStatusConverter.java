package ua.com.diploma.onheight.repository.converters.request;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.com.diploma.onheight.model.request.RequestStatus;

@Converter(autoApply = true)
public class RequestStatusConverter implements AttributeConverter<RequestStatus, String> {

    @Override
    public String convertToDatabaseColumn(RequestStatus requestStatus) {
        if (requestStatus == null) {
            return null;
        }
        return requestStatus.getKey();
    }

    @Override
    public RequestStatus convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return RequestStatus.getByKey(s);
    }
}
