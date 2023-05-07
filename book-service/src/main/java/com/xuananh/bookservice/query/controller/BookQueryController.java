package com.xuananh.bookservice.query.controller;

import com.xuananh.bookservice.query.model.BookResponseModel;
import com.xuananh.bookservice.query.queries.GetAllBooksQuery;
import com.xuananh.bookservice.query.queries.GetBookQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{bookId}")
    public BookResponseModel getBookDetail(@PathVariable String bookId) {
        GetBookQuery getBookQuery = new GetBookQuery(bookId);
        return queryGateway.query(getBookQuery, ResponseTypes.instanceOf(BookResponseModel.class)).join();
    }

    @GetMapping
    public List<BookResponseModel> getAll() {
        GetAllBooksQuery getAllBooksQuery = new GetAllBooksQuery();
        return queryGateway.query(getAllBooksQuery, ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
    }
}
