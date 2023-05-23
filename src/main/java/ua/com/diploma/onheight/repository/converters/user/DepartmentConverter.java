package ua.com.diploma.onheight.repository.converters.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.com.diploma.onheight.model.user.Department;

@Converter(autoApply = true)
public class DepartmentConverter implements AttributeConverter<Department, String> {

    @Override
    public String convertToDatabaseColumn(Department department) {
        if (department == null) {
            return null;
        }
        return department.getKey();
    }

    @Override
    public Department convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return Department.get(s);
    }
}
