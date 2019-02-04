package pl.edu.pjwstk.photoapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pjwstk.photoapp.domain.orders.Comment;
import pl.edu.pjwstk.photoapp.domain.orders.Order;
import pl.edu.pjwstk.photoapp.domain.users.AppUser;
import pl.edu.pjwstk.photoapp.domain.users.Type;
import pl.edu.pjwstk.photoapp.service.AppUserService;
import pl.edu.pjwstk.photoapp.service.OrderService;
import pl.edu.pjwstk.photoapp.service.files.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

@Controller
public class OrderController {

	private final OrderService orderService;
	private final AppUserService userService;
	private final FileStorageService storageService;

	@Autowired
	public OrderController(OrderService orderService, AppUserService userService, FileStorageService storageService) {
		this.orderService = orderService;
		this.userService = userService;
		this.storageService = storageService;
	}

	@GetMapping("/newOrder")
	public String newOrder(Model model) {
		model.addAttribute("customers", userService.findAllCustomers());
		model.addAttribute("order", new Order());
		return "newOrder";
	}


	@PostMapping("/newOrder")
	public String addOrder(@ModelAttribute Order order, @RequestParam("image") MultipartFile image, @RequestParam("image2") MultipartFile image2, @RequestParam("image3") MultipartFile image3, Principal principal, HttpServletRequest request) {
		String login = principal.getName();
		AppUser appUser = userService.getByLogin(login);
		if(request.isUserInRole("ROLE_CUSTOMER")) {
			order.setCustomer(appUser);
		} else if(request.isUserInRole("ROLE_EMPLOYEE")) {
			order.setEmployee(appUser);
		}
		ArrayList<String> imageUrls = new ArrayList<>();

		storeFile(image, imageUrls);
		storeFile(image2, imageUrls);
		storeFile(image3, imageUrls);
		order.setImageUrls(imageUrls);

		String id = orderService.createNewOrder(order);

		return "redirect:orders/" + id;
	}

	private void storeFile(MultipartFile image, ArrayList<String> imageUrls) {
		if (!image.isEmpty()) {
			String fileName = storageService.storeFile(image);
			if(fileName != null) {
				imageUrls.add(fileName);
			}
		}
	}

	@GetMapping("/orders/{id}")
	public String addOrder(Model model, @PathVariable String id) {
		orderService.getById(id).ifPresent(order -> model.addAttribute("order", order));
		model.addAttribute("comment", new Comment());
		return "orders/order";
	}

	@PostMapping("/addComment")
	public String addComment(@ModelAttribute @Valid Comment comment, BindingResult bindingResultComment, @ModelAttribute Order order, Principal principal) {
		String orderId = order.getId();
		if (bindingResultComment.hasErrors()) {
			return "redirect:orders/" + orderId;
		}
		order = orderService.getById(orderId).get();
		if (order.getComments() == null) {
			order.setComments(new ArrayList<>());
		}
		order.getComments().add(comment);
		String login = principal.getName();
		AppUser appUser = userService.getByLogin(login);
		comment.setAuthor(appUser);
		comment.setDate(LocalDateTime.now());

		orderService.updateOrder(order);

		return "redirect:orders/" + orderId;
	}

}
