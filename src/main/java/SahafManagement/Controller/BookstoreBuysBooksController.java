package SahafManagement.Controller;

import SahafManagement.Exception.BookAvailableException;
import SahafManagement.Exception.BookNotFoundException;
import SahafManagement.Exception.BookstoreNotFoundException;
import SahafManagement.Service.BookstoreBuysBooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstorepurchase")
public class BookstoreBuysBooksController {
    private BookstoreBuysBooksService bookstoreBuysBooksService;

    public BookstoreBuysBooksController(BookstoreBuysBooksService bookstoreBuysBooksService) {
        this.bookstoreBuysBooksService = bookstoreBuysBooksService;
    }

    @PutMapping("/book/{bookId}/add-to-bookstore/{bookstoreId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> saveBookToBookstore(@PathVariable Long bookId, @PathVariable Long bookstoreId) {
        try {
            bookstoreBuysBooksService.saveBookToBookstore(bookId, bookstoreId);
            return ResponseEntity.ok( "Book store #"+bookstoreId + " bought book #" + bookId);
        } catch (BookstoreNotFoundException | BookNotFoundException | BookAvailableException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}

