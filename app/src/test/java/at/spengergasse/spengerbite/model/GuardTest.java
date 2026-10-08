package at.spengergasse.spengerbite.model;

// SUT_ShouldXXX_WhenXXX

// SUT = What will be tested
// Should = What is expected to happen
// When = Under which condition

// Examples:
// SUT = HasText, Email
// Should = ShouldReturnStrippedValue, ShouldThrow, ShouldSetStatusCancelled
// When = WhenTextHasWhitespace, WhenOrderIsCancelled

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuardTest {


   @Test
   public void Email_ShouldReturnStrippedValue_WhenTextHasWhitespace() {

       // Given
       String name = " Pizzeria Mario ";

       // When
       String result = Guard.hasText(name, "name");

       // Then
       assertEquals("Pizzeria Mario", result);
   }

    @Test
   public void Email_ShouldThrow_WhenTextHasOnlyWhitespace() {

       // Given
       String name = "   ";

       // When
       IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
           () -> Guard.hasText(name, "name"));

       // Then
       assertTrue(ex.getMessage().contains("name"));
   }
}
