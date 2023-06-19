package ru.practicum.shareit.booking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookingDto {
    private Long id;
    @FutureOrPresent
    @NotNull
    private LocalDateTime start;
    @FutureOrPresent
    @NotNull
    private LocalDateTime end;
    @NotNull
    private Long itemId;
}
