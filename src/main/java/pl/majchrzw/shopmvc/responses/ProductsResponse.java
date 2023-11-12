package pl.majchrzw.shopmvc.responses;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import pl.majchrzw.shopmvc.model.Product;

import java.util.ArrayList;

@Data
public class ProductsResponse {
	
	private ArrayList<Product> content;
	
	private int totalElements;
	private int totalPages;
	private int size;
	private int number;
}
