package at.spengergasse.spengerbite.persistence.converters;

import at.spengergasse.spengerbite.model.shared.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email,String> {
    @Override
    public String convertToDatabaseColumn(Email email) {
        return email != null ? email.value() : null; // Entity -> DB
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return  dbData != null ? new Email(dbData) : null; // DB -> Entity
    }
}
