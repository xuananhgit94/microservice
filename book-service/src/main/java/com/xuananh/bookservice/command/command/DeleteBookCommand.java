package com.xuananh.bookservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteBookCommand {
    @TargetAggregateIdentifier
    private String bookId;
}
