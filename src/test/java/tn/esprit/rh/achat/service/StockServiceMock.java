package tn.esprit.rh.achat.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StockServiceMock {

    StockRepository stockRepository = Mockito.mock(StockRepository.class);

    @InjectMocks
    StockServiceImpl stockService;

    Stock stock1 = new Stock(1L, "f1",
            111, 11, null);
    Stock stock2 = new Stock(2L, "f2",
            222, 22, null);
    Stock stock3 = new Stock(3L, "f3",
            333, 33, null);
    Stock stock4 = new Stock(4L, "f4",
            444, 44, null);

    List<Stock> stocks = new ArrayList<Stock>() {
        {
            add(stock1);
            add(stock2);
            add(stock3);
        }
    };


    @Test
    void testretrieveAllProduits()
    {
        Mockito.when(stockRepository.findAll()).thenReturn(stocks);
        List<Stock>produitList=stockRepository.findAll();
        Assertions.assertEquals(3,produitList.size());

    }
    @Test
    void testretrieveProduit()
    {
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock1));
        Stock stock=stockRepository.findById(1L).get();
        Assertions.assertEquals("f1",stock1.getLibelleStock());
    }
    @Test
    void testaddStock()
    {
        Mockito.when(stockRepository.save(stocks.get(1))).thenReturn(stocks.get(1));
        Stock savedStock=stockService.addStock(stocks.get(1));
        Assertions.assertEquals(111,stock1.getQte());
    }


    @Test
    void testdeleteStock()
    {
        Mockito.doNothing().when(stockRepository).deleteById(Mockito.anyLong());
        stockService.deleteStock(3L);
        Mockito.verify(stockRepository, Mockito.times(1)).deleteById(3L);
    }

    }



