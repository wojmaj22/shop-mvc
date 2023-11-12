package pl.majchrzw.shopmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
	
	private Long id;
	private Product product;
	private int quantity;
	private double price;
}
