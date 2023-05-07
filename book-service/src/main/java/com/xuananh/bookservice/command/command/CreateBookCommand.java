package com.xuananh.bookservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookCommand {
    @TargetAggregateIdentifier
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
