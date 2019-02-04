package pl.edu.pjwstk.photoapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.photoapp.domain.reservations.Reservation;
import pl.edu.pjwstk.photoapp.domain.reservations.ReservationDateAndTime;
import pl.edu.pjwstk.photoapp.service.AppUserService;
import pl.edu.pjwstk.photoapp.service.BookingService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class CalendarController {

    private final BookingService bookingService;

    private final AppUserService appUserService;

    @Autowired
    public CalendarController(BookingService bookingService, AppUserService appUserService) {
        this.bookingService = bookingService;
        this.appUserService = appUserService;
    }

    @GetMapping("/newReservation")
    public String newReservation(Model model) {
        model.addAttribute("customers", appUserService.findAllCustomers());
        model.addAttribute("reservation", new Reservation());
        return "newReservation";
    }

    @GetMapping("/calendar")
    public String showCalendarPage(Model model, Principal principal) {
        model.addAttribute("customers", appUserService.findAllCustomers());
        model.addAttribute("reservations", bookingService.getByCustomer(principal.getName()));
        model.addAttribute("reservationDateAndTime", new ReservationDateAndTime());
        return "/calendar";
    }

    @GetMapping("/reservationsByDate/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getReservationsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        return bookingService.getByDate(localDate.atStartOfDay());
    }

    @PostMapping("/newReservation")
    public String addReservation(@ModelAttribute Reservation reservation, @RequestParam String datetime, Principal principal) {

        reservation.setCustomer(appUserService.getByLogin(principal.getName()));

        reservation.setDate(LocalDateTime.parse(datetime, DateTimeFormatter.ISO_DATE_TIME));

        String id = bookingService.createNewReservation(reservation);
        return "redirect:reservations/" + id;
    }

    @PostMapping("/addReservation")
    public String addReservation(@Valid @ModelAttribute ReservationDateAndTime reservationDateAndTime, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()){
            return "redirect:calendar";
        }
        Reservation reservation = new Reservation();
        reservation.setCustomer(appUserService.getByLogin(principal.getName()));
        reservation.setDate(reservationDateAndTime.getLocalDateTime());

        String id = bookingService.createNewReservation(reservation);
        return "redirect:reservations/" + id;
    }

    @GetMapping("/reservations/{id}")
    public String addReservaton(Model model, @PathVariable String id) {
        bookingService.getById(id).ifPresent(reservation -> model.addAttribute("reservation", reservation));
        return "reservations/reservation";
    }
}
