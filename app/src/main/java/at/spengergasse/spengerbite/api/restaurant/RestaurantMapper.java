package at.spengergasse.spengerbite.api.restaurant;

import at.spengergasse.spengerbite.model.restaurant.Restaurant;
import at.spengergasse.spengerbite.model.shared.Address;
import at.spengergasse.spengerbite.model.shared.OpeningHours;

// Mapper: translates between DTOs and the domain, no logic, no checks (the domain guards itself)
public final class RestaurantMapper {

    private RestaurantMapper() { }

    // Entity -> Response DTO
    public static RestaurantResponse toResponse(Restaurant restaurant) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    // Request DTO -> Entity
    public static Restaurant toEntity(CreateRestaurantRequest request) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    // Bonus
    // PUT: Request DTO -> Address
    public static Address toAddress(UpdateRestaurantRequest request) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    // Bonus
    // PUT: Request DTO -> OpeningHours
    public static OpeningHours toOpeningHours(UpdateRestaurantRequest request) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    // Bonus
    // PATCH: Request DTO -> OpeningHours
    public static OpeningHours toOpeningHours(ChangeOpeningHoursRequest request) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }
}
