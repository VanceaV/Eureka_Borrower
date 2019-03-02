package com.smoothstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.entity.BookLoans;
import com.smoothstack.service.BookCopiesService;
import com.smoothstack.service.BookLoansService;

@RestController
@RequestMapping("/lms")
public class BookLoansController {

	@Autowired
	private BookLoansService bookLoansService;

	@Autowired
	private BookCopiesService bookCopiesRepository;

	@PostMapping("/borrower/checkOutABook")
	public ResponseEntity<BookLoans> checkOutABook(@RequestParam("bookId") long bookId,
			@RequestParam("branchId") long branchId, @RequestParam("cardNo") long cardNo,
			@RequestParam("dateOut") String dateOut, @RequestParam("dateDue") String dateDue) {

		try {
			bookLoansService.create(bookId, branchId, cardNo, dateOut, dateDue);
			bookCopiesRepository.decrementNumberOfCopiesbyOne(bookId, branchId);
		} catch (Exception e) {
			return new ResponseEntity<BookLoans>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<BookLoans>(HttpStatus.CREATED);
	}

	@DeleteMapping("/borrower/returnABook")
	public ResponseEntity<BookLoans> returnABook(@RequestParam("bookId") long bookId,
			@RequestParam("branchId") long branchId, @RequestParam("cardNo") long cardNo) {

		try {
			bookLoansService.delete(bookId, branchId, cardNo);
			bookCopiesRepository.incrementNumberOfCopiesbyOne(bookId, branchId);
		} catch (Exception e) {
			return new ResponseEntity<BookLoans>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<BookLoans>(HttpStatus.ACCEPTED);

	}
}
