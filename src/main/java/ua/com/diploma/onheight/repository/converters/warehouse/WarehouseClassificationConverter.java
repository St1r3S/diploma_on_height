package ua.com.diploma.onheight.repository.converters.warehouse;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.com.diploma.onheight.model.warehouse.WarehouseClassification;

@Converter(autoApply = true)
public class WarehouseClassificationConverter implements AttributeConverter<WarehouseClassification, String> {
    
    @Override
    public String convertToDatabaseColumn(WarehouseClassification warehouseClassification) {
        if (warehouseClassification == null) {
            return null;
        }
        return warehouseClassification.getKey();
    }

    @Override
    public WarehouseClassification convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return WarehouseClassification.get(s);
    }
}
