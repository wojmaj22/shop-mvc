package pl.majchrzw.shopmvc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class SessionAttributesController {
	
	@PostMapping("/user_post")
	public String setUser(HttpSession session, @RequestBody String username){
		session.setAttribute("currentUser", username);
		return "redirect:products";
	}
	
	@GetMapping("/user")
	public String userPage(){
		return "user";
	}
}
