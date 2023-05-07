package com.xuananh.bookservice.query.projection;

import com.xuananh.bookservice.command.data.Book;
import com.xuananh.bookservice.command.data.BookRepository;
import com.xuananh.bookservice.query.model.BookResponseModel;
import com.xuananh.bookservice.query.queries.GetAllBooksQuery;
import com.xuananh.bookservice.query.queries.GetBookQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookProjection {
    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public BookResponseModel handle(GetBookQuery getBookQuery) {
        BookResponseModel bookResponseModel = new BookResponseModel();
        Book book = bookRepository.findById(getBookQuery.getBookId()).orElseThrow();
        BeanUtils.copyProperties(book, bookResponseModel);
        return bookResponseModel;
    }

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBooksQuery getAllBooksQuery) {
        List<Book> list = bookRepository.findAll();
        return list.stream().map(BookResponseModel::new).collect(Collectors.toList());
    }
}
