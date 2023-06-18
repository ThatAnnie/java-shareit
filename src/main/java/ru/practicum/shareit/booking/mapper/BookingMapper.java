package ru.practicum.shareit.booking.mapper;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingForItemDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;

public class BookingMapper {
    public static Booking bookingDtoToBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setId(bookingDto.getId());
        booking.setStart(bookingDto.getStart());
        booking.setEnd(bookingDto.getEnd());
        return booking;
    }

    public static BookingResponseDto bookingToBookingResponseDto(Booking booking) {
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(booking.getId());
        bookingResponseDto.setStart(booking.getStart());
        bookingResponseDto.setEnd(booking.getEnd());
        ItemDto itemDto = ItemMapper.itemToItemDto(booking.getItem());
        bookingResponseDto.setItem(itemDto);
        UserDto bookerDto = UserMapper.userToUserDto(booking.getBooker());
        bookingResponseDto.setBooker(bookerDto);
        bookingResponseDto.setStatus(booking.getStatus());
        return bookingResponseDto;
    }

    public static BookingForItemDto bookingToBookingForItemDto(Booking booking) {
        BookingForItemDto bookingForItemDto = new BookingForItemDto();
        bookingForItemDto.setId(booking.getId());
        bookingForItemDto.setEnd(booking.getEnd());
        bookingForItemDto.setStart(booking.getStart());
        bookingForItemDto.setBookerId(booking.getBooker().getId());
        return bookingForItemDto;
    }
}
