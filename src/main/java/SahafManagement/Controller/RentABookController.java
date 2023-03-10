package SahafManagement.Controller;

import SahafManagement.Exception.BookNotAvailableException;
import SahafManagement.Exception.BookNotFoundException;
import SahafManagement.Exception.BookstoreNotFoundException;
import SahafManagement.Exception.UserNotFoundException;
import SahafManagement.Service.RentABookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/bookstorerent")
public class RentABookController {

    private RentABookService rentABookService;

    public RentABookController(RentABookService rentABookService) {
        this.rentABookService = rentABookService;
    }

    @PostMapping("/rent")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> rentBook(@RequestParam Long userId, @RequestParam Long bookstoreId,
                                           @RequestParam Long bookId, @RequestParam LocalDate rentalDate,
                                           @RequestParam LocalDate returnDate) {
        try {
            rentABookService.rentBook(userId, bookstoreId, bookId, rentalDate, returnDate);
            return ResponseEntity.ok("User #" + userId + " rented book number  #" + bookId + " from bookstore #"+ bookstoreId + " between " + rentalDate + " - " + returnDate);
        } catch (BookstoreNotFoundException | UserNotFoundException | BookNotFoundException | BookNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
