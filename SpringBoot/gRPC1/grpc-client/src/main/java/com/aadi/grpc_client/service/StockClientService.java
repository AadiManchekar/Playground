package com.aadi.grpc_client.service;

import org.springframework.stereotype.Service;

import com.aadi.stocktrading.StockRequest;
import com.aadi.stocktrading.StockResponse;
import com.aadi.stocktrading.StockTradingServiceGrpc.StockTradingServiceBlockingStub;


@Service
public class StockClientService {
    private StockTradingServiceBlockingStub serviceBlockingStub;
    
    public StockClientService(StockTradingServiceBlockingStub serviceBlockingStub) {
        this.serviceBlockingStub = serviceBlockingStub;
    }

    public StockResponse getStockPrice(String stockSymbol) {
        StockRequest stockRequest = StockRequest.newBuilder().setStockSymbol(stockSymbol).build();
        return serviceBlockingStub.getStockPrice(stockRequest);
    }
}
