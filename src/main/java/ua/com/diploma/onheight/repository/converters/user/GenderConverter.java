package ua.com.diploma.onheight.repository.converters.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.com.diploma.onheight.model.user.Gender;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {
    
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getKey();
    }

    @Override
    public Gender convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return Gender.get(s);
    }
}
