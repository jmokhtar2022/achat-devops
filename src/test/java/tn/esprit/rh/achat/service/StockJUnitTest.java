package tn.esprit.rh.achat.service;
import org.junit.jupiter.api.Test;
import tn.esprit.rh.achat.entities.Stock;

public class StockJUnitTest {

    @Test
    public void getCorrectStockTest() {
        Stock stock1 = new Stock(10L, "f1", 111, 11, null);


            if (stock1.getIdStock().equals(10L)) {
                System.out.println("It's the correct stock");
            } else {
                System.out.println("It's an incorrect stock");
            }

    }
}
