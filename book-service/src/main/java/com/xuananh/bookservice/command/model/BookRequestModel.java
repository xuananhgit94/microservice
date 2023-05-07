package com.xuananh.bookservice.command.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestModel {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
