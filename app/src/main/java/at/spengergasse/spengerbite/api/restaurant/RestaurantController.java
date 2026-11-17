package at.spengergasse.spengerbite.api.restaurant;

import at.spengergasse.spengerbite.model.restaurant.Restaurant;
import at.spengergasse.spengerbite.repository.RestaurantRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

// REST API: Restaurants
//
// Verb    Path                    Business logic                 Success                  Error
// GET     /api/restaurants        list all restaurants           200 OK                   -
// GET     /api/restaurants/{id}   get one restaurant             200 OK                   404
// POST    /api/restaurants        create a restaurant            201 Created + Location   400
// PUT     /api/restaurants/{id}   replace a restaurant (bonus)   200 OK                   400, 404
// PATCH   /api/restaurants/{id}   change opening hours (bonus)   200 OK                   400, 404
// DELETE  /api/restaurants/{id}   delete a restaurant            204 No Content           404, 409
//
// 400 invalid input | 404 id not found | 409 valid, but the current state forbids it

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurants;

    public RestaurantController(RestaurantRepository restaurants) {
        this.restaurants = restaurants;
    }


    // GET /api/restaurants
    // -> 200 OK  List<RestaurantResponse>
    @GetMapping
    public ResponseEntity<?> getAllRestaurants() {
        // TODO Fetch all, map, respond
        throw new UnsupportedOperationException("TODO");
    }


    // GET /api/restaurants/{id}
    // -> 200 OK         RestaurantResponse
    // -> 404 Not Found  ProblemDetail
    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable Long id) {
        // TODO Fetch, Guard: 404, map + respond
        throw new UnsupportedOperationException("TODO");
    }


    // POST /api/restaurants
    // -> 201 Created      RestaurantResponse, Location header
    // -> 400 Bad Request  ProblemDetail
    @PostMapping
    public ResponseEntity<?> createRestaurant(@Valid @RequestBody CreateRestaurantRequest request) {
        // @Valid: Spring answers invalid requests with 400 before this method runs
        // TODO try: map to entity, save, respond 201 + Location
        // TODO catch IllegalArgumentException (thrown by the domain): respond 400
        throw new UnsupportedOperationException("TODO");
    }


    // Bonus
    // PUT /api/restaurants/{id}
    // -> 200 OK           RestaurantResponse
    // -> 400 Bad Request  ProblemDetail
    // -> 404 Not Found    ProblemDetail
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id,
                                              @Valid @RequestBody UpdateRestaurantRequest request) {
        // @Valid: Spring answers invalid requests with 400 before this method runs
        // TODO Fetch, Guard: 404
        // TODO try: restaurant.update(...), save, map + respond 200
        // TODO catch IllegalArgumentException (thrown by the domain): respond 400
        throw new UnsupportedOperationException("TODO");
    }


    // Bonus
    // PATCH /api/restaurants/{id}
    // -> 200 OK           RestaurantResponse
    // -> 400 Bad Request  ProblemDetail
    // -> 404 Not Found    ProblemDetail
    @PatchMapping("/{id}")
    public ResponseEntity<?> changeOpeningHours(@PathVariable Long id,
                                                @Valid @RequestBody ChangeOpeningHoursRequest request) {
        // @Valid: Spring answers invalid requests with 400 before this method runs
        // TODO Fetch, Guard: 404
        // TODO try: restaurant.changeOpeningHours(...), save, map + respond 200
        // TODO catch IllegalArgumentException (thrown by the domain): respond 400
        throw new UnsupportedOperationException("TODO");
    }


    // DELETE /api/restaurants/{id}
    // -> 204 No Content  no body
    // -> 404 Not Found   ProblemDetail
    // -> 409 Conflict    ProblemDetail
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        // TODO Guard: 404
        // TODO try: delete, respond 204
        // TODO catch DataIntegrityViolationException (menu items point to it): respond 409
        throw new UnsupportedOperationException("TODO");
    }


    // Helper: error response as ProblemDetail
    private static ResponseEntity<?> problem(HttpStatus status, String detail) {
        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(status, detail)).build();
    }
}
