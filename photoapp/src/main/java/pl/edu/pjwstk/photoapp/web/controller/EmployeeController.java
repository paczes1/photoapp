package pl.edu.pjwstk.photoapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.pjwstk.photoapp.domain.orders.OrderStatus;
import pl.edu.pjwstk.photoapp.service.BookingService;
import pl.edu.pjwstk.photoapp.service.OrderService;

@Controller
public class EmployeeController {

    private final OrderService orderService;
    private final BookingService bookingService;

    @Autowired
    public EmployeeController(OrderService orderService, BookingService bookingService) {
        this.orderService = orderService;
        this.bookingService = bookingService;
    }

    @GetMapping("/employee")
    public String showEmployeePage() {
        return "/employee";
    }

    @GetMapping("employee/orders")
    public String showOrders(Model model) {
        model.addAttribute("orders", orderService.getByStatus(OrderStatus.NEW));
        return "orders";
    }

    @GetMapping("employee/reservations")
    public String showReservations(Model model) {
        model.addAttribute("reservations", bookingService.getAll());
        return "reservations";
    }
}
