package tn.esprit.rh.achat.service;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.services.IStockService;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.List;

@SpringBootTest
public class StockJUnitTest {

    @Autowired
    private IStockService stockService;
    @Autowired
    private StockServiceImpl stockServiceImpl;
    Stock stock1 = new Stock(1L, "f1",111, 11, null);
    Stock stock2 = new Stock(2L, "f2",222, 22, null);
    Stock stock3 = new Stock(3L, "f3", 333, 33, null);
    @Test
    public void getCorrectStockTest() {
        Stock stock1 = new Stock(10L, "f1", 111, 11, null);
            if (stock1.getIdStock().equals(10L)) {
                System.out.println("It's the correct stock");
            } else {
                System.out.println("It's an incorrect stock");
            }
    }

    @Test
    @Order(1)
    public void addStock(){
        stockServiceImpl.addStock(stock1);
        stockServiceImpl.addStock(stock2);
        stockServiceImpl.addStock(stock3);
    }
    @Test
    @Order(2)
    public List<Stock> retrieveAllStocks(){
        return stockServiceImpl.retrieveAllStocks();
    }

    @Test
    @Order(3)
    public void VerifyStockStatus(){
        stockServiceImpl.retrieveStatusStock();
    }



}
