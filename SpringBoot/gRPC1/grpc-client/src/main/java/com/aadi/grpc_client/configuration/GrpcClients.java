package com.aadi.grpc_client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aadi.stocktrading.StockTradingServiceGrpc;
import com.aadi.stocktrading.StockTradingServiceGrpc.StockTradingServiceBlockingStub;

import io.grpc.ManagedChannelBuilder;

@Configuration
public class GrpcClients {
    
    @Bean
    public StockTradingServiceBlockingStub blockingStub() {
        return StockTradingServiceGrpc.newBlockingStub(ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build());
    }
}
