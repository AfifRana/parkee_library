package com.parkee.library_management.service;

import com.parkee.library_management.model.entity.Book;
import com.parkee.library_management.model.entity.Borrower;
import com.parkee.library_management.model.entity.Borrowing;
import com.parkee.library_management.model.request.NewBorrowingRq;
import com.parkee.library_management.repository.BorrowingRepository;
import com.parkee.library_management.util.GeneralUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Afif Rana M
 * on 23/02/2025
 */
@Service
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BookService bookService;
    private final BorrowerService borrowerService;

    public BorrowingService(@Autowired BorrowingRepository borrowingRepository,
                            @Autowired BookService bookService,
                            @Autowired BorrowerService borrowerService) {
        this.borrowingRepository = borrowingRepository;
        this.bookService = bookService;
        this.borrowerService = borrowerService;
    }

    public List<Borrowing> getBorrowings(Long borrowerId, Long bookId) {
        if (borrowerId != null && bookId != null) {
            return borrowingRepository.findByBorrowerIdAndBookId(borrowerId, bookId);
        } else if (borrowerId != null) {
            return borrowingRepository.findByBorrowerId(borrowerId);
        } else if (bookId != null) {
            return borrowingRepository.findByBookId(bookId);
        } else {
            return borrowingRepository.findAll();
        }
    }

    public Borrowing getBorrowingByBorrowingId(Long borrowingId) {
        return borrowingRepository.findById(borrowingId).orElse(new Borrowing());
    }

    public Borrowing addBorrowing(NewBorrowingRq newBorrowingRq) {
        // Check if the books are available
        if (bookService.getBookByIsbn(newBorrowingRq.getBookIsbn()).getStock() < 2) {
            throw new RuntimeException("Book with ISBN " + newBorrowingRq.getBookIsbn() + " is not available to borrow");
        }

        // Borrower only can have 1 active borrowing
        if (!borrowingRepository.findByBorrowerIdAndStatus(newBorrowingRq.getBorrowerId(), Borrowing.Status.BORROWED).isEmpty()) {
            throw new RuntimeException("Borrower with ID " + newBorrowingRq.getBorrowerId() + " already has an active borrowing");
        }

        // The maximum duration for borrowing books is 30 days
        if (GeneralUtility.isMoreThan30DaysFromNow(newBorrowingRq.getReturnDeadline())) {
            throw new RuntimeException("The maximum duration for borrowing books is 30 days");
        }

        // Retrieve book
        Book book = bookService.getBookByIsbn(newBorrowingRq.getBookIsbn());

        // Retrieve borrower
        Borrower borrower = borrowerService.getBorrowerById(newBorrowingRq.getBorrowerId());

        Borrowing borrowing = new Borrowing();
        borrowing.setBorrower(borrower);
        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setReturnDeadline(newBorrowingRq.getReturnDeadline());
        borrowing.setStatus(Borrowing.Status.BORROWED);
        borrowingRepository.save(borrowing);
        return borrowingRepository.save(borrowing);
    }

    public Borrowing updateBorrowing(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElse(new Borrowing());
        LocalDate currentDate = LocalDate.now();

        if (borrowing.getReturnDeadline().isBefore(currentDate)) {
            borrowing.setStatus(Borrowing.Status.LATE);
        } else {
            borrowing.setStatus(Borrowing.Status.RETURNED);
        }

        return borrowingRepository.save(borrowing);
    }
}
