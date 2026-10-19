package at.spengergasse.spengerbite.persistence.converters;

import at.spengergasse.spengerbite.model.shared.PasswordHash;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PasswordHashingConverter implements AttributeConverter<PasswordHash,String> {
    @Override
    public String convertToDatabaseColumn(PasswordHash passwordHash) {
        return passwordHash != null ? passwordHash.value() : null; // Entity -> DB
    }

    @Override
    public PasswordHash convertToEntityAttribute(String dbData) {
        return  dbData != null ? new PasswordHash(dbData) : null; // DB -> Entity
    }
}
