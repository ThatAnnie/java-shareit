package ru.practicum.shareit.exception;

public class StateIsNotSupportedException extends RuntimeException {
    public StateIsNotSupportedException(String message) {
        super(message);
    }
}
