package com.xuananh.bookservice.command.event;

import com.xuananh.bookservice.command.data.Book;
import com.xuananh.bookservice.command.data.BookRepository;
import jakarta.ws.rs.NotFoundException;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookEventHandler {
    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreateEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdateEvent event) {
        Book book = bookRepository.findById(event.getBookId()).orElseThrow();
        book.setAuthor(event.getAuthor());
        book.setName(event.getName());
        book.setIsReady(event.getIsReady());
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookDeleteEvent event) {
        bookRepository.deleteById(event.getBookId());
    }
}
