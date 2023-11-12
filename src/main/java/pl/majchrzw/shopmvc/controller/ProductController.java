package pl.majchrzw.shopmvc.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.majchrzw.shopmvc.ShopMvcApplication;
import pl.majchrzw.shopmvc.model.Product;
import pl.majchrzw.shopmvc.responses.ProductsResponse;
import pl.majchrzw.shopmvc.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@GetMapping("/products")
	public String ProductsList(Model model, HttpSession session, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
		ProductsResponse response = productService.getProducts(page,size);
		model.addAttribute( "products", response.getContent());
		
		model.addAttribute("user", ShopMvcApplication.getUsernameFromSession(session));
		if( response.getNumber() > 0){
			model.addAttribute("hasPrevious", true);
		} else {
			model.addAttribute("hasPrevious", false);
		}
		if( response.getNumber()+1 < response.getTotalElements()){
			model.addAttribute("hasNext", true);
		} else {
			model.addAttribute("hasNext", false);
		}
		model.addAttribute("page", response.getNumber());
		
		return "products";
	}
	
	@GetMapping("/products/delete")
	public String deleteProductFromList(@RequestParam Integer id){
		productService.deleteProduct(id);
		return "redirect:/products";
	}
	
	@GetMapping("/products/add")
	public String addProductGet(Model model){
		model.addAttribute("product", new Product());
		return "add_product";
	}
	
	@PostMapping("/products/save")
	public String addProductPost(@ModelAttribute("product") Product product){
		productService.postProduct(product);
		return "redirect:/products";
	}
}
