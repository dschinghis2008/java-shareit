package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("select b from Booking b join Item i on b.item=i.id where b.id=?1 and (b.user=?2 or i.owner=?2)")
    Optional<Booking> getById(Integer bookingId,Integer userId);

    @Query("select distinct b from Booking b join Item i on i.id=b.item and b.user=?1 and b.status=?2 "
            + "and b.user <> i.owner order by b.start desc")
    Collection<Booking> getBookingsByUserAndStatus(Integer userId, Status status);
    @Query("select distinct b from Booking b join Item i on i.id=b.item and b.user=?1 and b.user <> i.owner "
            + "order by b.start desc")
    Collection<Booking> getBookingsByUser(Integer userId);

    //@Query("select i from Item i where i.owner=?2 and i.id=?1")
    //Item checkOnOwnItem(Integer itemId,Integer userId);

    @Query("select b from Booking b join Item i on i.id=b.item where i.owner=?1 order by b.start desc")
    Collection<Booking> getAllByOwner(Integer userId);

    @Query("select b from Booking b join Item i on i.id=b.item where i.owner=?1 and b.status=?2 order by b.start desc")
    Collection<Booking> getAllByOwnerAndStatus(Integer userId, Status status);

    @Query("select b from Booking b join Item i on i.id=b.item where i.owner=?1 and b.end>?2 order by b.start desc")
    Collection<Booking> getByOwnerFuture(Integer userId,LocalDateTime date);

    @Query("select b from Booking b join Item i on i.id=b.item where i.owner=?1 and b.end<?2 order by b.start desc")
    Collection<Booking> getByOwnerPast(Integer userId,LocalDateTime date);

    Collection<Booking> findByUserAndEndIsBeforeOrderByStartDesc(Integer userId, LocalDateTime date);
    Collection<Booking> findByUserAndEndIsAfterOrderByStartDesc(Integer userId,LocalDateTime date);
}
