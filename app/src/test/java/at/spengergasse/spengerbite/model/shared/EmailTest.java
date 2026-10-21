package at.spengergasse.spengerbite.model.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Rich type test: the constructor is the guard, no Spring, no database
public class EmailTest {

    @Test
    public void Email_ShouldNormalize_WhenMixedCaseAndWhitespace() {

        // Given
        String raw = "  Max@Spengergasse.AT ";

        // When
        Email email = new Email(raw);

        // Then
        assertEquals("max@spengergasse.at", email.value());
    }

    @Test
    public void Email_ShouldThrow_WhenMissingAt() {

        // Given
        String raw = "max.spengergasse.at";

        // When
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> new Email(raw));

        // Then
        assertTrue(ex.getMessage().contains("email"));
    }
}
