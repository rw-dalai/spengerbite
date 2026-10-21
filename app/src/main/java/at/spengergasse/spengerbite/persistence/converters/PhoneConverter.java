package at.spengergasse.spengerbite.persistence.converters;

import at.spengergasse.spengerbite.model.shared.Phone;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PhoneConverter implements AttributeConverter<Phone,String> {
    @Override
    public String convertToDatabaseColumn(Phone phone) {
        return phone != null ? phone.value() : null; // Entity -> DB
    }

    @Override
    public Phone convertToEntityAttribute(String dbData) {
        return  dbData != null ? new Phone(dbData) : null; // DB -> Entity
    }
}
