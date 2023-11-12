package pl.majchrzw.shopmvc.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddToCartRequest {
	private int quantity;
	
	private int id;
}
