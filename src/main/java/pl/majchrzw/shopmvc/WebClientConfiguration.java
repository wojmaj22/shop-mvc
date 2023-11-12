package pl.majchrzw.shopmvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfiguration {
	
	@Value("${api.address}")
	private String API_URL;
	
	@Bean
	public WebClient webClient(WebClient.Builder builder){
		return builder.baseUrl(API_URL)
				.filter(logRequest())
				.build();
	}
	
	private static ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
			clientRequest.headers().forEach((name, values) ->
					values.forEach(value -> System.out.println(name + ": " + value)));
			System.out.println("Request Body: " + clientRequest.body().toString());
			return Mono.just(clientRequest);
		});
	}
}
