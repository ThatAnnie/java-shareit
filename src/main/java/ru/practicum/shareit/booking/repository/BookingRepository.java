package ru.practicum.shareit.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByBookerId(Long userId);

    List<Booking> findAllByBookerIdAndEndIsBefore(Long userId, LocalDateTime now);

    List<Booking> findAllByBookerIdAndStartIsAfter(Long userId, LocalDateTime now);

    List<Booking> findAllByBookerIdAndStatus(Long userId, BookingStatus waiting);

    @Query("select b from Booking b where b.booker.id = ?1 and b.start < ?2 and b.end > ?2")
    List<Booking> findAllByBookerCurrentDate(Long userId, LocalDateTime now);

    @Query("select b from Booking b where b.item.owner.id = ?1")
    List<Booking> findAllByOwnerId(Long userId);

    @Query("select b from Booking b where b.item.owner.id = ?1 and b.end < ?2")
    List<Booking> findAllByOwnerIdAndEndIsBefore(Long userId, LocalDateTime now);

    @Query("select b from Booking b where b.item.owner.id = ?1 and b.start > ?2")
    List<Booking> findAllByOwnerIdAndStartIsAfter(Long userId, LocalDateTime now);

    @Query("select b from Booking b where b.item.owner.id = ?1 and b.start < ?2 and b.end > ?2")
    List<Booking> findAllByOwnerCurrentDate(Long userId, LocalDateTime now);

    @Query("select b from Booking b where b.item.owner.id = ?1 and b.status = ?2")
    List<Booking> findAllByOwnerIdAndStatus(Long userId, BookingStatus waiting);

    @Query("select b from Booking b where b.item.id = ?1 and b.start < ?2 and b.status = 'APPROVED' order by b.end DESC")
    List<Booking> findItemLastBookings(Long itemId, LocalDateTime start);

    @Query("select b from Booking b where b.item.id = ?1 and b.start > ?2 and b.status = 'APPROVED' order by b.start")
    List<Booking> findItemNextBookings(Long itemId, LocalDateTime start);

    Optional<Booking> findFirstByBookerAndItemIdAndEndBefore(User booker, Long itemId, LocalDateTime date);
}
