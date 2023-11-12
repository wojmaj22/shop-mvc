package pl.majchrzw.shopmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	private Long id;
	
	private String user;
	
	private Date orderDate;
	
	private OrderStatus orderStatus;
	
	private List<OrderDetail> orderDetails;
}
