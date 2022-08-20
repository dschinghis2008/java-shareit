package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    private void checkValidUser(Integer id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private void checkOnValidBeforeAdd(Booking booking, Integer ownerId) {
        if (itemRepository.findById(booking.getItem()).isEmpty()
                || userRepository.findById(booking.getBookerId()).isEmpty()
                || itemRepository.findById(booking.getItem()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (booking.getBookerId() == null || booking.getStart() == null || booking.getEnd() == null
                || booking.getItem() == null
                || !itemRepository.findById(booking.getItem()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getAvailable()
                || booking.getEnd().isBefore(booking.getStart())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (booking.getStart().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (itemRepository.findById(booking.getItem()).orElseThrow().getOwner().equals(ownerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    private Item findItem(Integer itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Booking add(Booking booking, Integer ownerId) {
        checkOnValidBeforeAdd(booking, ownerId);
        booking.setStatus(Status.WAITING);
        log.info("добавлено бронирование /{}/", booking.toString());
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updApprove(Integer bookingId, Boolean approved, Integer userId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        log.info("updApprove>>>>>booking={}, userId={}", booking.toString(), userId);
        if (booking.getStatus() == Status.APPROVED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!findItem(booking.getItem()).getOwner().equals(userId)) {
            log.info("updApprove>>NotFoundItem>>>");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (approved) {
            booking.setStatus(Status.APPROVED);
        } else {
            booking.setStatus(Status.REJECTED);
        }
        log.info("изменен статус бронирования /{}/", booking.toString());
        return bookingRepository.save(booking);
    }

    @Override
    public Booking findById(Integer bookingId, Integer userId) {
        checkValidUser(userId);
        log.info("запрошено бронирование /{}/ владельца /{}/", bookingId, userId);
        return bookingRepository.getById(bookingId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @Override
    public Collection<Booking> findAllByUserAndStatus(Integer userId, Status stat) {
        checkValidUser(userId);
        log.info("запрошены бронирования пользователя /{}/ со статусом /{}/", userId, stat);
        return bookingRepository.getBookingsByUserAndStatus(userId, stat);
    }

    @Override
    public Collection<Booking> findAllByUser(Integer userId) {
        checkValidUser(userId);
        log.info("запрошены бронирования пользователя /{}/", userId);
        return bookingRepository.getBookingsByUser(userId);
    }

    @Override
    public Collection<Booking> findAllByUserFuture(Integer userId) {
        checkValidUser(userId);
        log.info("запрошены бронирования пользователя /{}/ state=FUTURE", userId);
        return bookingRepository.findByBookerIdAndEndIsAfterOrderByStartDesc(userId, LocalDateTime.now());
    }

    @Override
    public Collection<Booking> findAllByUserPast(Integer userId) {
        checkValidUser(userId);
        log.info("запрошены бронирования пользователя /{}/ state=PAST", userId);
        return bookingRepository.findByBookerIdAndEndIsBeforeOrderByStartDesc(userId, LocalDateTime.now());
    }

    @Override
    public Collection<Booking> findAllByUserCurrent(Integer userId) {
        checkValidUser(userId);
        log.info("запрошены бронирования пользователя /{}/ state=CURRENT", userId);
        return bookingRepository.getByUserCurrent(userId, LocalDateTime.now());
    }

    @Override
    public Collection<Booking> findAllByOwner(Integer userId) {
        checkValidUser(userId);
        log.info("запрошены бронирования вещей владельца /{}/", userId);
        return bookingRepository.getAllByOwner(userId);
    }

    @Override
    public Collection<Booking> findAllByOwnerAndStatus(Integer userId, Status stat) {
        checkValidUser(userId);
        log.info("запрошены бронирования вещей владельца /{}/ со статусом /{}/", userId, stat);
        return bookingRepository.getAllByOwnerAndStatus(userId, stat);
    }

    @Override
    public Collection<Booking> findAllByOwnerFuture(Integer userId) {
        checkValidUser(userId);
        log.info("запрошены бронирования вещей владельца /{}/ state=FUTURE", userId);
        return bookingRepository.getByOwnerFuture(userId, LocalDateTime.now());
    }

    @Override
    public Collection<Booking> findAllByOwnerPast(Integer userId) {
        checkValidUser(userId);
        log.info("запрошены бронирования вещей владельца /{}/ state=PAST", userId);
        return bookingRepository.getByOwnerPast(userId, LocalDateTime.now());
    }

    @Override
    public Collection<Booking> findAllByOwnerCurrent(Integer userId) {
        checkValidUser(userId);
        log.info("запрошены бронирования вещей владельца /{}/ state=CURRENT", userId);
        return bookingRepository.getByOwnerCurrent(userId, LocalDateTime.now());
    }
}
