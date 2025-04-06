package com.aadi.grpc_server.service;

import org.springframework.grpc.server.service.GrpcService;

import com.aadi.grpc_server.entity.Stock;
import com.aadi.grpc_server.repository.StockRepository;
import com.aadi.stocktrading.StockRequest;
import com.aadi.stocktrading.StockResponse;
import com.aadi.stocktrading.StockTradingServiceGrpc.StockTradingServiceImplBase;

import io.grpc.stub.StreamObserver;

@GrpcService
public class StockTradingServiceImpl extends StockTradingServiceImplBase {

    private final StockRepository stockRepository;

    public StockTradingServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void getStockPrice(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        String stockSymbol = request.getStockSymbol();
        Stock stockEntity = stockRepository.findByStockSymbol(stockSymbol);

        StockResponse stockResponse = StockResponse.newBuilder().setStockSymbol(stockSymbol)
                .setPrice(stockEntity.getPrice())
                .setTimestamp(stockEntity.getLastUpdated().toString())
                .build();

        responseObserver.onNext(stockResponse);
        responseObserver.onCompleted();
    }
}
