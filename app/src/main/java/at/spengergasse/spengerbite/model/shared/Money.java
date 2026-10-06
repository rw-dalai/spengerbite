package at.spengergasse.spengerbite.model.shared;

import at.spengergasse.spengerbite.model.Guard;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount, String currency) {

    public Money {
        amount = Guard.notNegative(amount, "amount")
            .setScale(2, RoundingMode.HALF_UP);

        currency = Guard.hasText(currency, "currency")
            .toUpperCase();
    }
}
