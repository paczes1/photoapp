package pl.edu.pjwstk.photoapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.pjwstk.photoapp.service.AppUserService;
import pl.edu.pjwstk.photoapp.service.UserDataService;

@Controller
public class AdminController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private UserDataService userDataService;

    @GetMapping("/admin")
    public String showAdminPage() {
        return "/admin";
    }

    @GetMapping("/admin/users")
    public String showAllUsers(Model model){
        model.addAttribute("userForm", appUserService.findAll());
        model.addAttribute("dataForm", userDataService.findAll());

        return "users";
    }

    @GetMapping("/users/{id}/{u_id}")
    public String showUserDetails(Model model, @PathVariable String id, @PathVariable String u_id) {
        appUserService.getById(id).ifPresent(userForm -> model.addAttribute("userForm", userForm));
        userDataService.getById(u_id).ifPresent(dataForm -> model.addAttribute("dataForm", dataForm));
        return "users/user";
    }

    @GetMapping("/users/delete/{id}/{u_id}")
    public String deleteUser(@PathVariable String id, @PathVariable String u_id){
        appUserService.deleteById(id);
        userDataService.deleteById(u_id);

        return "redirect:/deleteSuccess";
    }

    @GetMapping("/users/update/{id}/{u_id}")
    public String updateUser(@PathVariable String id, @PathVariable String u_id, Model model){

        appUserService.getById(id).ifPresent(userForm -> model.addAttribute("userForm", userForm));
        userDataService.getById(u_id).ifPresent(dataForm -> model.addAttribute("dataForm", dataForm));

        return "users/updateUser";
    }


    @GetMapping("/deleteSuccess")
        public String showDeleteConfirm(){
            return "deleteSuccess";
        }




    @GetMapping("/admin/orders")
    public String showAllOrders() {
        return "orders";
    }

    @GetMapping("/admin/reservations")
    public String showAllReservations() {
        return "/admin/reservations";
    }



}
