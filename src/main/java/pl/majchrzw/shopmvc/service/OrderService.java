package pl.majchrzw.shopmvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.majchrzw.shopmvc.model.Order;
import pl.majchrzw.shopmvc.model.OrderDetail;
import pl.majchrzw.shopmvc.model.OrderStatus;
import pl.majchrzw.shopmvc.requests.AddToCartRequest;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final WebClient webClient;
	
	public Order getCurrentUserOrder(String username){
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/api/orders")
						.queryParam("username", username)
						.queryParam("orderStatus", OrderStatus.NEW)
						.build())
				.retrieve()
				.bodyToMono(Order.class)
				.block();
	}
	
	public double calculateTotalPrice(Order order){
		double price = 0.0;
		if ( order != null && order.getOrderDetails() != null){
			for (OrderDetail detail: order.getOrderDetails()){
				price += detail.getPrice();
			}
		}
		return price;
	}
	
	public void postProductToOrder(Long orderId, AddToCartRequest requestBody){
		webClient.post()
				.uri("/api/orders/{id}/products", orderId)
				.bodyValue(requestBody)
				.retrieve()
				.toEntity(Void.class)
				.block();
	}
}
