package pl.edu.pjwstk.photoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.photoapp.db.repositories.ReservationRepository;
import pl.edu.pjwstk.photoapp.domain.reservations.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final ReservationRepository reservationRepository;
    private final AppUserService appUserService;

    @Autowired
    public BookingService(ReservationRepository reservationRepository, AppUserService appUserService) {
        this.reservationRepository = reservationRepository;
        this.appUserService = appUserService;
    }

    public String createNewReservation(Reservation reservation) {
        return reservationRepository.save(reservation).getId();
    }

    public Optional<Reservation> getById(String id) {
        return reservationRepository.findById(id);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void sendNotification() {
    }

    public List<Reservation> getByDate(LocalDateTime date) {
        return reservationRepository.findByDate(date);
    }


    public void cancelReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }


    public List<Reservation> getByCustomer(String login) {
        return reservationRepository.findByCustomer(appUserService.getByLogin(login));
    }
}

