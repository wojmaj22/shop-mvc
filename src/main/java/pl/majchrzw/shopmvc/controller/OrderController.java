package pl.majchrzw.shopmvc.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.majchrzw.shopmvc.ShopMvcApplication;
import pl.majchrzw.shopmvc.model.Order;
import pl.majchrzw.shopmvc.requests.AddToCartRequest;
import pl.majchrzw.shopmvc.service.OrderService;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@GetMapping("/order")
	public String getCurrentOrder(Model model, HttpSession session){
		Order order =  orderService.getCurrentUserOrder(ShopMvcApplication.getUsernameFromSession(session));
		model.addAttribute( "order", order);
		model.addAttribute("totalPrice", orderService.calculateTotalPrice(order));
		return "order";
	}
	
	@GetMapping("/order/add")
	public String addToOrder(@RequestParam("productId") Integer productId, HttpSession session, @RequestParam("quantity") Integer quantity){

		Long orderId = orderService.getCurrentUserOrder(ShopMvcApplication.getUsernameFromSession(session)).getId();
		AddToCartRequest requestBody = new AddToCartRequest(quantity,productId);
		orderService.postProductToOrder(orderId, requestBody);
		return "redirect:/order";
	}
}
