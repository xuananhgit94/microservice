package com.xuananh.bookservice.command.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreateEvent {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
