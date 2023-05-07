package com.xuananh.bookservice.command.aggregate;

import com.xuananh.bookservice.command.command.CreateBookCommand;
import com.xuananh.bookservice.command.command.DeleteBookCommand;
import com.xuananh.bookservice.command.command.UpdateBookCommand;
import com.xuananh.bookservice.command.event.BookCreateEvent;
import com.xuananh.bookservice.command.event.BookDeleteEvent;
import com.xuananh.bookservice.command.event.BookUpdateEvent;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Aggregate
public class BookAggregate {
    @AggregateIdentifier
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand createBookCommand) {
        BookCreateEvent bookCreateEvent = new BookCreateEvent();
        BeanUtils.copyProperties(createBookCommand, bookCreateEvent);
        AggregateLifecycle.apply(bookCreateEvent);
    }

    @EventSourcingHandler
    public void on(BookCreateEvent event) {
        this.bookId = event.getBookId();
        this.author = event.getAuthor();
        this.name = event.getName();
        this.isReady = event.getIsReady();
    }

    @CommandHandler
    public void handle(UpdateBookCommand updateBookCommand) {
        BookUpdateEvent bookUpdateEvent = new BookUpdateEvent();
        BeanUtils.copyProperties(updateBookCommand, bookUpdateEvent);
        AggregateLifecycle.apply(bookUpdateEvent);
    }

    @CommandHandler
    public void handle(DeleteBookCommand deleteBookCommand) {
        BookDeleteEvent bookDeleteEvent = new BookDeleteEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeleteEvent);
        AggregateLifecycle.apply(bookDeleteEvent);
    }

    @EventSourcingHandler
    public void on(BookUpdateEvent event) {
        this.bookId = event.getBookId();
        this.author = event.getAuthor();
        this.name = event.getName();
        this.isReady = event.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookDeleteEvent event) {
        this.bookId = event.getBookId();
    }
}
