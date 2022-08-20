package ru.practicum.shareit.booking;

import java.util.Collection;

public interface BookingService {
    Booking add(Booking booking, Integer ownerId);

    Booking updApprove(Integer bookingId, Boolean approved, Integer userId);

    Booking findById(Integer bookingId, Integer userId);

    Collection<Booking> findAllByUserAndStatus(Integer userId, Status stat);

    Collection<Booking> findAllByUser(Integer userId);

    Collection<Booking> findAllByOwner(Integer userId);

    Collection<Booking> findAllByOwnerAndStatus(Integer userId, Status stat);

    Collection<Booking> findAllByOwnerFuture(Integer userId);

    Collection<Booking> findAllByOwnerPast(Integer userId);

    Collection<Booking> findAllByOwnerCurrent(Integer userId);

    Collection<Booking> findAllByUserFuture(Integer userId);

    Collection<Booking> findAllByUserPast(Integer userId);

    Collection<Booking> findAllByUserCurrent(Integer userId);

}
