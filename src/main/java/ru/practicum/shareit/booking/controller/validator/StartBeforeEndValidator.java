package ru.practicum.shareit.booking.controller.validator;

import ru.practicum.shareit.booking.dto.BookingDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartBeforeEndValidator implements ConstraintValidator<StartBeforeEnd, BookingDto> {
    @Override
    public boolean isValid(BookingDto booking, ConstraintValidatorContext cxt) {
        return booking.getStart().isBefore(booking.getEnd());
    }
}