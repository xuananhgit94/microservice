package com.xuananh.bookservice.command.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDeleteEvent {
    private String bookId;
}
