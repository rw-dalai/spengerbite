package at.spengergasse.spengerbite.model;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import java.math.BigDecimal;
import java.util.Collection;

/** Guard checks arguments, throws IllegalArgumentException (400) and returns the normalized value. */
public final class Guard {

    // libphonenumber: one shared instance, numbers without country code are read as Austrian
    private static final PhoneNumberUtil PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();
    private static final String DEFAULT_REGION = "AT";

    private Guard() { }

    // --- Common Guards ---

    /** Value must not be null. */
    public static <T> T notNull(T value, String field) {
        if (value == null) {
            throw new IllegalArgumentException(field + " cannot be null");
        }
        return value;
    }

    /** Text must not be null or blank, returns it stripped. */
    public static String hasText(String value, String field) {
        // check
        notNull(value, field);
        if (value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be blank");
        }
        // normalize
        return value.strip();
    }

    /** Value must not be negative, zero is fine. */
    public static BigDecimal notNegative(BigDecimal value, String field) {
        notNull(value, field);
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(field + " must be non-negative, but was " + value);
        }
        return value;
    }

    /** Number must be greater than zero. */
    public static int positive(int value, String field) {
        if (value <= 0) {
            throw new IllegalArgumentException(field + " must be positive, but was " + value);
        }
        return value;
    }

    /** Collection must not be null or empty. */
    public static <T extends Collection<?>> T notEmpty(T value, String field) {
        notNull(value, field);
        if (value.isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty");
        }
        return value;
    }

    /** Text must have exactly the given length. */
    public static String hasLength(String value, int length, String field) {
        String text = hasText(value, field);
        if (text.length() != length) {
            throw new IllegalArgumentException(field + " must have " + length + " characters, but was " + text);
        }
        return text;
    }

    /** Any condition the caller must meet. */
    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    // --- Domain Guards ---

    /** Email must contain an @, returns it lowercase. */
    public static String email(String value, String field) {
        // check
        String email = hasText(value, field);
        if (!email.contains("@")) {
            throw new IllegalArgumentException(
                field + " must be a valid email address, but was " + email);
        }
        // normalize
        return email.toLowerCase();
    }

    /** Phone in national or international notation, returns E.164 like +436601234567. */
    public static String phone(String value, String field) {
        String rawPhone = hasText(value, field);
        try {
            // check if the phone number is valid for Austria (AT)
            PhoneNumber phone = PHONE_NUMBER_UTIL.parse(rawPhone, DEFAULT_REGION);
            if (!PHONE_NUMBER_UTIL.isValidNumber(phone)) {
                throw new IllegalArgumentException(
                    field + " must be a valid phone number, but was " + rawPhone);
            }
            // normalize to E.164 format
            return PHONE_NUMBER_UTIL.format(phone, PhoneNumberUtil.PhoneNumberFormat.E164);

        } catch (NumberParseException e) {
            throw new IllegalArgumentException(
                field + " must be a valid phone number, but was " + rawPhone, e);
        }
    }
}
