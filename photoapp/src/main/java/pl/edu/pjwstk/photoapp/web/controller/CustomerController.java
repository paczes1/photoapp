package pl.edu.pjwstk.photoapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.pjwstk.photoapp.domain.users.AppUser;
import pl.edu.pjwstk.photoapp.domain.users.UserData;
import pl.edu.pjwstk.photoapp.service.AppUserService;
import pl.edu.pjwstk.photoapp.service.BookingService;
import pl.edu.pjwstk.photoapp.service.OrderService;
import pl.edu.pjwstk.photoapp.service.UserDataService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CustomerController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private UserDataService userDataService;

    private final OrderService orderService;
    private final BookingService bookingService;

    @Autowired
    public CustomerController(OrderService orderService, BookingService bookingService) {
        this.orderService = orderService;
        this.bookingService = bookingService;
    }

    @GetMapping("/customer")
    public String showCustomerPage() {
        return "/customer";
    }

    @GetMapping("customer/orders")
    public String showOrders(Model model, Principal principal) {
        model.addAttribute("orders", orderService.getByCustomer(principal.getName()));
        return "orders";
    }
    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("userForm", new AppUser());
        model.addAttribute("dataForm", new UserData());

        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute(value = "userForm") @Valid AppUser appUser, BindingResult bindingResultAppUser,
                               @ModelAttribute(value = "dataForm") @Valid UserData userData,
                               BindingResult bindingResultUserData){


        if(bindingResultAppUser.hasErrors() || bindingResultUserData.hasErrors()){
            return "register";
        }

        if(appUserService.getByLogin(appUser.getLogin()) != null){
            bindingResultAppUser.rejectValue("login", "error.loginExist","Podana nazwa użytkownika jest już zajęta");
            return "register";
        }

        if(userDataService.getByEmail(userData.getEmail()) != null){
           bindingResultUserData.rejectValue("email", "error.emailExist", "Podany email został już użyty");
            return "register";
        }

        appUserService.addCustomer(appUser);
        userDataService.addCustomerData(userData);

        return "redirect:/success";

    }

    @GetMapping("/success")
    public String showSuccessPage(Model model){
        model.addAttribute("userForm", appUserService.findAll());

        return "success";
    }

    @GetMapping("customer/reservations")
    public String showReservations(Model model, Principal principal) {
        model.addAttribute("reservations", bookingService.getByCustomer(principal.getName()));
        return "reservations";
    }
}
