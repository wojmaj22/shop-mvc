package pl.majchrzw.shopmvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.majchrzw.shopmvc.model.Product;
import pl.majchrzw.shopmvc.responses.ProductsResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final WebClient webClient;
	
	public ProductsResponse getProducts(Optional<Integer> page, Optional<Integer> size){
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/api/products")
						.queryParam("size", size.orElse(10))
						.queryParam("page", page.orElse(0))
						.build())
				.retrieve()
				.bodyToMono(ProductsResponse.class)
				.block();
	}
	
	public void deleteProduct(Integer id){
		webClient.delete()
				.uri("/api/products/{id}", id)
				.retrieve()
				.toEntity(Void.class)
				.block();
	}
	
	public void postProduct(Product product){
		webClient.post()
				.uri("/api/products")
				.bodyValue(product)
				.retrieve()
				.toEntity(Void.class)
				.block();
	}
}
