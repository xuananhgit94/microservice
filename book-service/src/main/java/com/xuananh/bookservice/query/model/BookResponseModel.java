package com.xuananh.bookservice.query.model;

import com.xuananh.bookservice.command.data.Book;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseModel {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;

    public BookResponseModel(Book book) {
        BeanUtils.copyProperties(book, this);
    }
}
