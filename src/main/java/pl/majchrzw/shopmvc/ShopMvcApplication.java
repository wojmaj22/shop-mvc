package pl.majchrzw.shopmvc;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopMvcApplication.class, args);
	}
	
	public static String getUsernameFromSession(HttpSession session){
		String attribute = (String) session.getAttribute("currentUser");
		String user = null;
		if ( attribute != null) {
			user = attribute.substring(attribute.indexOf("=")+1);
		}
		return user;
	}

}
