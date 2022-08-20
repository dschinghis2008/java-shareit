package ru.practicum.shareit.booking;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.StatusDto;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserService;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final ItemService itemService;
    private final UserService userService;
    private final BookingMapper bookingMapper;

    @PostMapping
    public BookingDto add(@RequestBody BookingDto bookingDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        User user = new User();
        user.setId(userId);
        bookingDto.setBooker(user);
        Item item = new Item();
        bookingDto.setItem(item);
        bookingDto.setUserId(userId);
        Booking booking = bookingService.add(bookingMapper.toBooking(bookingDto), userId);
        item = itemService.getById(booking.getItem());
        user = userService.getById(booking.getBookerId());
        return bookingMapper.toDto(booking, item, user);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto updApprove(@PathVariable Integer bookingId, @RequestParam @NonNull Boolean approved,
                                 @RequestHeader("X-Sharer-User-Id") Integer userId) {
        Booking booking = bookingService.updApprove(bookingId, approved, userId);
        Item item = itemService.getById(booking.getItem());
        User user = userService.getById(booking.getBookerId());
        return bookingMapper.toDto(booking, item, user);
    }

    @GetMapping("/{bookingId}")
    public BookingDto findById(@PathVariable Integer bookingId, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        Booking booking = bookingService.findById(bookingId, userId);
        Item item = itemService.getById(booking.getItem());
        User user = userService.getById(booking.getBookerId());
        return bookingMapper.toDto(booking, item, user);
    }

    @GetMapping
    public Collection<BookingDto> findAllByUser(@RequestHeader("X-Sharer-User-Id") Integer userId,
                                                @RequestParam(defaultValue = "ALL") StatusDto state) {
        ArrayList<BookingDto> listDto = new ArrayList<>();
        switch (state) {
            case ALL:
                for (Booking booking : bookingService.findAllByUser(userId)) {
                    Item item = itemService.getById(booking.getItem());
                    User user = userService.getById(booking.getBookerId());
                    listDto.add(bookingMapper.toDto(booking, item, user));
                }
                break;
            case WAITING:
            case APPROVED:
            case REJECTED:
            case CANCELED:
                for (Booking booking : bookingService.findAllByUserAndStatus(userId, bookingMapper.toStatus(state))) {
                    Item item = itemService.getById(booking.getItem());
                    User user = userService.getById(booking.getBookerId());
                    listDto.add(bookingMapper.toDto(booking, item, user));
                }
                break;
            case FUTURE:
                for (Booking booking : bookingService.findAllByUserFuture(userId)) {
                    Item item = itemService.getById(booking.getItem());
                    User user = userService.getById(booking.getBookerId());
                    listDto.add(bookingMapper.toDto(booking, item, user));
                }
                break;
            case PAST:
                for (Booking booking : bookingService.findAllByUserPast(userId)) {
                    Item item = itemService.getById(booking.getItem());
                    User user = userService.getById(booking.getBookerId());
                    listDto.add(bookingMapper.toDto(booking, item, user));
                }
                break;
            case CURRENT:
                for (Booking booking : bookingService.findAllByUserCurrent(userId)) {
                    Item item = itemService.getById(booking.getItem());
                    User user = userService.getById(booking.getBookerId());
                    listDto.add(bookingMapper.toDto(booking, item, user));
                }
        }

        return listDto;
    }

    @GetMapping("/owner")
    public Collection<BookingDto> findAllByOwner(@RequestHeader("X-Sharer-User-Id") Integer userId,
                                                 @RequestParam(defaultValue = "ALL") StatusDto state) {
        ArrayList<BookingDto> listDto = new ArrayList<>();
        switch (state) {
            case ALL:
                for (Booking booking : bookingService.findAllByOwner(userId)) {
                    Item item = itemService.getById(booking.getItem());
                    User user = userService.getById(booking.getBookerId());
                    listDto.add(bookingMapper.toDto(booking, item, user));
                }
                break;
            case WAITING:
            case APPROVED:
            case REJECTED:
            case CANCELED:
                Status status = bookingMapper.toStatus(state);
                for (Booking booking : bookingService.findAllByOwnerAndStatus(userId, status)) {
                    Item item = itemService.getById(booking.getItem());
                    User user = userService.getById(booking.getBookerId());
                    listDto.add(bookingMapper.toDto(booking, item, user));
                }
                break;
            case FUTURE:
                for (Booking booking : bookingService.findAllByOwnerFuture(userId)) {
                    Item item = itemService.getById(booking.getItem());
                    User user = userService.getById(booking.getBookerId());
                    listDto.add(bookingMapper.toDto(booking, item, user));
                }
                break;
            case PAST:
                for (Booking booking : bookingService.findAllByOwnerPast(userId)) {
                    Item item = itemService.getById(booking.getItem());
                    User user = userService.getById(booking.getBookerId());
                    listDto.add(bookingMapper.toDto(booking, item, user));
                }
                break;
            case CURRENT:
                for (Booking booking : bookingService.findAllByOwnerCurrent(userId)) {
                    Item item = itemService.getById(booking.getItem());
                    User user = userService.getById(booking.getBookerId());
                    listDto.add(bookingMapper.toDto(booking, item, user));
                }
        }

        return listDto;
    }

}
