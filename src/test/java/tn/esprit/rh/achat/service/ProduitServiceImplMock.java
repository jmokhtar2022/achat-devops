package tn.esprit.rh.achat.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProduitServiceImplMock {

    ProduitRepository produitRepository = Mockito.mock(ProduitRepository.class);

    @InjectMocks
    ProduitServiceImpl produitService;

    Produit produit1 = new Produit(1L, "f1",
            "f2", 111, new Date(), new Date(), new Stock(),
            new HashSet<>(), new CategorieProduit());

    List<Produit> produits = new ArrayList<Produit>() {
        {
            add(produit1);

            add(new Produit(2L, "f3", "f4",222 , new Date(), new Date(), new Stock(),
                    new HashSet<>(), new CategorieProduit()));
            add(new Produit(3L, "f5", "f6",333 , new Date(), new Date(), new Stock(),
                    new HashSet<>(), new CategorieProduit()));
        }
    };

    @Test
    public void testretrieveAllProduits()
    {
        Mockito.when(produitRepository.findAll()).thenReturn(produits);
        List<Produit>produitList=produitService.retrieveAllProduits();
        Assertions.assertNotNull(produitList);
    }

    @Test
    public void testretrieveProduit()
    {
        Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit1));
        Produit produit=produitService.retrieveProduit(1L);
        Assertions.assertNotNull(produit);
    }

    @Test
    public void testaddProduit()
    {
        Mockito.when(produitRepository.save(produits.get(1))).thenReturn(produits.get(1));
       Produit savedProduct=produitService.addProduit(produits.get(1));
        Assertions.assertNotNull(savedProduct);
    }

    @Test
    public void testupdateProduit()
    {
        Mockito.when(produitRepository.save(produits.get(2))).thenReturn(produits.get(2));
        Produit updatedProduct=produitService.updateProduit(produits.get(2));
        Assertions.assertNotNull(updatedProduct);
    }
    @Test
    public void testdeleteProduit()
    {
        Mockito.doNothing().when(produitRepository).deleteById(Mockito.anyLong());
        produitService.deleteProduit(3L);
        Mockito.verify(produitRepository, Mockito.times(1)).deleteById(3L);
    }
}
