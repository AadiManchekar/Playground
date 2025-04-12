package com.aadi.grpc_client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aadi.grpc_client.service.StockClientService;

@SpringBootApplication
public class GrpcClientApplication implements CommandLineRunner{

	private StockClientService stockClientService;

	public GrpcClientApplication(StockClientService stockClientService) {
		this.stockClientService = stockClientService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GrpcClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("gRPC client response: " + stockClientService.getStockPrice("AAPL").getPrice());
	}

}
