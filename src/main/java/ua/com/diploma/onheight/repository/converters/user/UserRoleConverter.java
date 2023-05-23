package ua.com.diploma.onheight.repository.converters.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.com.diploma.onheight.model.user.UserRole;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {
    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        if (userRole == null) {
            return null;
        }
        return userRole.getKey();
    }

    @Override
    public UserRole convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return UserRole.get(s);
    }
}
